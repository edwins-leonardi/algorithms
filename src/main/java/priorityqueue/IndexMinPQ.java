package priorityqueue;

import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private Key[] keys;
    private int[] pq, qp;
    private int n;
    private int maxN;

    public IndexMinPQ(int initialCapacity) {
        this.maxN = initialCapacity;
        n = 0;
        keys = (Key[]) new Comparable[initialCapacity + 1];
        pq = new int[initialCapacity + 1];
        qp = new int[initialCapacity + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= maxN)
            throw new IllegalArgumentException();
        return qp[i] != -1;
    }

    public int size() {
        return n;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN)
            throw new IllegalArgumentException();
        if (contains(i))
            throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int minIndex() {
        if (n == 0)
            throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    public Key minKey() {
        if (n == 0)
            throw new NoSuchElementException("Priority queue underflow");
        return keys[pq[1]];
    }

    public int delMin() {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1; // delete
        keys[min] = null; // to help with garbage collection
        pq[n + 1] = -1; // not needed
        return min;
    }

    public Key keyOf(int i) {
        if (i < 0 || i >= maxN)
            throw new IllegalArgumentException();
        if (!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");
        else
            return keys[i];
    }

    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN)
            throw new IllegalArgumentException();
        if (!contains(i))
            throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1))
                j++;
            if (!greater(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public static void main(String[] args) {
        IndexMinPQ<String> pq = new IndexMinPQ<>(5);
        pq.insert(0, "D");
        pq.insert(1, "A");
        pq.insert(2, "B");
        pq.insert(3, "E");
        pq.insert(4, "C");

        System.out.println("key of 3? " + pq.keyOf(3));
        System.out.println("min key? " + pq.minKey());
        System.out.println("min index? " + pq.minIndex());
        System.out.println("del min ? " + pq.delMin());
        System.out.println("del min again? " + pq.delMin());

    }

}
