package compression.burrowswheeler;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {
    private static final int R = 256;

    private static class Node {
        private char c;
        private int code;
        private Node next;
        private Node prev;

        private Node(char c) {
            this.c = c;
        }
    }

    private static Node add(Node head, Node tail, char c) {
        if (tail == null) {
            tail = new Node(c);
        } else {
            Node oldTail = tail;
            tail = new Node(c);
            oldTail.next = tail;
            tail.prev = oldTail;
        }
        return tail;
    }

    private static Node getCode(Node head, char c) {
        int code = 0;
        Node node = head;
        while (node != null && node.c != c) {
            node = node.next;
            code++;
        }
        node.code = code;
        return node;
    }

    private static Node getChar(Node head, int code) {
        int i = 0;
        Node node = head;
        while (code != i) {
            node.code = i++;
            node = node.next;
        }
        return node;
    }

    private static Node moveToFront(Node head, Node node) {
        if (head == node)
            return node;
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        if (next != null)
            next.prev = prev;
        node.prev = null;
        head.prev = node;
        node.next = head;
        return node;
    }


    public static void encode() {
        Node tail = null, head = null;
        for (int c = 0; c < R; c++) {
            tail = add(head, tail, (char) c);
            if (head == null)
                head = tail;
        }

        while (!BinaryStdIn.isEmpty()) {
            char c = BinaryStdIn.readChar();
            Node node = getCode(head, c);
            int code = node.code;
            BinaryStdOut.write(code, 8);
            head = moveToFront(head, node);
        }
        BinaryStdOut.close();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        Node tail = null, head = null;
        for (int c = 0; c < R; c++) {
            tail = add(head, tail, (char) c);
            if (head == null)
                head = tail;
        }

        while (!BinaryStdIn.isEmpty()) {
            int i = BinaryStdIn.readInt(8);
            Node node = getChar(head, i);
            BinaryStdOut.write(node.c);
            head = moveToFront(head, node);
        }

        BinaryStdOut.close();
    }

    // if args[0] is "-", apply move-to-front encoding
    // if args[0] is "+", apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
