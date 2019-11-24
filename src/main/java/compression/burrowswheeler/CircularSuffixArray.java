package compression.burrowswheeler;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class CircularSuffixArray {
    // circular suffix array of s

    private Suffix[] suffixes;

    private static class Suffix implements Comparable<CircularSuffixArray.Suffix> {
        private final String text;
        private final int index;

        private Suffix(String text, int index) {
            this.text = text;
            this.index = index;
        }

        private int length() {
            return text.length();
        }

        private char charAt(int i) {
            return text.charAt((index + i) % text.length());
        }

        public int compareTo(CircularSuffixArray.Suffix that) {
            if (this == that) return 0;  // optimization
            int n = Math.min(this.length(), that.length());
            for (int i = 0; i < n; i++) {
                if (this.charAt(i) < that.charAt(i)) return -1;
                if (this.charAt(i) > that.charAt(i)) return +1;
            }
            return this.length() - that.length();
        }

        public String toString() {
            return text.substring(index) + text.substring(0, index);
        }
    }

    public CircularSuffixArray(String s) {
        if (s == null)
            throw new IllegalArgumentException();
        int size = s.length();
        suffixes = new Suffix[size];
        for (int i = 0; i < size; i++) {
            suffixes[i] = new Suffix(s, i);
        }
        Arrays.sort(suffixes);
    }

    // length of s
    public int length() {
        return suffixes.length;
    }


    // returns index of ith sorted suffix
    public int index(int i) {
        if (i < 0 || i >= suffixes.length)
            throw new IllegalArgumentException();
        return suffixes[i].index;
    }


    // unit testing (required)
    public static void main(String[] args) {
        CircularSuffixArray csa = new CircularSuffixArray("CADABRA!ABRA");
        for (int i = 0; i < csa.length(); i++) {
            StdOut.println(i + " " + csa.index(i));
        }
    }
}
