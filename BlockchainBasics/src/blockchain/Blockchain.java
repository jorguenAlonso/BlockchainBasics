package blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private List<Block> blocks;

    public Blockchain() {
        blocks = new ArrayList<>();
    }

    public void addBlock(Block block) {
        if (blocks.isEmpty()) {
            // If the blockchain is empty, set previousHash as an empty string
            block.setPreviousHash("");
        } else {
            // Otherwise, set previousHash as the hash of the last block in the list
            Block lastBlock = getLastBlock();
            block.setPreviousHash(lastBlock.getHash());
        }
        blocks.add(block);
    }
    
    public int size() {
        return blocks.size();
    }

    public Block getLastBlock() {
        return blocks.get(blocks.size() - 1);
    }

    public boolean isValid() {
        for (int i = 1; i < blocks.size(); i++) {
            if (!blocks.get(i).isValid(blocks.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Block block : blocks) {
            sb.append(block).append("\n");
        }
        return sb.toString();
    }
}