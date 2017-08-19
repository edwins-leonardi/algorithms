package edu.princeton.cs.algs4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayList<Item> implements Iterable<Item> {
    private Item[] a;         // array of items
    private int n;            // number of elements on bag

    /**
     * Initializes an empty bag.
     */
    public ResizingArrayList() {
        a = (Item[]) new Object[2];
        n = 0;
    }

    /**
     * Is this bag empty?
     * @return true if this bag is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in this bag.
     * @return the number of items in this bag
     */
    public int size() {
        return n;
    }

    // resize the underlying array holding the elements
    private void resize(int newSize) {
        Item[] resizedArray = (Item[]) new Object[newSize];
        int pointer = 0;
        for (Item item : a) {
            if (item != null)
                resizedArray[pointer++] = item;
        }
        a = resizedArray;
    }
    
    public Item get(int index){
    	if(index>n || index < 0)
    		throw new IndexOutOfBoundsException(String.format("Index: %s, Size: %s", index, n));
    	return a[index];
    }

    /**
     * Adds the item to this bag.
     * @param item the item to add to this bag
     */
    public void add(Item item) {
        if (n == a.length) resize(2*a.length);    // double size of array if necessary
        a[n++] = item;                            // add item
    }
    
    public void remove(int index) {
    	if(index>n || index < 0)
    		throw new IndexOutOfBoundsException(String.format("Index: %s, Size: %s", index, n));
        a[index] = null;                            // to avoid loitering
        n--;
        // shrink size of array if necessary
        if (n > 0 && n == a.length/4) {
        	System.out.println("new size :" + a.length/4);
        	resize(a.length/2); 
        }
    }



    /**
     * Returns an iterator that iterates over the items in the bag in arbitrary order.
     * @return an iterator that iterates over the items in the bag in arbitrary order
     */
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ArrayIterator implements Iterator<Item> {
    	 private int pointer;
         private int count;

         public boolean hasNext() {
             return count < n;
         }

         public Item next() {
             if (!hasNext())
                 throw new NoSuchElementException();
             Item item = null;
             for (; pointer < a.length; pointer++) {
                 item = a[pointer++];
                 if (item != null) {
                     count++;
                     return item;
                 }
             }
             throw new NoSuchElementException();
         }
    }
}
