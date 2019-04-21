package array;

public class BinarySearch {

    public int search(int[] array, int value) {

        int start = 0;
        int end = array.length - 1;

        while (start <= end) {

            int middle = (start + end) / 2;

            if (array[middle] == value) {
                return middle;
            }

            if (array[middle] < value) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }

        }

        return -1;

    }

    public int searchRecursive(int[] array, int value) {
        return doSearchRecursive(array, value, 0, array.length - 1);
    }

    private int doSearchRecursive(int[] array, int value, int start, int end) {
        if (start <= end) {
            int middle = (start + end) / 2;

            if (array[middle] == value) {
                return middle;
            }

            if (array[middle] < value) {
                return doSearchRecursive(array, value,middle + 1, end);
            }

            return doSearchRecursive(array, value, start, middle - 1);
        }
        return -1;
    }
}
