package priorityqueue;

public class UnorderedMaxPQ<Key extends Comparable<Key>> {

    private Key[] items;
    private int N;

    public UnorderedMaxPQ(int initialCapacity) {
        items = (Key[]) new Comparable[initialCapacity];
        N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key k) {
        items[N++] = k;
    }

    public Comparable<Key> delMax() {
        int max = 0;
        for (int i = 1; i < N; i++)
            if (less(max, i))
                max = i;
        exchange(items, N - 1, max);
        Comparable<Key> largest = items[N - 1];
        N--;
        items[N] = null;
        return largest;
    }

    private boolean less(int a, int b) {
        return items[a].compareTo(items[b]) < 0;
    }

    private void exchange(Comparable[] a, int i, int j) {
        Comparable aux = a[i];
        a[i] = a[j];
        a[j] = aux;
    }

    public static void main(String[] args) {
        UnorderedMaxPQ<Character> chars = new UnorderedMaxPQ<>(6);
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
