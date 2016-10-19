import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTreeMain {

    public static void main(String[] args) {
        Point2D a = new Point2D(0.0, 0.0);
        Point2D b = new Point2D(0.1, 0.4);
        Point2D c = new Point2D(0.6, 0.5);
        RectHV r = new RectHV(0.4, 0.3, 0.8, 0.6);

        System.out.println(r.contains(a));
        System.out.println(r.distanceTo(a));
        System.out.println();
        System.out.println(r.contains(b));
        System.out.println(r.distanceTo(b));
        System.out.println();
        System.out.println(r.contains(c));
        System.out.println(r.distanceTo(c));
        System.out.println();
        a.draw();
        b.draw();
        c.draw();
        r.draw();
    }
}
