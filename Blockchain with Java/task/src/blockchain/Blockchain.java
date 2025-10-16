package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private final List<Block> blocks= new ArrayList<>();;

    public Blockchain() {
        blocks.add(new Block(System.currentTimeMillis()));
    }

    public Block getLastBlock() {
        return blocks.getLast();
    }

    public void generateBlock(long timestamp) {
        var block = new Block(timestamp);
        block.setId(blocks.size() + 1);
        block.setPreviousBlockHash(getLastBlock().getCurrentBlockHash());
        blocks.add(block);
    }

    public boolean isValid() {
        for (int i = 1; i < blocks.size(); i++) {
            var currentBlock = blocks.get(i);
            var previousBlock = blocks.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.getCurrentBlockHash())
                    ||
                    !previousBlock.getHash().equals(currentBlock.getPreviousBlockHash())
            ) {
                return false;
            }
        }
        return true;
    }

    public void printBlocks() {
        blocks.forEach(System.out::println);
    }
}
