import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;
    private int head;
    private int tail;
    private int hole;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        head = 0;
        hole = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new NullPointerException();
        System.out.println("enqueue " + item);
        if (size == items.length)
            resize(size * 2);
        items[tail++] = item; // add item
        if (tail == items.length)
            tail = hole; // wrap-around
        size++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int idx = head;
        if (tail > head)
            idx = head++ % (tail + 1);
        Item item = items[idx];
        items[idx] = null;
        size--;
        System.out.println("idx =" + idx + " head =" + (head - 1) + " tail =" + tail + " size " + size + " length "
                + items.length);
        print();
        if (size > 0 && size == (items.length / 4))
            resize(items.length / 2);
        System.out.println("deque " + item);
        return item;
    }

    private void print() {
        System.out.print("array = ");
        for (Item it : items)
            System.out.print(it + ",");
        System.out.println("");
    }

    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int idx = head++;
        if (tail > head)
            idx = head % (tail + 1);
        return items[idx];
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> linkedList = new RandomizedQueue<>();

        int k = 0;
        linkedList.enqueue("" + k++);
        linkedList.dequeue();
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.dequeue();
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.dequeue();
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.enqueue("" + k++);
        linkedList.dequeue();

        /*
         * for (int i = 0; i < 50; i++) { int p = StdRandom.uniform(10); if (p <
         * 7) { linkedList.enqueue("" + k++); StdOut.println("enque "); } if (p
         * == 7) StdOut.println("deque " + linkedList.dequeue()); if (p == 8)
         * StdOut.println("isEmpty " + linkedList.isEmpty()); if (p == 9)
         * StdOut.println("isEmpty " + linkedList.isEmpty()); } StdOut.printf(
         * "Size is = %s\n", linkedList.size());
         */
        for (int i = 0; i < 20; i++) {
            StdOut.println("sample " + linkedList.dequeue());
        }

        for (String it : linkedList) {
            StdOut.println("s= " + it);
        }

        for (String it : linkedList) {
            StdOut.println("t= " + it);
        }
    }

    private void resize(int newSize) {
        Item[] resizedArray = (Item[]) new Object[newSize];
        int pointer = 0;
        for (Item item : items) {
            if (item != null)
                resizedArray[pointer++] = item;
        }
        items = resizedArray;
        head = StdRandom.uniform(size);
        hole = head;
        tail = size;
        System.out.println("resize tail = " + tail + " head = " + head);
    }

    private class RandomizedIterator implements Iterator<Item> {

        private int pointer;
        private int count;

        private RandomizedIterator() {
            if (head < tail)
                this.pointer = StdRandom.uniform(head, tail);
            else if (head > tail)
                this.pointer = StdRandom.uniform(tail, head);
            else
                this.pointer = head;
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            count++;
            System.out.println("tail " + tail + " head = " + head + " pointer =  " + pointer);
            int idx = pointer;
            if (tail > head)
                idx = pointer++ % (tail);

            if (items[idx] == null) {
                pointer = head;
                idx = pointer++;
            }
            return items[idx];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}