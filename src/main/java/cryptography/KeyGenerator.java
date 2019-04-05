package cryptography;

import java.security.SecureRandom;
import java.util.Arrays;

public final class KeyGenerator {

    public static void main(String[] args) {
        KeyGenerator kg = new KeyGenerator();

        System.out.println(Arrays.toString(kg.generate()));
        System.out.println(Arrays.toString(kg.generate()));
        System.out.println(Arrays.toString(kg.generate()));
    }

    public byte[] generate() {
        byte[] bytes = new byte[16];
        SecureRandom r = new SecureRandom();
        r.nextBytes(bytes);

        return bytes;
    }

}
