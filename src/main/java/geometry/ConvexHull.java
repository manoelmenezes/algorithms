package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class ConvexHull {

    public List<Point> convexHull(Point[] points) {

        int min = 0;

        for (int i = 1; i < points.length; i++) {
            if (points[i].getY() < points[min].getY()
                    || (points[i].getY() == points[min].getY()
                            && points[i].getX() < points[min].getX() ) ) {
                min = i;
            }
        }

        swap(0, min, points);

        Arrays.sort(points, 1, points.length, comparator(points[0]));

        int m = 1;
        for (int i = 1; i < points.length; i++) {

            while (i < points.length - 1 && Point.orientation(points[0], points[i], points[i + 1]) == Point.Orientation.COLINEAR ) {
                i++;
            }

            points[m] = points[i];
            m++;
        }

        if (m < 3) {
            return new ArrayList<>();
        }

        Stack<Point> stack = new Stack<>();
        stack.push(points[0]);
        stack.push(points[1]);
        stack.push(points[2]);

        for (int i = 3; i < m; i++) {

            while (Point.orientation(nextToTop(stack), stack.peek(), points[i]) != Point.Orientation.COUNTERCLOCKWISE ){
                stack.pop();
            }

            stack.push(points[i]);
        }

        return new ArrayList<>(stack);

    }

    private Point nextToTop(Stack<Point> stack) {
        Point p = stack.pop();
        Point result = stack.peek();
        stack.push(p);

        return result;
    }

    private int distance(Point p, Point q) {
        int yDiff = q.getY() - p.getY();
        int xDiff = q.getX() - p.getX();

        return yDiff * yDiff + xDiff * xDiff;
    }

    private Comparator<Point> comparator(Point p) {
        return (q, r) -> {
            Point.Orientation o = Point.orientation(p, q, r);

            if (o == Point.Orientation.COLINEAR) {
                return distance(p, r) >= distance(p, q) ? -1 : 1;
            }

            return o == Point.Orientation.COUNTERCLOCKWISE ? -1 : 1;
        };
    }

    private void swap(int i, int j, Point[] points) {
        Point tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }

    public static void main(String[] args) {
        Point points[] = {new Point(0, 3), new Point(1, 1), new Point(2, 2), new Point(4, 4),
                new Point(0, 0), new Point(1, 2), new Point(3, 1), new Point(3, 3)};

        ConvexHull convexHull = new ConvexHull();

        List<Point> result = convexHull.convexHull(points);

        System.out.println(result);
    }

}
