package bit;

import org.junit.Assert;
import org.junit.Test;

public class TurnOffBitTest {

    @Test
    public void testTurnOff() {
        Assert.assertEquals(Integer.valueOf("111011", 2).intValue(),
                TurnOffBit.turnOff(Integer.valueOf("111111", 2), 3));
    }
}
