import java.util.ArrayList;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

    private TreeSet<Point2D> points;

    public PointSET() {
        // construct an empty set of points
        points = new TreeSet<Point2D>();
    }

    public boolean isEmpty() {
        return points.isEmpty();
    }

    public int size() {
        // number of points in the set
        return points.size();
    }

    public void insert(Point2D p) {
        // add the point to the set (if it is not already in the set)
        points.add(p);
    }

    public boolean contains(Point2D p) {
        // does the set contain point p?
        return points.contains(p);
    }

    public void draw() {
        // draw all points to standard draw
        for (Point2D p : points)
            p.draw();
    }

    public Iterable<Point2D> range(RectHV rect) {
        ArrayList<Point2D> rangeList = new ArrayList<Point2D>();
        // all points that are inside the rectangle
        for (Point2D p : points)
            if (rect.contains(p))
                rangeList.add(p);

        return rangeList;
    }

    public Point2D nearest(Point2D p) {
        // a nearest neighbor in the set to point p; null if the set is empty
        Point2D nearest = null;
        double shortestDistance = Double.MAX_VALUE;
        for (Point2D neighbor : points) {
            if (neighbor != p && p.distanceTo(neighbor) < shortestDistance) {
                nearest = neighbor;
                shortestDistance = p.distanceTo(neighbor);
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        // unit testing of the methods (optional)
    }
}
