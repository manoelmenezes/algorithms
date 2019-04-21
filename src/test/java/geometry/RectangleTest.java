package geometry;

import org.junit.Assert;
import org.junit.Test;

public class RectangleTest {

    @Test
    public void testCollide() {
        // set up
        Point l1 = new Point(0, 10);
        Point r1 = new Point(10, 0);
        Point l2 = new Point(5, 5);
        Point r2 = new Point(15, 0);
        Rectangle first = new Rectangle(l1, r1);
        Rectangle second = new Rectangle(l2, r2);

        // SUT
        ColisionDetection colisionDetection = new ColisionDetection();

        boolean collide = colisionDetection.collide(first, second);

        // assert
        Assert.assertTrue(collide);
    }
}
