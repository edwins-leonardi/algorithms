package cache;

import java.util.HashMap;

public class LRUCache<T extends Comparable> {

    private int capacity;
    private HashMap<String, Node<T>> cache = new HashMap<>();
    private LinkedList<T> list = new LinkedList<>();
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public T get(String key) {
        if (cache.containsKey(key)) {
            Node<T> node = cache.get(key);
            list.moveToFront(node);
            return node.value;
        }
        return null;
    }

    public void add(String key, T value){
        if (cache.size() >= capacity) {
            Node<T> last = list.tail;
            list.removeLast();
            cache.remove(last.key);
            Node<T> node = list.add(key, value);
            cache.put(key, node);
        } else if (cache.containsKey(key)) {
            Node<T> node = cache.get(key);
            node.value = value;
            list.moveToFront(node);
        } else {
            Node<T> node = list.add(key, value);
            cache.put(key, node);
        }
    }

    private void print(){
        for (String key: cache.keySet()) {
            System.out.println(key + "\t" + cache.get(key).value);
        }
    }

    public static void main(String[] args) {
        LRUCache<Integer> lru = new LRUCache<>(3);
        lru.add("A", 1);
        lru.add("B", 2);
        lru.add("C", 3);
        System.out.println(lru.get("A"));
        lru.add("B",0);
        lru.add("D",4);
        lru.print();
    }
}

class LinkedList<T extends  Comparable> {
    protected Node<T> head;
    protected Node<T> tail;
    protected Node<T> add(String key, T item) {
        Node<T> newNode = new Node(key, item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        }
        return newNode;
    }
    protected void moveToFront(Node<T> node) {
        if (node == tail) {
            removeLast();
            add(node.key, node.value);
        } else if (node != head) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }
    protected void removeLast() {
        if (tail != head) {
            tail = tail.prev;
            tail.next = null;
        }
    }
}

class Node<T extends Comparable>{
    protected Node next;
    protected Node prev;
    protected T value;
    protected String key;
    protected Node(String key, T value){
        this.key = key;
        this.value = value;
    }
}


