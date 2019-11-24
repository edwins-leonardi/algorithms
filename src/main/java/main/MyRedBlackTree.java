package main;

import symboltables.RedBlackTree;

public class MyRedBlackTree<Key extends Comparable<Key>, Value> {
    class Node{
        private Node left;
        private Node right;
        private Key key;
        private Value value;
        private boolean color;
        private int size;
        Node(Key key, Value value, boolean color, int size){
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public void put(Key key, Value value) {
        this.root = put(this.root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null)
            return new Node(key, value, RED, 1);

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = put(node.left, key, value);
        else if (cmp > 0)
            node.right = put(node.right, key, value);
        else
            node.value = value;

        if (isRed(node.right) && !isRed(node.left))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);
        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // make a right-leaning link lean to the left
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    // flip the colors of a node and its two children
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no
    // such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x.value;
        }
        return null;
    }


    private int size(Node x) {
        if (x == null)
            return 0;
        return x.size;
    }

    private boolean isRed(Node x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    public void printTree(){
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;

        System.out.println("key = [" + node.key + "] = size = " + node.size);
        print(node.left);
        print(node.right);

    }

    public static void main(String[] args) {
        MyRedBlackTree<String, Integer> map = new MyRedBlackTree<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        map.put("E", 5);
        map.put("F", 6);
        map.put("G", 7);
        map.put("H", 8);
        map.put("I", 77);
        map.put("J", 9);
        map.put("K", 10);
        map.put("L", 11);
        map.put("M", 12);
        map.put("Z", 13);

        System.out.println(map.get("H"));

        map.printTree();
    }
}
