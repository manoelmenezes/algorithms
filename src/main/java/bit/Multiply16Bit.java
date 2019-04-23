package bit;

public class Multiply16Bit {

    public static int multiply(int m, int n) {

        int mLow = m & (Integer.valueOf("11111111", 2));
        int mHigh = m >> 8;
        int nLow = n & (Integer.valueOf("11111111", 2));
        int nHigh = n >> 8;

        return 1;

    }
}
