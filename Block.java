import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Block {
  private int previousHash;
  private String data;
  private String hash;
  
  public Block(int previousHash, String data) {
    this.previousHash = previousHash;
    this.data = data;
    this.hash = calculateHash();
  }
  
  public String calculateHash() {
    // Use SHA-256 to hash the previous hash, data, and a random number
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      String input = previousHash + data + (int) (Math.random() * 10);
      byte[] hash = digest.digest(input.getBytes("UTF-8"));
      
      // Convert the hash to a hexadecimal string
      StringBuilder hexString = new StringBuilder();
      for (int i = 0; i < hash.length; i++) {
        String hex = Integer.toHexString(0xff & hash[i]);
        if (hex.length() == 1) {
          hexString.append('0');
        }
        hexString.append(hex);
      }
      return hexString.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  public int getPreviousHash() {
    return previousHash;
  }
  
  public String getData() {
    return data;
  }
  
  public String getHash() {
    return hash;
  }
}