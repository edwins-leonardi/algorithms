package queuesdequeues;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int length() {
        return items.length;
    }

    public void enqueue(Item item) {
        if (size >= items.length)
            resize(size * 2);
        items[size++] = item;
    }

    public Item dequeue() {
        if (isEmpty())
            return null;
        Item item = null;
        while (item == null) {
            int randomIndex = StdRandom.uniform(items.length);
            item = items[randomIndex];
            if (item != null)
                items[randomIndex] = null;
        }
        size--;
        if (size < (items.length / 4))
            resize(items.length / 2);
        return item;
    }

    public Item sample() {
        Item item = null;
        while (item == null) {
            int randomIndex = StdRandom.uniform(items.length);
            item = items[randomIndex];
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<Character> linkedList = new RandomizedQueue<Character>();
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
            StdOut.println(" - " + linkedList.length());
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
    }

    class RandomizedIterator implements Iterator<Item> {

        private int pointer;
        private int count;

        
        public boolean hasNext() {
            return count < size;
        }

        
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = null;
            for (; pointer < items.length; pointer++) {
                item = items[pointer++];
                if (item != null) {
                    count++;
                    return item;
                }
            }
            throw new NoSuchElementException();
        }

        
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}