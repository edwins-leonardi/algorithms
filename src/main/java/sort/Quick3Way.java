package sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3Way {
    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int lt = lo;
        int gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)
                exchange(a, i++, lt++);
            else if (cmp > 0)
                exchange(a, gt--, i);
            else
                i++;
        }
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exchange(Comparable[] items, int i, int j) {
        Comparable aux = items[i];
        items[i] = items[j];
        items[j] = aux;
    }

    public static void main(String[] args) {
        Character[] chars = new Character[] { 'Z', 'B', 'A', 'R', 'I', 'G', 'L', 'I', 'O' };
        QuickSort.sort(chars);
        for (Character c : chars)
            System.out.println(c);
    }

}
