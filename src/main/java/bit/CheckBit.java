package bit;

public class CheckBit {

    public static boolean check(int i, int bit) {
        return ((i >> (bit - 1)) & 1) == 1;
    }
}
