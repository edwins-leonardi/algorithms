package collinear;
public class BruteCollinearPoints {

    private int numberOfSegments;
    private Segment[] segments;
    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();
        segments = new Segment[points.length / 2];
        Point[] pointsClone = points.clone();

        for (int i = 0; i < pointsClone.length; i++) {
            checkNull(i, pointsClone);
            Point first = pointsClone[i];
            for (int j = i + 1; j < pointsClone.length; j++) {
                checkNull(j, pointsClone);
                checkRepeated(pointsClone[i], pointsClone[j]);
                double slope = first.slopeTo(pointsClone[j]);
                int matches = 1;
                Point smallest = getSmallerPoint(first, pointsClone[j]);
                Point greatest = getGreaterPoint(first, pointsClone[j]);
                for (int k = j + 1; k < pointsClone.length; k++) {
                    if (skip(i, k, pointsClone, slope))
                        continue;
                    matches++;
                    smallest = getSmallerPoint(smallest, pointsClone[k]);
                    greatest = getGreaterPoint(greatest, pointsClone[k]);
                    for (int l = k + 1; l < pointsClone.length; l++) {
                        if (skip(i, l, pointsClone, slope))
                            continue;
                        matches++;
                        smallest = getSmallerPoint(smallest, pointsClone[l]);
                        greatest = getGreaterPoint(greatest, pointsClone[l]);
                    }
                    if (matches >= 3
                            && !alreadyExists(new Segment(smallest, greatest)))
                        addSegment(smallest, greatest);
                    break;
                }
            }
        }
        lineSegments = new LineSegment[numberOfSegments];
        for (int i = 0; i < numberOfSegments; i++)
            lineSegments[i] = new LineSegment(segments[i].p, segments[i].q);
    }

    public int numberOfSegments() {
        return numberOfSegments;
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private boolean skip(int idx1, int idx2, Point[] points, double slope) {
        checkNull(idx2, points);
        checkRepeated(points[idx1], points[idx2]);
        return (points[idx1].slopeTo(points[idx2]) != slope);
    }

    private Point getGreaterPoint(Point p1, Point p2) {
        return p1.compareTo(p2) > 0 ? p1 : p2;
    }

    private Point getSmallerPoint(Point p1, Point p2) {
        return p1.compareTo(p2) < 0 ? p1 : p2;
    }

    private void checkNull(int n, Point[] points) {
        if (points[n] == null)
            throw new NullPointerException();
    }

    private void addSegment(Point p, Point q) {
        segments[numberOfSegments++] = new Segment(p, q);
    }

    private boolean alreadyExists(Segment another) {
        for (Segment segment : segments) {
            if (segment == null)
                break;
            double slope = segment.p.slopeTo(segment.q);
            double secondarySlope = another.p.slopeTo(another.q);
            if (slope == secondarySlope) {
                if (secondarySlope == Double.POSITIVE_INFINITY
                        && (another.p.slopeTo(segment.p) == secondarySlope || another.p
                                .slopeTo(segment.q) == secondarySlope))
                    return true;
                else if (secondarySlope == 0
                        && (another.p.slopeTo(segment.p) == secondarySlope || another.p
                                .slopeTo(segment.q) == secondarySlope))
                    return true;
                else if (secondarySlope != Double.POSITIVE_INFINITY
                        && secondarySlope != 0
                        && another.p.slopeTo(segment.q) == secondarySlope)
                    return true;
            }
        }
        return false;
    }

    private void checkRepeated(Point p, Point q) {
        if (p.compareTo(q) == 0)
            throw new IllegalArgumentException();
    }

    private class Segment {
        private Point p;
        private Point q;

        private Segment(Point p, Point q) {
            this.p = p;
            this.q = q;
        }
    }
}
