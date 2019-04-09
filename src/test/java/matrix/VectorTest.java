package matrix;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VectorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyArray() {
        // SUT
        new Vector(new int[]{});
    }

    @Test
    public void testConstructorWithSuccess() {
        // set up
        int[] x = {1};

        // SUT
        Vector vector = new Vector(x);

        // assert
        assertEquals(1, vector.getN());
        assertTrue(Arrays.equals(x, vector.getX()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsUnitVectorWithNegativeParameter() {
        // set up
        int[] x = {1};
        Vector vector = new Vector(x);

        // SUT
        vector.isUnitVector(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsUnitVectorWithInvalidParameter() {
        // set up
        int[] x = {1};
        Vector vector = new Vector(x);

        // SUT
        vector.isUnitVector(x.length);
    }

    @Test
    public void testIsUnitVectorWithSuccess() {
        // set up
        int[] x = {1};
        Vector vectorX = new Vector(x);

        int[] y = {0, 0, 1};
        Vector vectorY = new Vector(y);

        // SUT
        boolean isUnitVectorX = vectorX.isUnitVector(0);
        boolean isUnitVectorY_0 = vectorY.isUnitVector(0);
        boolean isUnitVectorY_1 = vectorY.isUnitVector(1);
        boolean isUnitVectorY_2 = vectorY.isUnitVector(2);

        // assert
        assertTrue(isUnitVectorX);
        assertFalse(isUnitVectorY_0);
        assertFalse(isUnitVectorY_1);
        assertTrue(isUnitVectorY_2);
    }

}
