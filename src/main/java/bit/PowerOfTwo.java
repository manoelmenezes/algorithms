package bit;

public class PowerOfTwo {

    public static boolean isPowerOfTwo(int i) {
        return i != 0 && (i & (i - 1)) == 0;
    }
}
