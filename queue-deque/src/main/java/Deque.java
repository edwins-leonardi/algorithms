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
        else
            oldFirst.previous = first;
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
        size--;
        if (isEmpty())
            last = null;
        else
            first.previous = null;
        return item;
    }

    public Item removeLast() {
        if (last == null)
            throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        size--;
        if (isEmpty())
            first = null;
        else
            last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private void checkNull(Item item) {
        if (item == null)
            throw new NullPointerException();
    }

    private class Node {
        private Item item;
        private Node next;
        private Node previous;

        private Node(Item item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        private Node node;

        private DequeIterator(Node node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (node == null)
                throw new NoSuchElementException();
            Item item = node.item;
            node = node.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("1");
        deque.addLast("2");
        deque.addFirst("3");
        deque.addFirst("4");
        deque.addLast("5");
        System.out.println(deque.removeLast());
        System.out.println(deque.removeLast());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());

        for (String str : deque) {
            System.out.println(" s " + str);
        }
    }
}
