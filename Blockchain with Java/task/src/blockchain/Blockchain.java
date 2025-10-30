package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private static Blockchain INSTANCE;
    private final List<Block> chain = new ArrayList<>();
    private static final long TARGET_TIME_SECONDS = 10;
    private int difficulty = 0;
    private long lastBlockTimestamp;

    private Blockchain() {
        // Genesis block
        Block genesis = new Block(0, "0", System.currentTimeMillis(), 0, "genesis");
        chain.add(genesis);
        lastBlockTimestamp = genesis.getTimestamp();
    }

    public static Blockchain getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Blockchain();
        }
        return INSTANCE;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public Block getLastBlock() {
        return chain.getLast();
    }

    public synchronized boolean addBlock(Block candidate) {
        Block last = getLastBlock();

        if (!candidate.getPreviousBlockHash().equals(last.getCurrentBlockHash())) {
            return false;
        }
        if (!candidate.getCurrentBlockHash().startsWith("0".repeat(difficulty))) {
            return false;
        }

        chain.add(candidate);

        long actualTime = (candidate.getTimestamp() - lastBlockTimestamp) / 1000; // в секундах
        adjustDifficulty(actualTime);
        lastBlockTimestamp = candidate.getTimestamp();

        String change;
        if (actualTime < TARGET_TIME_SECONDS / 2) {
            change = "N was increased to " + difficulty;
        } else if (actualTime > TARGET_TIME_SECONDS * 2) {
            change = "N was decreased by 1";
        } else {
            change = "N stays the same";
        }

        System.out.print(candidate);
        System.out.println(change + "\n");

        return true;
    }

    private void adjustDifficulty(long actualTime) {
        if (actualTime < TARGET_TIME_SECONDS / 2) {
            difficulty++;
        } else if (actualTime > TARGET_TIME_SECONDS * 2) {
            difficulty = Math.max(0, difficulty - 1);
        }
    }
}
