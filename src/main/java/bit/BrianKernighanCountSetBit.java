package bit;

public class BrianKernighanCountSetBit {

    public static int count(int i) {
        int count = 0;

        while (i != 0) {
            count++;
            i = i & (i - 1);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(Integer.valueOf("0000FFFF", 16)));
    }
}
