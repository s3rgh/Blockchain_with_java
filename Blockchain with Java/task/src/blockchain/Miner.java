package blockchain;

import java.util.concurrent.Callable;

public class Miner implements Callable<Block> {
    private final Blockchain blockchain = Blockchain.getInstance();
    private final String name;

    public Miner(String name) {
        this.name = name;
    }

    @Override
    public Block call() {
        while (!Thread.currentThread().isInterrupted()) {
            Block minedBlock = tryMineBlock();
            if (minedBlock != null) {
                // Успешно добавили блок — возвращаем его
                return minedBlock;
            }
            // Если null — значит, кто-то опередил нас или поток прерван.
            // Продолжаем майнить следующий блок.
        }
        return null;
    }

    private Block tryMineBlock() {
        Block lastBlock = blockchain.getLastBlock();
        int currentDifficulty = blockchain.getDifficulty();
        long startTime = System.currentTimeMillis();

        long nonce = 0;
        while (!Thread.currentThread().isInterrupted()) {
            long timestamp = System.currentTimeMillis();
            String data = "Block data";
            String hash = StringUtil.applySha256(
                    lastBlock.getCurrentBlockHash() + timestamp + nonce + data
            );

            if (hash.startsWith("0".repeat(currentDifficulty))) {
                // Создаём кандидат
                Block candidate = new Block(
                        lastBlock.getId() + 1,
                        lastBlock.getCurrentBlockHash(),
                        timestamp,
                        nonce,
                        data
                );
                candidate.setCreatedBy(name);
                candidate.setDuration((timestamp - startTime) / 1000);

                // Пытаемся добавить в блокчейн
                if (blockchain.addBlock(candidate)) {
                    return candidate; // Успех!
                } else {
                    // Кто-то опередил — выходим, чтобы начать майнить следующий блок
                    return null;
                }
            }

            nonce++;
            if (nonce % 1_000_000 == 0) {
                if (Thread.currentThread().isInterrupted()) {
                    return null;
                }
                Thread.yield();
            }
        }
        return null;
    }
}