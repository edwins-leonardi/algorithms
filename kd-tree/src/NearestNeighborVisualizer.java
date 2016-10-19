/******************************************************************************
 *  Compilation:  javac NearestNeighborVisualizer.java
 *  Execution:    java NearestNeighborVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class NearestNeighborVisualizer {

    public static void main(String[] args) {
        String filename = args[0];
        In in = new In(filename);

        StdDraw.enableDoubleBuffering();

        // initialize the two data structures with point from standard input
        PointSET brute = null; // new PointSET();
        KdTree kdtree = new KdTree();
        int count = 0;
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            if (kdtree != null)
                kdtree.insert(p);
            count++;
            System.out.println(kdtree.size());
            if (brute != null)
                brute.insert(p);
        }

        Point2D q = new Point2D(0.29, 0.49);
        Point2D n = kdtree.nearest(q);
        System.out.println("nearest " + n);

        while (true) {

            // the location (x, y) of the mouse
            double x = StdDraw.mouseX();
            double y = StdDraw.mouseY();
            Point2D query = new Point2D(x, y);

            // draw all of the points
            StdDraw.clear();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            if (kdtree != null)
                kdtree.draw();
            if (brute != null)
                brute.draw();

            // draw in red the nearest neighbor (using brute-force algorithm)
            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.RED);
            if (brute != null)
                brute.nearest(query).draw();
            StdDraw.setPenRadius(0.02);

            // draw in blue the nearest neighbor (using kd-tree algorithm)
            StdDraw.setPenColor(StdDraw.BLUE);
            if (kdtree != null)
                kdtree.nearest(query).draw();

            /*
             * StdDraw.setPenColor(StdDraw.GREEN); StdDraw.setPenRadius(0.01);
             * RectHV r = new RectHV(0.5, 0.5, 0.7, 0.6); r.draw(); if (kdtree
             * != null) { for (Point2D p : kdtree.range(r)) p.draw(); }
             */
            StdDraw.show();
            StdDraw.pause(40);
        }
    }
}
