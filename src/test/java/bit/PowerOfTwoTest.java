package bit;

import org.junit.Assert;
import org.junit.Test;

public class PowerOfTwoTest {

    @Test
    public void testPowerOfTwo() {

        Assert.assertTrue(PowerOfTwo.isPowerOfTwo(1));
        Assert.assertTrue(PowerOfTwo.isPowerOfTwo(2));
        Assert.assertTrue(PowerOfTwo.isPowerOfTwo(4));
        Assert.assertTrue(PowerOfTwo.isPowerOfTwo(8));
        Assert.assertFalse(PowerOfTwo.isPowerOfTwo(0));
        Assert.assertFalse(PowerOfTwo.isPowerOfTwo(3));
        Assert.assertFalse(PowerOfTwo.isPowerOfTwo(5));
    }
}
