package bit;

public class UnsetRightMostSetBit {

//    public static int unsetRightMost(int i) {
//        int c = 0;
//        int tmp = i;
//        while (((tmp >> 1) & Integer.MAX_VALUE) != 0) {
//            c++;
//            tmp = tmp >> 1;
//        }
//
//        return i & ~(1 << c);
//    }

    public static int unsetRightMost1(int i) {
        return i & (i - 1);
    }

    public static void main(String[] args) {
        System.out.println(Integer.valueOf("-" + Integer.toBinaryString(Integer.MIN_VALUE), 2));

        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE + 1);
        System.out.println(-1 * (int)Math.pow(2, 31) - 1);

        System.out.println(Integer.MAX_VALUE);
        System.out.println((int)Math.pow(2, 31));
    }
}
