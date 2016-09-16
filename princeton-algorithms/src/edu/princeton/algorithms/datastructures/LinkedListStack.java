package edu.princeton.algorithms.datastructures;

import edu.princeton.cs.algs4.StdOut;

public class LinkedListStack<Item> implements Stack<Item> {

    private Node first;
    private int size;

    @Override
    public void push(Item item) {
        Node oldFirst = first;
        first = new Node(item, oldFirst);
        size++;
    }

    @Override
    public Item pop() {
        if (first == null)
            return null;
        Item item = first.self;
        first = first.next;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    class Node {
        Node next;
        Item self;

        Node(Item self, Node next) {
            this.self = self;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LinkedListStack<Character> linkedList = new LinkedListStack<Character>();
        linkedList.push('1');
        linkedList.push('2');
        linkedList.push('3');
        linkedList.push('4');
        linkedList.push('5');
        linkedList.push('6');
        linkedList.push('7');
        linkedList.push('8');
        StdOut.printf("Size is = %s\n", linkedList.size());
        while (!linkedList.isEmpty()) {
            StdOut.print(linkedList.pop());
        }
    }
}
