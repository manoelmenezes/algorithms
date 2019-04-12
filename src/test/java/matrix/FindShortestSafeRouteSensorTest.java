package matrix;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class FindShortestSafeRouteSensorTest {

    @Test
    public void test() {
        // set up
        int[][] matrix = {
                { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
                { 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
                { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
        };

        // SUT
        FindShortestSafeRouteSensor finder = new FindShortestSafeRouteSensor(matrix);
        List<Cell> path = finder.find();


        // assert
        Assert.assertEquals(12, path.size());
        Assert.assertEquals(List.of(
                new Cell(2, 0),
                new Cell(2, 1),
                new Cell(2, 2),
                new Cell(2, 3),
                new Cell(2, 4),
                new Cell(2, 5),
                new Cell(2, 6),
                new Cell(1, 6),
                new Cell(0, 6),
                new Cell(0, 7),
                new Cell(0, 8),
                new Cell(0, 9) ), path);
    }


}
