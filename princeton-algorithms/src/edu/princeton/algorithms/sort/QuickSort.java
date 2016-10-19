package edu.princeton.algorithms.sort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    private static int partition(Comparable[] a, int lo, int hi) {

        int i = lo;
        int j = hi + 1;

        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi)
                    break;

            while (less(a[lo], a[--j]))
                if (j == lo)
                    break;

            if (i >= j)
                break;

            exchange(a, i, j);
        }

        exchange(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
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
        Character[] chars = new Character[] { 'Z', 'B', 'A', 'R', 'I', 'G',
                'L', 'I', 'O' };
        QuickSort.sort(chars);
        for (Character c : chars)
            System.out.println(c);
    }
}
