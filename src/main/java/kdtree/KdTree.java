package kdtree;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {

    private Node root;
    private final boolean VERTICAL = true;

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size(root);
    }

    public void insert(Point2D p) {
        root = insert(root, p, root);
    }

    public boolean contains(Point2D key) {
        if (key == null)
            throw new NullPointerException("argument to contains() is null");
        return get(key) != null;
    }

    public void draw() {
        Queue<Node> nodes = new Queue<Node>();
        inorder(root, nodes);
        for (Node n : nodes) {
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(StdDraw.BLACK);
            n.key.draw();
            if (n.line == VERTICAL) {
                StdDraw.setPenRadius(0.001);
                StdDraw.setPenColor(StdDraw.RED);

                RectHV r = new RectHV(n.leftRect.xmax(), n.rightRect.ymin(),
                        n.rightRect.xmin(), n.rightRect.ymax());
                r.draw();
            } else {
                StdDraw.setPenRadius(0.001);
                StdDraw.setPenColor(StdDraw.BLUE);

                RectHV r = new RectHV(n.leftRect.xmin(), n.leftRect.ymax(),
                        n.leftRect.xmax(), n.rightRect.ymin());
                r.draw();

            }
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> points = new Queue<Point2D>();
        inorderWithinRect(root, points, rect);
        return points;
    }

    public Point2D nearest(Point2D target) {
        if (root == null)
            return null;
        Point2D nearest = findNearest(root, root.key, target);
        return nearest;
    }

    private Point2D findNearest(Node n, Point2D nearest, Point2D target) {
        if (n == null)
            return nearest;

        if (n.key.distanceTo(target) < nearest.distanceTo(target))
            nearest = n.key;

        if (pointLeansLeft(n, target)) {
            nearest = findNearest(n.left, nearest, target);
            if (n.right != null && canBeReached(n.rightRect, target, nearest))
                nearest = findNearest(n.right, nearest, target);
        } else {
            nearest = findNearest(n.right, nearest, target);
            if (n.left != null && canBeReached(n.leftRect, target, nearest))
                nearest = findNearest(n.left, nearest, target);
        }

        return nearest;
    }

    private Node insert(Node node, Point2D point, Node parent) {
        if (node == null) {
            RectHV leftRect = getLeftRect(parent, point);
            RectHV rightRect = getRightRect(parent, point);
            if (parent == null)
                return new Node(point, VERTICAL, 1, leftRect, rightRect);
            else
                return new Node(point, !parent.line, 1, leftRect, rightRect);
        }
        if (node.key.equals(point))
            return node;
        int cmp = comparePoints(point, node);
        if (cmp < 0) {
            node.left = insert(node.left, point, node);
        } else {
            node.right = insert(node.right, point, node);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /*
     * private Double calculateMinimumDistance(Node node, Node parent, Point2D
     * target) {
     * 
     * double maxY = (parent == null) ? 1d : Math.max(parent.key.y(),
     * node.key.y()); double maxX = (parent == null) ? 1d :
     * Math.max(parent.key.x(), node.key.x()); if (node.line == VERTICAL) return
     * new Point2D(node.key.x(), Math.min(target.y(), maxY))
     * .distanceTo(target); else return new Point2D(Math.min(target.x(), maxX),
     * node.key.y()) .distanceTo(target); }
     */
    // private boolean canBeReached(Node node, Node parent, Point2D target,
    // Point2D nearest) {
    //
    // double maxY = (parent == null) ? 1d : Math.max(parent.key.y(),
    // node.key.y());
    // double maxX = (parent == null) ? 1d : Math.max(parent.key.x(),
    // node.key.x());
    // Point2D other = null;
    // if (node.line == VERTICAL)
    // other = new Point2D(node.key.x(), Math.min(target.y(), maxY));
    // else
    // other = new Point2D(Math.min(target.x(), maxX), node.key.y());
    //
    // return target.distanceSquaredTo(other) <= target
    // .distanceSquaredTo(nearest);
    //
    // }

    private RectHV getLeftRect(Node parent, Point2D point) {
        if (parent == null)
            return new RectHV(0, 0, point.x(), 1);
        else {
            RectHV parentRect = parent.rightRect;
            if (pointLeansLeft(parent, point))
                parentRect = parent.leftRect;

            if (parent.line == VERTICAL)
                return new RectHV(parentRect.xmin(), parentRect.ymin(),
                        parentRect.xmax(), point.y());
            else
                return new RectHV(parentRect.xmin(), parentRect.ymin(),
                        point.x(), parentRect.ymax());
        }
    }

    private RectHV getRightRect(Node parent, Point2D point) {
        if (parent == null)
            return new RectHV(point.x(), 0, 1, 1);
        else {
            RectHV parentRect = parent.rightRect;
            if (pointLeansLeft(parent, point))
                parentRect = parent.leftRect;

            if (parent.line == VERTICAL)
                return new RectHV(parentRect.xmin(), point.y(),
                        parentRect.xmax(), parentRect.ymax());
            else
                return new RectHV(point.x(), parentRect.ymin(),
                        parentRect.xmax(), parentRect.ymax());
        }
    }

    private boolean canBeReached(RectHV rect, Point2D target, Point2D nearest) {
        return rect.distanceSquaredTo(target) <= target
                .distanceSquaredTo(nearest);
    }

    private int comparePoints(Point2D pointToCompare, Node node) {
        if (node.line == VERTICAL)
            return Double.valueOf(pointToCompare.x()).compareTo(node.key.x());
        else
            return Double.valueOf(pointToCompare.y()).compareTo(node.key.y());
    }

    private void inorderWithinRect(Node node, Queue<Point2D> q, RectHV rect) {
        if (node == null)
            return;
        if (rect.contains(node.key))
            q.enqueue(node.key);

        if (rectIntersectsLine(node, rect)) {
            inorderWithinRect(node.left, q, rect);
            inorderWithinRect(node.right, q, rect);
        } else if (rectLeansLeft(node, rect)) {
            inorderWithinRect(node.left, q, rect);
        } else {
            inorderWithinRect(node.right, q, rect);
        }
    }

    private boolean rectIntersectsLine(Node node, RectHV rect) {
        if (node.line == VERTICAL)
            return rect.xmin() <= node.key.x() && rect.xmax() >= node.key.x();
        else
            return rect.ymin() <= node.key.y() && rect.ymax() >= node.key.y();
    }

    private boolean rectLeansLeft(Node node, RectHV rect) {
        if (node.line == VERTICAL)
            return Double.valueOf(rect.xmax()).compareTo(node.key.x()) < 0;
        else
            return Double.valueOf(rect.ymax()).compareTo(node.key.y()) < 0;
    }

    private boolean pointLeansLeft(Node node, Point2D target) {
        if (node.line == VERTICAL)
            return Double.valueOf(target.x()).compareTo(node.key.x()) < 0;
        else
            return Double.valueOf(target.y()).compareTo(node.key.y()) < 0;
    }

    private void inorder(Node x, Queue<Node> q) {
        if (x == null)
            return;
        inorder(x.left, q);
        q.enqueue(x);
        inorder(x.right, q);
    }

    private Point2D get(Point2D key) {
        Node node = root;
        while (node != null) {
            if (node.key.equals(key))
                return key;
            int cmp = comparePoints(key, node);
            if (cmp < 0)
                node = node.left;
            else
                node = node.right;
        }
        return null;
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    private class Node {
        private Point2D key; // key
        private Node left, right; // links to left and right subtrees
        private boolean line; // color of parent link
        private int size; // subtree count
        private RectHV leftRect;
        private RectHV rightRect;

        public Node(Point2D key, boolean line, int size, RectHV leftRect,
                RectHV rightRect) {
            this.key = key;
            this.line = line;
            this.size = size;
            this.leftRect = leftRect;
            this.rightRect = rightRect;
        }
    }
}
