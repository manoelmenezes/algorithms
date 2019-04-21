package geometry;

public final class Rectangle {

    private final Point topLeft;

    private final Point bottomRight;

    public Rectangle(final Point topLeft, final Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Rectangle)) {
            return false;
        }

        Rectangle r = (Rectangle) obj;

        return r.topLeft.equals(topLeft) && r.bottomRight.equals(bottomRight);

    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + topLeft.hashCode();
        result = 31 * result + bottomRight.hashCode();

        return result;

    }

    @Override
    public String toString() {
        return String.format("Rectangle(%s, %s)", topLeft.toString(), bottomRight.toString());
    }
}
