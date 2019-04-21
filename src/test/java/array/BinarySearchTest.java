package array;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

    @Test
    public void testSearch() {
        // set up
        int[] array = {1, 2, 3, 4, 5};

        // SUT
        BinarySearch binarySearch = new BinarySearch();

        // assert
        Assert.assertEquals(0, binarySearch.search(array, 1));
        Assert.assertEquals(1, binarySearch.search(array, 2));
        Assert.assertEquals(2, binarySearch.search(array, 3));
        Assert.assertEquals(3, binarySearch.search(array, 4));
        Assert.assertEquals(-1, binarySearch.search(array, 0));
        Assert.assertEquals(4, binarySearch.search(array, 5));
    }

    @Test
    public void testSearchRecursive() {
        // set up
        int[] array = {1, 2, 3, 4, 5};

        // SUT
        BinarySearch binarySearch = new BinarySearch();

        // assert
        Assert.assertEquals(0, binarySearch.searchRecursive(array, 1));
        Assert.assertEquals(1, binarySearch.searchRecursive(array, 2));
        Assert.assertEquals(2, binarySearch.searchRecursive(array, 3));
        Assert.assertEquals(3, binarySearch.searchRecursive(array, 4));
        Assert.assertEquals(-1, binarySearch.searchRecursive(array, 0));
        Assert.assertEquals(4, binarySearch.searchRecursive(array, 5));
    }
}
