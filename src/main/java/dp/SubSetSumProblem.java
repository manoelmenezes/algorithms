package dp;

import java.util.HashMap;
import java.util.Map;

public class SubSetSumProblem {

    private int[] array;

    private int n;

    public SubSetSumProblem(int[] array) {
        this.array = array;
        this.n = array.length;
    }

    public boolean isThereSubSetWithSum(int sum) {
        return doIsThereSubSetWithSum(n - 1, 0, sum, new HashMap<>());
    }

    private boolean doIsThereSubSetWithSum(int index,
                                           int currentSum,
                                           int sum,
                                           Map<String, Boolean> results) {

        if (index < 0) {
            return sum == currentSum;
        }

        String key = index + "-" + currentSum;

        if (!results.containsKey(key)) {
            boolean subset1 = doIsThereSubSetWithSum(index - 1, currentSum + array[index], sum, results);

            boolean subset2 = doIsThereSubSetWithSum(index - 1, currentSum, sum, results);

            results.put(key, subset1 || subset2);
        }

        return results.get(key);

    }

    public static void main(String[] args) {
        int[] array = {7, 3, 2, 5, 8};

        SubSetSumProblem subSetSumProblem = new SubSetSumProblem(array);

        boolean isThereSubset = subSetSumProblem.isThereSubSetWithSum(14);

        System.out.println(isThereSubset);
    }

}
