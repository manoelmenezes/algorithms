package math;

import java.util.Random;

public class Random1To7 {

    private Random random = new Random();

    public static void main(String[] args) {
        Random1To7 r = new Random1To7();

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int v = r.random1To7();
            if (v < 1 || v > 7) {
                throw new IllegalStateException("Incorrect random value " + v);
            }
            System.out.println(v);
        }
    }

    private int random1To5() {
        return 1 + random.nextInt(5);
    }

    /**
     * Given the function random1To5 return a random number in the range 1 to 7.
     * <p>
     * It is necessary to convert a number x in the range 1 to 5 to a number y
     * in the range 1 to 7.
     * <p>
     * One approach is to have two numbers r and s in the range 1 to 5.
     * <p>
     * r s sum  sum % 7 mod + 1
     * 1 1 1    1       2           4
     * 1 2 3    3       4           3
     * 1 3 4    4       5           3
     * 1 4 5    5       6           4
     * 1 5 6    6       7           5
     * 2 1 3    3       4
     * 2 2 4    4       5
     * 2 3 5    5       6
     * 2 4 6    6       7
     * 2 5 7    0       1           4
     * 3 1 4    4       5
     * 3 2 5    5       6
     * 3 3 6    6       7
     * 3 4 7    0       1
     * 3 5 8    1       2
     * 4 1 5    5       6
     * 4 2 6    6       7
     * 4 3 7    0       1
     * 4 4 8    1       2
     * 4 5 9    2       3
     * 5 1 6    6       7
     * 5 2 7    0       1
     * 5 3 8    1       2
     * 5 4 9    2       3           2
     * 5 5 10   3       4
     * <p>
     * The problem with this approach is because the generated numbers will not be random.
     *
     * @return random number in the range 1 to 7.
     */
    public int random1To7() {
        int[][] values = {
                {1, 2, 3, 4, 5},
                {6, 7, 1, 2, 3},
                {4, 5, 6, 7, 1},
                {2, 3, 4, 5, 6},
                {7, 0, 0, 0, 0}};

        int result = 0;

        while (result == 0) {
            result = values[random1To5() - 1][random1To5() - 1];
        }

        return result;
    }

}
