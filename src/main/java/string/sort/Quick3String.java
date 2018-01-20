package string.sort;

import edu.princeton.cs.algs4.StdRandom;

public class Quick3String {

    private static int charAt(String s, int d) {
        if (d == s.length())
            return -1;
        return s.charAt(d);
    }

    public static void sort(String[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo)
            return;

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v)
                exchange(a, lt++, i++);
            else if (t > v)
                exchange(a, i, gt--);
            else
                i++;

        }
        sort(a, lo, lt - 1, d);
        if (v >= 0)
            sort(a, lt, gt, d + 1);
        sort(a, gt + 1, hi, d);
    }

    private static void exchange(String[] items, int i, int j) {
        String aux = items[i];
        items[i] = items[j];
        items[j] = aux;
    }

    public static void main(String[] args) {
        String[] codes = new String[] { "are", "by", "sea", "seashells", "seashells", "sells", "sells", "at", "she",
                "she", "shells", "shore", "surely", "the", "the", "ball" };
        StdRandom.shuffle(codes);
        Quick3String.sort(codes);
        for (String s : codes)
            System.out.print(s + "\t");
        System.out.println();
    }
}
