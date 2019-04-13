package dp;

import java.util.HashMap;
import java.util.Map;

public class MinimumSumPartition {

    private static int count = 0;

    private static int countRepetitive = 0;

    private int[] array;

    private int n;

    public MinimumSumPartition(int[] array) {
        this.array = array;
        this.n = array.length;
    }

    public int minimumSum() {
        return doMinimumSum(n - 1, 0, 0, new HashMap<>());
    }

    private int doMinimumSum(int index, int sum1, int sum2, Map<String, Integer> results) {
        count++;

        if (index < 0) {
            return Math.abs(sum1 - sum2);
        }

        String key = index + "-" + sum1;

        if (results.containsKey(key)) {
            countRepetitive++;
            return results.get(key);
        }

        int diff1 = doMinimumSum(index - 1, sum1 + array[index], sum2, results);

        int diff2 = doMinimumSum(index - 1, sum1, sum2 + array[index], results);

        int minDiff = Math.min(diff1, diff2);

        results.put(key, minDiff);

        return minDiff;
    }

    public static void main(String[] args) {
        int[] array = {10, 20, 15, 5, 25, 18, 27, 19, 18, 19, 20, 12, 18, 15, 90, 91, 1, 1, 1, 1, 1, 1, 1, 1, 1};

        MinimumSumPartition minimumSumPartition = new MinimumSumPartition(array);

        int minimumSum = minimumSumPartition.minimumSum();

        System.out.println("Minimum sum: " + minimumSum);

        System.out.println("count: " + count);

        System.out.println("count repetitive: " + countRepetitive);
    }

}
