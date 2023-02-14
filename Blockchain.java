import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;


public class Blockchain {
  private List<Block> blocks;
  
  public Blockchain() {
    blocks = new ArrayList<>();
    blocks.add(createGenesisBlock());
  }
  
  public Block createGenesisBlock() {
    return new Block(0, "Genesis block");
  }
  
  public Block getLatestBlock() {
    return blocks.get(blocks.size() - 1);
  }
  
  public void addBlock(Block block) {
    block.setPreviousHash(getLatestBlock().getHash());
    block.setHash(block.calculateHash());
    blocks.add(block);
  }
  
  public boolean isChainValid() {
    for (int i = 1; i < blocks.size(); i++) {
      Block currentBlock = blocks.get(i);
      Block previousBlock = blocks.get(i - 1);
      
      // Check if the current block's hash is correct
      if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
        return false;
      }
      
      // Check if the previous block's hash is correct
      if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
        return false;
      }
    }
    return true;
  }
 
}

