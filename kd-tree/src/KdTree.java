import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

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
        Queue<Point2D> points = new Queue<Point2D>();
        inorder(root, points);
        for (Point2D p : points)
            p.draw();
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
            nearest = findNearest(n.right, nearest, target);

            /*
             * if (n.right != null && calculateMinimumDistance(n.right, n,
             * target) < nearest .distanceTo(target)) nearest =
             * findNearest(n.right, nearest, target); else if (n.right != null)
             * { if (calculateMinimumDistance(n.right, n, target) < 0 ||
             * nearest.distanceTo(target) < 0) { System.out.println("peeeee"); }
             * }
             */} else {
            nearest = findNearest(n.right, nearest, target);
            nearest = findNearest(n.left, nearest, target);
            /*
             * if (n.left != null && calculateMinimumDistance(n.left, n, target)
             * < nearest .distanceTo(target)) nearest = findNearest(n.left,
             * nearest, target); else if (n.left != null) { if
             * (calculateMinimumDistance(n.left, n, target) < 0 ||
             * nearest.distanceTo(target) < 0) { System.out.println("peeeee"); }
             * }
             */}

        return nearest;
    }

    private Node insert(Node node, Point2D key, Node parent) {
        if (node == null) {
            if (parent == null)
                return new Node(key, VERTICAL, 1);
            else
                return new Node(key, !parent.line, 1);
        }
        if (node.key.equals(key))
            return node;
        int cmp = comparePoints(key, node);
        if (cmp <= 0) {
            node.left = insert(node.left, key, parent);
        } else {
            node.right = insert(node.right, key, parent);
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Double calculateMinimumDistance(Node node, Node parent,
            Point2D target) {

        double maxY = (parent == null) ? 1d : Math.max(parent.key.y(),
                node.key.y());
        double maxX = (parent == null) ? 1d : Math.max(parent.key.x(),
                node.key.x());
        if (node.line == VERTICAL)
            return new Point2D(node.key.x(), Math.min(target.y(), maxY))
                    .distanceTo(target);
        else
            return new Point2D(Math.min(target.x(), maxX), node.key.y())
                    .distanceTo(target);
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

    private void inorder(Node x, Queue<Point2D> q) {
        if (x == null)
            return;
        inorder(x.left, q);
        q.enqueue(x.key);
        inorder(x.right, q);
    }

    private Point2D get(Point2D key) {
        Node node = root;
        while (node != null) {
            if (node.key.equals(key))
                return key;
            int cmp = comparePoints(key, node);
            if (cmp <= 0)
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

        public Node(Point2D key, boolean line, int size) {
            this.key = key;
            this.line = line;
            this.size = size;
        }
    }
}
