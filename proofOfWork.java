import java.security.MessageDigest;

public class ProofOfWork {

    // Difficulty of the PoW algorithm, which determines how difficult it is to find a valid solution.
    private static final int DIFFICULTY = 5;

    public static void main(String[] args) {
        // The data that we want to add to the blockchain (in this case, a simple message).
        String data = "I am adding this data to the blockchain";

        // The current timestamp, which will be used as the block's timestamp.
        long timestamp = System.currentTimeMillis();

        // The nonce is a random number that we will use as part of the proof of work algorithm.
        // We will iterate over different nonce values until we find a valid solution.
        int nonce = 0;

        // Keep incrementing the nonce until we find a valid solution.
        while (true) {
            // Combine the data, timestamp, and nonce to create the input for the proof of work algorithm.
            String input = data + timestamp + nonce;

            // Use the SHA-256 message digest to hash the input.
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(input.getBytes());

            // Convert the hash to a hexadecimal string.
            String hashString = bytesToHex(hash);

            // Check if the hash string has the required number of leading zeros.
            if (hashString.startsWith("0".repeat(DIFFICULTY))) {
                // If it does, we have found a valid solution!
                System.out.println("Found a valid solution: " + hashString);
                break;
            }

            // If we didn't find a solution, increment the nonce and try again.
            nonce++;
        }
    }

    // Converts a byte array to a hexadecimal string.
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
