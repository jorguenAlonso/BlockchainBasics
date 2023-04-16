package blockchain;

public class Main {

	public static void main(String[] args) {
        System.out.println("Welcome to the Blockchain Generator!");

        // Create a new blockchain
        Blockchain blockchain = new Blockchain();

        // Create a scanner to read input from the console
        Scanner scanner = new Scanner(System.in);

        // Loop to get user input for block data
        while (true) {
            System.out.print("Enter block data (or 'quit' to exit): ");
            String data = scanner.nextLine();

            // Check if user wants to quit
            if ("quit".equalsIgnoreCase(data)) {
                break;
            }

            // Check if blockchain has at least one block
            if (blockchain.size() > 0) {
                // Create a new block with the user input data and previous hash
                Block block = new Block(System.currentTimeMillis(), data,
                        blockchain.getLastBlock().getHash());
                blockchain.addBlock(block);
                System.out.println("New block added: " + block);
            } else {
                // Create the first block with the user input data
                Block block = new Block(System.currentTimeMillis(), data, "");
                blockchain.addBlock(block);
                System.out.println("Genesis block added: " + block);
            }
        }

        // Check if the blockchain is valid
        if (blockchain.isValid()) {
            System.out.println("Blockchain is valid.");
        } else {
            System.out.println("Blockchain is not valid.");
        }

    }
}