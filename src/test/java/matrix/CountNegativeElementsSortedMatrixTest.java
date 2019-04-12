package matrix;

import org.junit.Assert;
import org.junit.Test;

public class CountNegativeElementsSortedMatrixTest {

    @Test
    public void testCountStartingFromTopLeft() {
        // set up

        int[][] matrix = {
                {-10, -5, -1, 1, 5},
                {-5, -4, -3, 2, 8},
                {-1, 0, 1, 2, 3},
                {0, 1, 2, 3, 4}
        };

        // SUT
        CountNegativeElementsSortedMatrix counter = new CountNegativeElementsSortedMatrix(matrix);
        int count = counter.countStartingFromTopLeft();

        // assert
        Assert.assertEquals(7, count);

    }

    @Test
    public void testCountStartingFromTopRight() {
        // set up

        int[][] matrix = {
                {-10, -5, -1, 1, 5},
                {-5, -4, -3, 2, 8},
                {-1, 0, 1, 2, 3},
                {0, 1, 2, 3, 4}
        };

        // SUT
        CountNegativeElementsSortedMatrix counter = new CountNegativeElementsSortedMatrix(matrix);
        int count = counter.countStartingFromTopRight();

        // assert
        Assert.assertEquals(7, count);
    }

}
