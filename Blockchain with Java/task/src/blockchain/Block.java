package blockchain;

public class Block {
    private long id;
    private final long timestamp;
    private String previousBlockHash;
    private final String currentBlockHash;

    protected Block(long timestamp) {
        this.id = 1;
        this.timestamp = timestamp;
        this.previousBlockHash = "0";
        this.currentBlockHash = getHash();
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Hash of the previous block:\n" + previousBlockHash + "\n" +
                "Hash of the block:\n" + currentBlockHash + "\n";
    }

    public String getHash() {
        return StringUtil.applySha256(this.previousBlockHash + this.timestamp + this.currentBlockHash + this.id);
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public String getCurrentBlockHash() {
        return currentBlockHash;
    }

    public void setId(long id) {
        this.id = id;
    }
}
