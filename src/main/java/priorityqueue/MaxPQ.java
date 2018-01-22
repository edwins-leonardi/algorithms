package priorityqueue;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] items;
    private int N;

    public MaxPQ(int initialCapacity) {
        items = (Key[]) new Comparable[initialCapacity + 1];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key k) {
        items[++N] = k;
        swin(N);
    }

    public Comparable<Key> delMax() {
        Key max = items[1];
        exchange(1, N--);
        sink(1);
        items[N + 1] = null;
        return max;
    }

    private void sink(int k) {
        while (2 * k < N) {
            int j = k * 2;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exchange(k, j);
            k = j;
        }
    }

    private void swin(int k) {
        while (k > 1 && less(k / 2, k)) {
            exchange(k, k / 2);
            k = k / 2;
        }
    }

    private boolean less(int a, int b) {
        return items[a].compareTo(items[b]) < 0;
    }

    private void exchange(int i, int j) {
        Key aux = items[i];
        items[i] = items[j];
        items[j] = aux;
    }

    public static void main(String[] args) {
        MaxPQ<Character> chars = new MaxPQ<>(6);
        chars.insert('X');
        chars.insert('D');
        chars.insert('V');
        chars.insert('F');
        chars.insert('M');
        chars.insert('W');
        System.out.println(chars.delMax());
        System.out.println(chars.delMax());
        System.out.println(chars.delMax());
    }

}
