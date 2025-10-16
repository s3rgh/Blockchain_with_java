package blockchain;

public class Main {
    public static void main(String[] args) {
        var chain = new Blockchain();
        chain.generateBlock(System.currentTimeMillis());
        chain.generateBlock(System.currentTimeMillis());
        chain.generateBlock(System.currentTimeMillis());
        chain.generateBlock(System.currentTimeMillis());
        chain.printBlocks();
    }
}
