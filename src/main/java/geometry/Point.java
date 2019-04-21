package geometry;

public final class Point {

    private final int x;

    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Point)) {
            return false;
        }

        Point p = (Point) o;

        return p.x == x && p.y == y;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;

        return result;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
