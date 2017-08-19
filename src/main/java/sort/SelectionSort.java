package sort;

import java.util.Comparator;

public class SelectionSort {

    private SelectionSort() {
    }

    public static void sort(Comparable[] items) {
        for (int i = 0; i < items.length; i++) {
            int min = i;
            for (int j = i + 1; j < items.length; j++) {
                if (less(items[j], items[i]))
                    min = j;
            }
            exchange(items, i, min);
        }
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(comparator, a[j], a[min]))
                    min = j;
            }
            exchange(a, i, min);
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
        SelectionSort.sort(array);
        System.out.println("ordered array :");
        print(array);
    }
}
