package blockchain;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;




public class Block {

    private long timestamp;
    private String data;
    private String previousHash;
    private String hash;

    public Block(long timestamp, String data, String previousHash) {
        this.timestamp = timestamp;
        this.data = data;
        this.previousHash = previousHash;
        this.hash = calculateHash();
    }

    public Block(int timestamp, String data) {
        this(timestamp, data, "");
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getData() {
        return data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    private String calculateHash() {
        String hash = String.valueOf(timestamp) + data + previousHash;
        return sha256(hash);
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }
    
    private static String sha256(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data.getBytes());
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Block{" +
                "timestamp=" + timestamp +
                ", data='" + data + '\'' +
                ", previousHash='" + previousHash + '\'' +
                ", hash='" + hash + '\'' +
                '}';
    }

    public boolean isValid(Block previousBlock) {
        if (previousBlock == null) {
            return false;
        }
        if (!previousBlock.getHash().equals(this.previousHash)) {
            return false;
        }
        return true;
    }}
