package bit;

import org.junit.Assert;
import org.junit.Test;

public class UnsetRightMostSetBitTest {

    @Test
    public void testUnsetRightMost() {
        Assert.assertEquals(16, UnsetRightMostSetBit.unsetRightMost1(20));
        Assert.assertEquals(0, UnsetRightMostSetBit.unsetRightMost1(0));
    }
}
