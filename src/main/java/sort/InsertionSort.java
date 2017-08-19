package sort;

import java.util.Comparator;

public class InsertionSort {
    private InsertionSort() {
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--) {
                exchange(a, j, j - 1);
            }
        }
    }

    private static void exchange(Object[] items, int i, int j) {
        Object aux = items[i];
        items[i] = items[j];
        items[j] = aux;
    }

    private static boolean less(Comparator comparator, Object v, Object w) {
        return comparator.compare(v, w) < 0;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }

    private static void print(Comparable[] items) {
        System.out.print("\n[");
        for (Comparable c : items) {
            System.out.print(c + ", ");
        }
        System.out.print("]\n");
    }

    public static void main(String args[]) {
        Integer[] array = new Integer[] { 45, 64, 23, 49, 3, 9, 2, 8, 16, 17,
                83, 1 };
        System.out.println("Unordered array :");
        print(array);
        InsertionSort.sort(array);
        System.out.println("ordered array :");
        print(array);
    }
}
