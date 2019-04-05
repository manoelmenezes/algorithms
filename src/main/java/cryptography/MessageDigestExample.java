package cryptography;

import javax.crypto.Cipher;
import java.security.MessageDigest;
import java.util.Arrays;

public class MessageDigestExample {

    public static void main(String[] args) throws Exception {
        int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");

        System.out.println(maxKeyLen == Integer.MAX_VALUE);

        byte[] data = {1, 2, 3};

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        messageDigest.update(data);

        byte[] hash = messageDigest.digest();

        System.out.println(">>> hash");
        System.out.println(Arrays.toString(hash));

        byte[] data1 = {1, 2, 3};

        messageDigest.update(data1);

        byte[] hash1 = messageDigest.digest();

        System.out.println(MessageDigest.isEqual(hash, hash1));

    }

}
