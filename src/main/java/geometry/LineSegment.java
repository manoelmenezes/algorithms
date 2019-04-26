package geometry;

public final class LineSegment {

    private final Point p;

    private final Point q;

    public LineSegment(final Point p, final Point q) {
        this.p = p;
        this.q = q;
    }

    public boolean intersect(LineSegment lineSegment) {
        Point.Orientation o1 = Point.orientation(p, q, lineSegment.p);
        Point.Orientation o2 = Point.orientation(p, q, lineSegment.q);
        Point.Orientation o3 = Point.orientation(lineSegment.p, lineSegment.q, p);
        Point.Orientation o4 = Point.orientation(lineSegment.p, lineSegment.q, q);

        if (o1 != o2 && o3 != o4) {
            return true;
        }

        if (o1 == Point.Orientation.COLINEAR && Point.onSegment(p, lineSegment.p, q)) {
            return true;
        }

        if (o2 == Point.Orientation.COLINEAR && Point.onSegment(p, lineSegment.q, q)) {
            return true;
        }

        if (o3 == Point.Orientation.COLINEAR && Point.onSegment(lineSegment.p, p, lineSegment.q)) {
            return true;
        }

        if (o4 == Point.Orientation.COLINEAR && Point.onSegment(lineSegment.p, q, lineSegment.q)) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LineSegment)) {
            return false;
        }

        LineSegment lineSegment = (LineSegment) obj;

        return (p.equals(lineSegment.p) && q.equals(lineSegment.q))
                || (p.equals(lineSegment.q) && q.equals(lineSegment.p));
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + p.hashCode();
        result = 31 * result + q.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return String.format("LineSegment(%s, %s)", p, q);
    }

    public static void main(String[] args) {
        Point p1 = new Point(1, 1);
        Point q1 = new Point(10, 1);
        Point p2 = new Point(1, 2);
        Point q2 = new Point(10, 2);

        LineSegment ls1 = new LineSegment(p1, q1);
        LineSegment ls2 = new LineSegment(p2, q2);

        if (ls1.intersect(ls2)) {
            System.out.println(ls1 + " intersects " + ls2);
        } else {
            System.out.println(ls1 + " does not intersect " + ls2);
        }

        p1 = new Point(10, 0);
        q1 = new Point(0, 10);
        p2 = new Point(0, 0);
        q2 = new Point(10, 10);

        ls1 = new LineSegment(p1, q1);
        ls2 = new LineSegment(p2, q2);

        if (ls1.intersect(ls2)) {
            System.out.println(ls1 + " intersects " + ls2);
        } else {
            System.out.println(ls1 + " does not intersect " + ls2);
        }

        p1 = new Point(-5, -5);
        q1 = new Point(0, 0);
        p2 = new Point(1, 1);
        q2 = new Point(10, 10);

        ls1 = new LineSegment(p1, q1);
        ls2 = new LineSegment(p2, q2);

        if (ls1.intersect(ls2)) {
            System.out.println(ls1 + " intersects " + ls2);
        } else {
            System.out.println(ls1 + " does not intersect " + ls2);
        }

    }
}
