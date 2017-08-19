package datastructures;

import edu.princeton.cs.algs4.StdOut;

public class ArrayStack<Item> implements Stack<Item> {
    private int size;
    private Item[] items = (Item[]) new Object[10];

    public void push(Item item) {
        if (size < items.length) {
            items[size++] = item;
        }
    }

    
    public Item pop() {
        if (size < 0) {
            return null;
        }
        Item item = items[--size];
        items[size] = null;
        return item;
    }

    
    public boolean isEmpty() {
        return size == 0;
    }

    
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArrayStack<Character> arrayList = new ArrayStack<Character>();
        arrayList.push('1');
        arrayList.push('2');
        arrayList.push('3');
        arrayList.push('4');
        arrayList.push('5');
        arrayList.push('6');
        arrayList.push('7');
        arrayList.push('8');
        StdOut.printf("Size is = %s\n", arrayList.size());
        while (!arrayList.isEmpty()) {
            StdOut.print(arrayList.pop());
        }
    }

}
