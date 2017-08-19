package datastructures;

import edu.princeton.cs.algs4.StdOut;

public class LinkedListQueue<Item> implements Queue<Item> {

    private Node first;
    private Node last;
    private int size;

    
    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node(item, null);
        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
        size++;
    }

    
    public Item dequeue() {
        if (first == null)
            return null;
        Item item = first.self;
        first = first.next;
        size--;
        if (isEmpty())
            last = null;
        return item;
    }

    
    public boolean isEmpty() {
        return first == null;
    }

    
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        LinkedListQueue<Character> linkedList = new LinkedListQueue<Character>();
        linkedList.enqueue('1');
        linkedList.enqueue('2');
        linkedList.enqueue('3');
        linkedList.enqueue('4');
        linkedList.enqueue('5');
        linkedList.enqueue('6');
        linkedList.enqueue('7');
        linkedList.enqueue('8');
        StdOut.printf("Size is = %s\n", linkedList.size());
        while (!linkedList.isEmpty()) {
            StdOut.print(linkedList.dequeue());
        }
    }

    class Node {
        Node next;
        Item self;

        Node(Item self, Node next) {
            this.self = self;
            this.next = next;
        }
    }
}
