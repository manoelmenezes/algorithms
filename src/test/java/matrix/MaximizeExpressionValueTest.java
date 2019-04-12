package matrix;

import org.junit.Assert;
import org.junit.Test;

public class MaximizeExpressionValueTest {

    @Test
    public void testMaximize() {
        // set up
        int[] array = {3, 9, 10, 1, 30 , 40};

        // SUT
        MaximizeExpressionValue maximizeExpressionValue = new MaximizeExpressionValue(array);
        int max = maximizeExpressionValue.maximize();

        // assert
        Assert.assertEquals(46, max);
    }
}
