package fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first; // beginning of queue
    private Node<Item> last; // end of queue
    private int n; // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        private Node(Item item) {
            this.item = item;
        }
    }

    /**
     * Initializes an empty queue.
     */
    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>(item);
        last.next = null;
        if (isEmpty())
            first = last;
        else
            oldlast.next = last;
        n++;
    }

    public Item dequeue() {
        if (first == null)
            throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty())
            last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueInterator(first);
    }

    class QueueInterator implements Iterator<Item> {

        private Node<Item> current;

        public QueueInterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {

        Queue<String> q = new Queue<>();

        System.out.println(q.isEmpty());
        System.out.println(q.size());
        q.enqueue("abacate");
        q.enqueue("berinja");
        q.enqueue("carambola");
        System.out.println("------------");
        for (String s : q) {
            System.out.print(s + "\t");
        }
        System.out.println("------------");
        System.out.println(q.isEmpty());
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.size());
        q.enqueue("morango");
        System.out.println(q.size());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
    }
}