package bit;

import org.junit.Assert;
import org.junit.Test;

public class AddOneTest {

    @Test
    public void testTwoComplement() {
        Assert.assertEquals(-1, ~1 + 1);
        Assert.assertEquals(1, ~(-1) + 1);
        Assert.assertEquals(-2, ~2 + 1);
        Assert.assertEquals(2, ~(-2) + 1);
    }

    @Test
    public void testAddOne() {
        Assert.assertEquals(1, AddOne.addOne(0));
        Assert.assertEquals(2, AddOne.addOne(1));
        Assert.assertEquals(0, AddOne.addOne(-1));
        Assert.assertEquals(-1, AddOne.addOne(-2));
    }
}
