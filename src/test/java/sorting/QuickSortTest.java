package sorting;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {

    @Test
    public void testSort() {
        // set up
        int[] array = {1, 5, 2, 0, 4, 3};

        // SUT
        QuickSort quickSort = new QuickSort();
        quickSort.sort(array);

        // assert
        Assert.assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5}, array);
    }
}
