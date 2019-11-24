package priorityqueue;

public class HeapSort {

    public void sort(Comparable[] pq) {
        int N = pq.length;
        for (int k = N / 2; k >= 1; k--)
            sink(pq, k, N);
        while (N > 1) {
            exchange(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    private void sink(Comparable[] pq, int k, int N) {
        while (2 * k < N) {
            int j = k * 2;
            if (j < N && less(pq, j, j + 1))
                j++;
            if (!less(pq, k, j))
                break;
            exchange(pq, k, j);
            k = j;
        }
    }

    private boolean less(Comparable[] pq, int a, int b) {
        return pq[a - 1].compareTo(pq[b - 1]) < 0;
    }

    private void exchange(Comparable[] items, int i, int j) {
        Comparable aux = items[i - 1];
        items[i - 1] = items[j - 1];
        items[j - 1] = aux;
    }

    public static void main(String[] args) {
        HeapSort hs = new HeapSort();
        String names[] = new String[] { "Ana", "Maria", "Fatima", "Roberto", "Velasques", "Zuleide", "Anibal", "Aline" };
        hs.sort(names);
        for (String name : names)
            System.out.println(name);
    }

}
