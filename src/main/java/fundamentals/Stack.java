package fundamentals;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private Node<Item> last; // end of stack
    private int n; // number of elements on stack

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
    public Stack() {
        last = null;
        n = 0;
    }

    public void push(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>(item);
        last.next = oldlast;
        n++;
    }

    public Item pop() {
        if (last == null)
            throw new NoSuchElementException("Queue underflow");
        Item item = last.item;
        last = last.next;
        n--;
        return item;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueInterator(last);
    }

    class QueueInterator implements Iterator<Item> {

        private Node<Item> current;

        public QueueInterator(Node<Item> last) {
            current = last;
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

        Stack<String> q = new Stack<>();

        System.out.println(q.isEmpty());
        System.out.println(q.size());
        q.push("abacate");
        q.push("berinja");
        q.push("carambola");
        System.out.println("------------");
        for (String s : q) {
            System.out.print(s + "\t");
        }
        System.out.println("------------");
        System.out.println(q.isEmpty());
        System.out.println(q.size());
        System.out.println(q.pop());
        System.out.println(q.pop());
        System.out.println(q.size());
        q.push("morango");
        System.out.println(q.size());
        System.out.println(q.pop());
        System.out.println(q.pop());
    }
}