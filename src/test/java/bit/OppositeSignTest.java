package bit;

import org.junit.Assert;
import org.junit.Test;

public class OppositeSignTest {

    @Test
    public void testHasOppositeSign() {
        Assert.assertTrue(OppositeSign.hasOppositeSign(-1, 1));
        Assert.assertTrue(OppositeSign.hasOppositeSign(1, -1));
        Assert.assertFalse(OppositeSign.hasOppositeSign(1, 1));
        Assert.assertTrue(OppositeSign.hasOppositeSign(-2, 3));

    }
}
