package edu.princeton.cs.algs4;

import java.util.Iterator;

public class LinkedList implements Iterable<Integer> {
    private Node first;
    private Node last;
    private int size;

    public Node add(Integer value) {
        Node newNode = new Node(value);
        addNode(newNode);
        return newNode;
    }

    public void addNode(Node newNode) {
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
//        while(newNode != null) {
//            size++;
//            newNode = newNode.next;
//        }
    }
    public void addAsFirst(Integer value) {
        if (first == null) {
            first = new Node(value);
            last = first;
        } else {
            Node oldFirst = first;
            first = new Node(value);
            first.next = oldFirst;
        }
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedList[");
        for (int v : this){
            sb.append(v);
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }

    public Node getFirst(){
        return first;
    }

    public int size() {
        return size;
    }

    public Node intersects(LinkedList otherLinkedList){
        int offset = 0;
        if (this.size != otherLinkedList.size) {
            offset = Math.abs(this.size - otherLinkedList.size);
        }
        LinkedList smaller = otherLinkedList;
        LinkedList bigger = this;
        if(otherLinkedList.size > bigger.size) {
            smaller = bigger;
            bigger = otherLinkedList;
        }
        Node n1 = bigger.first;
        Node n2 = smaller.first;
        for(int i = 0; i < offset; i++)
            n1 = n1.next;
        while(n1 != null && n2 != null) {
            if (n1 == n2)
                return n1;
            n1 = n1.next;
            n2 = n2.next;
        }
        return null;
    }

    private class LinkedListIterator implements Iterator<Integer> {

        Node current;

        LinkedListIterator(Node first) {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            Integer value = current.value;
            this.current = current.next;
            return value;
        }

        @Override
        public void remove() {
        }
    }

    @Override
    public Iterator iterator() {
        return new LinkedListIterator(first);
    }

    protected static class Node {
        Node next;
        Integer value;

        Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "nextIsNull=" + (next == null) +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        LinkedList ll1 = new LinkedList();
        ll1.add(1);
        ll1.add(2);
        ll1.add(3);
        ll1.add(4);
        ll1.add(5);
        Node n = ll1.add(6);
        ll1.add(7);
        ll1.add(8);
        ll1.add(9);
        LinkedList ll2 = new LinkedList();
        ll2.add(10);
        ll2.add(11);
        ll2.addNode(n);

        Node intersection = ll1.intersects(ll2);
        System.out.println(intersection);
        while(intersection != null) {
            System.out.println(intersection.value);
            intersection = intersection.next;
        }
    }
}
