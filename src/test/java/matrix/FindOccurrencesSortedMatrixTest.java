package matrix;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FindOccurrencesSortedMatrixTest {

    @Test
    public void testFind() {
        // set up
        int[][] matrix = {
                { -4, -3, -1,  3,  5 },
                { -3, -2,  2,  4,  6 },
                { -1,  1,  3,  5,  8 },
                {  3,  4,  7,  8,  9 }
        };

        // SUT
        FindOccurrencesSortedMatrix finder = new FindOccurrencesSortedMatrix(matrix);
        List<Cell> occurrences = finder.find(3);

        // asserts
        Assert.assertEquals(new ArrayList<>(), occurrences);

    }
}
