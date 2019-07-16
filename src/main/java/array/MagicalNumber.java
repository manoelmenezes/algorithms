package array;

import java.util.HashSet;
import java.util.Set;

public class MagicalNumber {

    public static final int N = 3;

    public static final int MAGICAL_NUMBER = 15;

    public static final Set<String> PERMUTATIONS;

    static {
        PERMUTATIONS = permutation("123456789");
    }

    public static Set<String> permutation(String str) {
        Set<String> result = new HashSet<>();

        if (str.isEmpty()) {
            result.add("");
            return result;
        }

        char c = str.charAt(0);

        Set<String> perms = permutation(str.substring(1));

        for (String p: perms) {
            result.add(c + p);
            for (int i = 1; i < p.length(); i++) {
                result.add(p.substring(0, i) + c + p.substring(i));
            }
            result.add(p + c);
        }

        return result;
    }

    public static boolean isMagic(String s) {

        int sum = 0;

        for (int i = 0; i < s.length(); i++) {

            sum += Integer.parseInt(s.charAt(i) + "");

            if ((i + 1) % N == 0) {

                if (sum != MAGICAL_NUMBER) {
                    return false;
                }

                sum = 0;

            }
        }

        for (int i = 0; i < N; i++) {
            sum = Integer.parseInt(s.charAt(i) + "")
                    + Integer.parseInt(s.charAt(i + N) + "")
                    + Integer.parseInt(s.charAt(i + 2 * N) + "");

            if (sum != MAGICAL_NUMBER) {
                return false;
            }
        }

        sum = Integer.parseInt(s.charAt(0) + "")
                + Integer.parseInt(s.charAt(4) + "")
                + Integer.parseInt(s.charAt(8) + "");

        if (sum != MAGICAL_NUMBER) {
            return false;
        }

        sum = Integer.parseInt(s.charAt(2) + "")
                + Integer.parseInt(s.charAt(4) + "")
                + Integer.parseInt(s.charAt(6) + "");

        if (sum != MAGICAL_NUMBER) {
            return false;
        }

        return true;
    }

    public static int solve(int[][] matrix) {

        int min = Integer.MAX_VALUE;

        for (String p: PERMUTATIONS) {

            if (isMagic(p)) {

                int sum = 0;

                for (int k = 0, i = 0; k < N; k++) {
                    for (int l = 0; l < N; l++) {
                        sum += Math.abs(Integer.parseInt(p.charAt(i++) + "") - matrix[k][l]);
                    }
                }

                if (sum < min) {
                    min = sum;
                }
            }

        }

        return min;
    }

    public static void main(String[] args) {
        int[][] m1 = {
                {5, 3, 4},
                {1, 5, 8},
                {6, 4, 2}
        };

        System.out.println(solve(m1));

        int[][] m2 = {
                {4, 9, 2},
                {3, 5, 7},
                {8, 1, 5}
        };
        System.out.println(solve(m2));

        int[][] m3 = {
                {4, 8, 2},
                {4, 5, 7},
                {6, 1, 6}
        };
        System.out.println(solve(m3));
    }
}
