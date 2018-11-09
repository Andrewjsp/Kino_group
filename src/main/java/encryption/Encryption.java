package encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    public String getHashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = messageDigest.digest(password.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(String.format("%02x", b));
        }
        String hashPassword = stringBuilder.toString();
        return hashPassword;
    }
}
