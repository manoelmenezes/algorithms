package dp;

import org.checkerframework.checker.units.qual.K;

import java.util.HashMap;

public class KnapsackProblem {

    private static int countRepetitive = 0;
    private static int count = 0;

    private int[] values;

    private int[] weights;

    private int n;

    public KnapsackProblem(int[] values, int[] weights) {
        this.values = values;
        this.weights = weights;
        this.n = values.length;
    }

    public int maximizeValue(int weightLimit) {
        return doMaximizeValue(n - 1, weightLimit, 0, 0, new HashMap<>());
    }

    private int doMaximizeValue(int index, int weightLimit, int value, int weight, HashMap<String, Integer> results) {
        count++;

        if (index < 0) {
            return value;
        }

        String key = index + "-" + value + "-" + weight;

        if (!results.containsKey(key)) {
            int value1 = 0;
            if (weights[index] + weight <= weightLimit) {
                value1 = doMaximizeValue(index - 1, weightLimit, value + values[index], weight + weights[index], results);
            }

            int value2 = doMaximizeValue(index - 1, weightLimit, value, weight, results);

            results.put(key, Math.max(value1, value2));
        } else {
            countRepetitive++;
        }

        return results.get(key);
    }

    // Input:
    // Values (stored in array v)
    // Weights (stored in array w)
    // Number of distinct items (n)
    // Knapsack capacity (W)
    public int knapSack(int[] v, int[] w, int W)
    {
        // T[i][j] stores the maximum value of knapsack having weight
        // less than equal to j with only first i items considered.
        int[][] T = new int[v.length + 1][W + 1];

        // do for ith item
        for (int i = 1; i <= v.length; i++)
        {
            // consider all weights from 0 to maximum capacity W
            for (int j = 0; j <= W; j++)
            {
                // don't include ith element if j-w[i-1] is negative
                if (w[i-1] > j) {
                    T[i][j] = T[i-1][j];
                }
                else {
                    // find maximum value we get by excluding or including
                    // the i'th item
                    T[i][j] = Integer.max(T[i-1][j],
                            T[i-1][j-w[i-1]] + v[i-1]);
                }
            }
        }

        // return maximum value
        return T[v.length][W];
    }

    public static void main(String[] args) {
        int[] values = {20, 5, 10, 40, 15, 25, 1, 10, 5, 1, 3, 6, 7, 11, 15, 30, 38, 1, 2, 3, 9, 18, 11, 10, 19, 14, 13, 12};
        int[] weights = {1, 2, 3, 8, 7, 4, 1, 2, 3, 8, 7, 4, 1, 2, 3, 8, 7, 4, 1, 2, 3, 8, 7, 4, 2, 1, 7, 3};

        KnapsackProblem knapsackProblem = new KnapsackProblem(values, weights);

        int maxValue = knapsackProblem.maximizeValue(10);

        System.out.println("Max value: " + maxValue);
        System.out.println("Repetitive: " + countRepetitive);
        System.out.println("Count: " + count);

        maxValue = knapsackProblem.knapSack(values, weights, 10);

        System.out.println("Max value " + maxValue);
    }
}
