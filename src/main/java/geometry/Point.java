package geometry;

public final class Point {

    public enum Orientation {
        COLINEAR,
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

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

    public static boolean onSegment(Point p1, Point p2, Point p3) {
        return p2.x <= Math.max(p1.x, p3.x)
                && p2.x >= Math.min(p1.x, p3.x)
                && p2.y <= Math.max(p1.y, p3.y)
                && p2.y >= Math.min(p1.y, p3.y);
    }

    public static Orientation orientation(Point p1, Point p2, Point p3) {
        int value = (p2.y - p1.y) * (p3.x - p2.x) - (p3.y - p2.y) * (p2.x - p1.x);

        if (value == 0) {
            return Orientation.COLINEAR;
        }

        if (value > 0) {
            return Orientation.CLOCKWISE;
        }

        return Orientation.COUNTERCLOCKWISE;
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

    public static void main(String[] args) {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(4, 4);
        Point p3 = new Point(1, 2);

        System.out.println(orientation(p1, p2, p3));
        System.out.println(orientation(p2, p1, p3));
        System.out.println(orientation(p2, p3, p1));
        System.out.println(orientation(p1, p3, p2));
        System.out.println(orientation(p3, p1, p2));
        System.out.println(orientation(p3, p2, p1));
    }
}