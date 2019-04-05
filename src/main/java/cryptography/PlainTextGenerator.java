package cryptography;

import java.util.Arrays;

public final class PlainTextGenerator {

    public static void main(String[] args) {
        PlainTextGenerator ptg = new PlainTextGenerator();

        byte[] bytes = ptg.generate();

        System.out.println(Arrays.toString(bytes));
    }

    /**
     * The the bytes from 0..15 indexes will be
     * the same as the bytes from 16..31 indexes.
     *
     * @return bytes from 0..15 equal to the
     * bytes from 16..31.
     */
    public byte[] generate() {
        byte[] bytes = new byte[32];

        for (int i = 0; i < 32; i++) {
            bytes[i] = (byte) (i % 16);
        }

        return bytes;
    }
}