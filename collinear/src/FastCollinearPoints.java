import java.util.HashSet;

import edu.princeton.cs.algs4.MergeX;

public class FastCollinearPoints {

    // private int numberOfSegments;
    private HashSet<Segment> segments = new HashSet<Segment>();

    private LineSegment[] lineSegments;

    // private HashSet<LineSegment> lineSegments = new HashSet<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        if (points == null)
            throw new NullPointerException();

        // segments = new Segment[points.length];
        Point[] pointsClone = points.clone();
        MergeX.sort(pointsClone);

        for (int i = 0; i < pointsClone.length - 1; i++) {
            checkNull(i, pointsClone);
            Point[] aux = new Point[pointsClone.length - 1];
            int idx = 0;
            for (int j = i + 1; j % pointsClone.length != i; j++) {
                aux[idx++] = pointsClone[j % pointsClone.length];
                checkRepeated(pointsClone[i], pointsClone[j
                        % pointsClone.length]);
            }
            if (aux.length >= 3) {
                MergeX.sort(aux, pointsClone[i].slopeOrder());
                extractFoundSegments(pointsClone[i], aux);
            }
        }

        // lineSegments = new LineSegment[numberOfSegments];
        // for (int i = 0; i < numberOfSegments; i++)
        // ineSegments[i] = new LineSegment(segments[i].p, segments[i].q);

        lineSegments = new LineSegment[segments.size()];
        int i = 0;
        for (Segment segment : segments)
            lineSegments[i++] = new LineSegment(segment.p, segment.q);

    }

    /*
     * public int numberOfSegments() { return numberOfSegments; }
     */

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.clone();
    }

    private void checkRepeated(Point p, Point q) {
        if (p.compareTo(q) == 0)
            throw new IllegalArgumentException();
    }

    private void extractFoundSegments(Point first, Point[] aux) {
        Point smallest = first;
        Point greatest = first;
        Double lastSlope = null;
        int count = 0;
        for (Point p : aux) {
            Double slope = p.slopeTo(first);
            if (!slope.equals(lastSlope)) {
                if (count >= 3)
                    addSegment(smallest, greatest);
                count = 0;
                lastSlope = slope;
                smallest = first;
                greatest = first;

            }
            count++;
            smallest = getSmallerPoint(smallest, p);
            greatest = getGreaterPoint(greatest, p);
        }
        if (count >= 3)
            addSegment(smallest, greatest);
    }

    /*
     * private void addSegment(Point p, Point q) { segments.
     * 
     * if (exists(new Segment(p, q))) return; if (numberOfSegments >=
     * segments.length) resize(segments.length * 2);
     * segments[numberOfSegments++] = new Segment(p, q); }
     */
    private void addSegment(Point p, Point q) {
        segments.add(new Segment(p, q));
    }

    /*
     * private void resize(int n) { Segment[] aux = new Segment[n]; for (int i =
     * 0; i < numberOfSegments; i++) { aux[i] = segments[i]; } segments = aux; }
     */

    /*
     * private boolean exists(Segment seg) { for (int i = 0; i <
     * segments.length; i++) { if (segments[i] == null) break; if
     * (segments[i].p.equals(seg.p) && segments[i].q.equals(seg.q)) return true;
     * }
     * 
     * return false; }
     */

    private void checkNull(int n, Point[] points) {
        if (points[n] == null)
            throw new NullPointerException();
    }

    private Point getGreaterPoint(Point p1, Point p2) {
        return p1.compareTo(p2) > 0 ? p1 : p2;
    }

    private Point getSmallerPoint(Point p1, Point p2) {
        return p1.compareTo(p2) < 0 ? p1 : p2;
    }

    private class Segment {
        private Point p;
        private Point q;

        private Segment(Point p, Point q) {
            this.p = p;
            this.q = q;
        }

        public String toString() {
            return p + " -> " + q;
        }

        @Override
        public boolean equals(Object obj) {
            Segment another = (Segment) obj;
            return this.p.equals(another.p) && this.q.equals(another.q);
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

    }
}