package queuesdequeues;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        checkNull(item);
        Node oldFirst = first;
        first = new Node(item, oldFirst);
        if (isEmpty())
            last = first;
        size++;
    }

    public void addLast(Item item) {
        checkNull(item);
        Node oldLast = last;
        last = new Node(item, null);
        if (isEmpty())
            first = last;
        else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        size++;
    }

    public Item removeFirst() {
        if (first == null)
            throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        first.previous = null;
        size--;
        if (isEmpty())
            last = null;
        return item;
    }

    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        size--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private void checkNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

    class Node {
        Item item;
        Node next;
        Node previous;

        private Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    class DequeIterator implements Iterator<Item> {
        private Node node;

        private DequeIterator(Node node) {
            this.node = node;
        }

        
        public boolean hasNext() {
            return node != null;
        }

        
        public Item next() {
            if (node == null)
                throw new NoSuchElementException();
            Item item = node.item;
            node = node.next;
            return item;
        }

        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
