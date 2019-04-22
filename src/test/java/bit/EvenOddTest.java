package bit;

import org.junit.Assert;
import org.junit.Test;

public class EvenOddTest {

    @Test
    public void testIsEven() {
        Assert.assertTrue(EvenOdd.isEven(2));
        Assert.assertTrue(EvenOdd.isEven(4));
        Assert.assertTrue(EvenOdd.isEven(6));
        Assert.assertTrue(EvenOdd.isEven(8));
        Assert.assertFalse(EvenOdd.isEven(1));
        Assert.assertFalse(EvenOdd.isEven(3));
        Assert.assertFalse(EvenOdd.isEven(5));
        Assert.assertFalse(EvenOdd.isEven(-1));
        Assert.assertTrue(EvenOdd.isEven(-2));
    }
}
