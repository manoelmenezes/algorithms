package cryptography;

import java.util.Arrays;
import java.nio.ByteBuffer;

public final class InitializationVectorGenerator {

    public byte[] getIV(long messageNo) {
        ByteBuffer bb = ByteBuffer.allocate(16);
        bb.putLong(0, messageNo);
        return bb.array();
    }

    public static void main(String[] agrs) {

        InitializationVectorGenerator ivg = new InitializationVectorGenerator();

        System.out.println(Arrays.toString(ivg.getIV(0)));
        System.out.println(Arrays.toString(ivg.getIV(1)));
        System.out.println(Arrays.toString(ivg.getIV(2)));
        System.out.println(Arrays.toString(ivg.getIV(3)));
        System.out.println(Arrays.toString(ivg.getIV(4)));
        System.out.println(Arrays.toString(ivg.getIV(5)));
        System.out.println(Arrays.toString(ivg.getIV(6362828937L)));
        System.out.println(Arrays.toString(ivg.getIV(Long.MAX_VALUE)));

    }

}