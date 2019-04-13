package dp;

import java.util.Arrays;
import java.util.HashMap;

public class PartitionProblem {

    private int[] array;

    private int n;

    public PartitionProblem(int[] array) {
        this.array = array;
        this.n = array.length;
    }

    public boolean twoSetsEqualSum() {
        int sum = Arrays.stream(array).sum();
        return ((sum & 1) == 0) && doTwoSetsEqualSum(n - 1, 0, 0, new HashMap<>());
    }

    private boolean doTwoSetsEqualSum(int index, int sum1, int sum2, HashMap<String, Boolean> results) {

        if (index < 0) {
            return sum1 == sum2;
        }

        String key = index + "-" + sum1;

        if (!results.containsKey(key)) {
            boolean equalSum1 = doTwoSetsEqualSum(index - 1, sum1 + array[index], sum2, results);

            boolean equalSum2 = doTwoSetsEqualSum(index - 1, sum1, sum2 + array[index], results);

            results.put(key, equalSum1 || equalSum2);
        }

        return results.get(key);

    }

    public static void main(String[] args) {
        int[] array = {3, 1, 1, 2, 2, 1};

        PartitionProblem partitionProblem = new PartitionProblem(array);

        boolean equalSum = partitionProblem.twoSetsEqualSum();

        System.out.println("Equal sum: " + equalSum);
    }


}
