package sorting;

import com.google.common.base.Preconditions;

import java.util.Arrays;

public class RadixSort {

    private static final int BASE = 10;

    private static final int DIGITS = 4;

    private static final int BITS_PER_DIGIT = 8;

    private static final int BYTE = Integer.valueOf("11111111", 2);

    // O(n)
    private static int findMax(int[] input) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }

        return max;
    }

    // O(1)
    private static int getNumDigits(int max) {
        int digit = 1;
        while (max / 10 > 0) {
            digit++;
            max = max / 10;
        }

        return digit;

    }

    // O(n + BASE)
    private static void countingSort(int[] input, int digit) {

        int[] count = count(input, digit);

        int[] acc = new int[BASE];
        acc[0] = count[0];
        for (int i = 1; i < BASE; i++) {
            acc[i] = count[i] + acc[i - 1];
        }

        int[] result = new int[input.length];

        for (int i = input.length - 1; i >= 0; i--) {
            result[--acc[ digitAt(input[i], digit) ]] = input[i];
        }

        for (int i = 0; i < input.length; i++) {
            input[i] = result[i];
        }

    }

    // O(n)
    private static int[] count(int[] input, int digit) {
        int[] count = new int[BASE];

        for (int i = 0; i < input.length; i++) {
            count[ digitAt(input[i], digit) ]++;
        }

        return count;
    }

    // O(1)
    private static int digitAt(int value, int position) {
        int digit = 0;

        if (Math.pow(BASE, position) > value) {
            return digit;
        }

        while (value > 0 && position >= 0) {
            digit = value % BASE;
            value = (value - digit) / BASE;
            position--;
        }

        return digit;
    }

    public static void sort(int[] input) {
        // O(n)
        int max = findMax(input);

        // O(1)
        int digits = getNumDigits(max);

        // O(maxDigits * (n + BASE))
        for (int i = 0; i < digits; i++) {
            countingSort(input, i);
        }

    }

    public static int getDigit(int value, int digit) {
        Preconditions.checkArgument(digit >= 1 && digit <= DIGITS, String.format("Digit must be in range [1, %d]", digit));

        return (value >> (BITS_PER_DIGIT * (digit - 1))) & BYTE;
    }

    public static void main(String[] args) {
        int[] input = {170, 45, 75, 90, 802, 24, 2, 66};

        sort(input);

        System.out.println(Arrays.toString(input));

        for (int i = 0; i < input.length; i++) {
            System.out.println(String.format("Bits of %d: %s", input[i], Integer.toBinaryString(input[i])));
            for (int d = 1; d <= DIGITS; d++) {
                System.out.println(String.format("Digit %d: %d", d, getDigit(input[i], d)));
            }
        }

    }

}
