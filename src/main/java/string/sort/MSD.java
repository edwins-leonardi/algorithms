package string.sort;

import edu.princeton.cs.algs4.StdRandom;

public class MSD {

    private static String[] aux;
    private static final int R = 256;
    private static final int CUTOFF = 15;

    public static void sort(String[] a, String[] aux, int lo, int hi, int d) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo) {
            return;
        }

        // if (hi <= lo + CUTOFF) {
        // insertion(a, lo, hi, d);
        // return;
        // }
        System.out.println("LO = " + lo + " HI = " + hi);
        print(a, lo, hi);

        // compute frequency counts
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c + 2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < R + 1; r++)
            count[r + 1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c + 1]++] = a[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];

        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++)
            sort(a, aux, lo + count[r], lo + count[r + 1] - 1, d + 1);

    }

    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
                exch(a, j, j - 1);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i))
                return true;
            if (v.charAt(i) > w.charAt(i))
                return false;
        }
        return v.length() < w.length();
    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length())
            return -1;
        return s.charAt(d);
    }

    public static void sort(String[] a) {
        aux = new String[a.length];
        sort(a, aux, 0, a.length - 1, 0);
    }

    public static void main(String[] args) {
        String[] codes = new String[] { "are", "by", "sea", "seashells", "seashells", "sells", "sells", "at", "she",
                "she", "shells", "shore", "surely", "the", "the", "ball" };
        StdRandom.shuffle(codes);
        MSD.sort(codes);
    }

    public static void print(String[] codes, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            System.out.print(codes[i] + "\t");
        System.out.println();
    }
}
