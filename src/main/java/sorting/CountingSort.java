package sorting;

import java.util.Arrays;

/**
 * When the range of values is know before hand, for instance:
 *
 * 1. Sort students by grade where the values are in the range [1,10].
 * 2. sort companies by stock percentage increase where the values are in the range [1,100].
 *
 * CountingSort is applied by creating a temp array with all possible values in the range,
 * where these values are the indexes of the array and each position stores how many indexes
 * values appear in the original input.
 *
 * For instance, in the grade example:
 *
 * Create a count array[1..10]
 *
 * count[input[i]]++;
 *
 *
 */
public class CountingSort {

    private static final int MAX = 10;

    public static void sort(int[] input) {
        int[] count = count(input);

        int[] acc = accumulate(count);

        int[] result = new int[input.length];

        for (int i = input.length - 1; i >= 0; i--) {
            int p = --acc[input[i]];
            result[p] = input[i];
        }

        for (int i = 0; i < input.length; i++) {
            input[i] = result[i];
        }


    }

    private static int[] count(int[] input) {

        int[] count = new int[MAX + 1];

        for (int i = 0; i < input.length; i++) {
            count[input[i]]++;
        }

        return count;

    }

    private static int[] accumulate(int[] count) {
        int[] acc = new int[count.length];

        acc[0] = count[0];

        for (int i = 1; i < count.length; i++) {
            acc[i] = count[i] + acc[i - 1];
        }

        return acc;
    }

    public static void main(String[] args) {
        int[] input = {1, 10, 5, 1, 2, 9, 8, 7, 10, 1, 2, 4, 5, 2, 3, 3, 10};

        sort(input);

        System.out.println(Arrays.toString(input));
    }

}
