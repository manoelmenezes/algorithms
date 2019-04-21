package sorting;

import java.util.Stack;

public class QuickSort {

    public static final class Range {
        int start;
        int end;
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public void sort(int[] array) {

        Stack<Range> stack = new Stack<>();

        stack.push(new Range(0, array.length - 1));

        while (!stack.isEmpty()) {
            Range range = stack.pop();

            int pivot = partition(array, range.start, range.end);

            if (pivot + 1 < range.end) {
                stack.push(new Range(pivot + 1, range.end));
            }

            if (pivot - 1 > range.start) {
                stack.push(new Range(range.start, pivot - 1));
            }
        }

    }

    private int partition(int[] array, int start, int end) {
        int pivot = start;

        int i = start;
        int j = end;

        while (i <= j) {
            if (array[i] <= array[pivot]) {
                i++;
            } else {
               swap(array, i, j);
               j--;
            }
        }

        swap(array, pivot, i - 1);

        return i -1;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
