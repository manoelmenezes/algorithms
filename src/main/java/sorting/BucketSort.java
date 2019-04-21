package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class BucketSort {

    /**
     * Bucket sort assumes input is in the range [0,1)
     * @param input array with values in the range [0,1)
     */
    public void sort(final double[] input) {
        int n = input.length;

        List<List<Double>> buckets = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            buckets.add(new LinkedList<>());
        }

        for (int i = 0; i < n; i++) {
            buckets.get((int) (n * input[i])).add(input[i]);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort(buckets.get(i));
        }

        for (int i = 0, k = 0; i < n; i++) {
            if (!buckets.get(i).isEmpty()) {
                for (int j = 0; j < buckets.get(i).size(); j++) {
                    input[k++] = buckets.get(i).get(j);
                }
            }
        }
    }

    public int hash(int value, int[] code) {
        return (int)((double) value / code[0] * (code[1] - 1));
    }

    public int[] hash(int[] input) {
        int[] code = new int[2];
        int max = input[0];
        int n = input.length;
        for (int i = 1; i < n; i++) {
            if (input[i] > max) {
                max = input[i];
            }
        }
        code[0] = max;
        code[1] = (int) Math.sqrt(n);
        return code;
    }

    public static void main(String[] args) {
        int[] input = { 80, 50, 30, 10, 90, 60, 0, 70, 40, 20, 50 };

        BucketSort bucketSort = new BucketSort();

        int[] code = bucketSort.hash(input);

        System.out.println("Max: " + code[0]);
        System.out.println("Sqrt length: " + code[1]);

        for (int i = 0; i < input.length; i++) {
            System.out.println(String.format("hash of %d is %d", input[i], bucketSort.hash(input[i], code)));
        }

        double[] arr = {0.897, 0.565, 0.656, 0.1234, 0.665, 0.3434};

        bucketSort.sort(arr);

        System.out.println(Arrays.toString(arr));
    }

}
