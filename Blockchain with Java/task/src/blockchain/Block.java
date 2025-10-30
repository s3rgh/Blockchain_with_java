package blockchain;

public class Block {
    private long id;
    private long timestamp;
    private long magicNumber;
    private long duration;
    private String previousBlockHash;
    private String currentBlockHash;
    private String createdBy;

    public Block(long id, String previousHash, long timestamp, long magicNumber, String data) {
        this.id = id;
        this.previousBlockHash = previousHash;
        this.timestamp = timestamp;
        this.magicNumber = magicNumber;
        this.currentBlockHash = StringUtil.applySha256(previousHash + timestamp + magicNumber + data);
    }

    @Override
    public String toString() {
        return "Block:\n" +
                "Created by miner # " + createdBy + "\n" +
                "Id: " + id + "\n" +
                "Timestamp: " + timestamp + "\n" +
                "Magic number: " + magicNumber + "\n" +
                "Hash of the previous block:\n" + previousBlockHash + "\n" +
                "Hash of the block:\n" + currentBlockHash + "\n" +
                "Block was generating for " + duration + " seconds\n";
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCurrentBlockHash(String currentBlockHash) {
        this.currentBlockHash = currentBlockHash;
    }

    public String getCurrentBlockHash() {
        return currentBlockHash;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public long getId() {
        return id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getMagicNumber() {
        return magicNumber;
    }

    public long getDuration() {
        return duration;
    }
}
