package geometry;

public class ColisionDetection {

    public boolean collide(final Rectangle r, final Rectangle s) {
        boolean condition1 = r.getTopLeft().getX() <= s.getTopLeft().getX()
                && s.getTopLeft().getX() <= r.getBottomRight().getX();

        boolean condition2 = r.getBottomRight().getY() <= s.getTopLeft().getY()
                && s.getTopLeft().getY() <= r.getTopLeft().getY();

        boolean condition3 = r.getTopLeft().getX() <= s.getBottomRight().getX()
                && s.getBottomRight().getX() <= r.getBottomRight().getX();

        boolean condition4 = r.getBottomRight().getY() <= s.getBottomRight().getY()
                && s.getBottomRight().getY() <= r.getTopLeft().getY();

        return (condition1 && condition2)
                || (condition3 && condition1)
                || (condition1 && condition4)
                || (condition3 || condition4);
    }
}
