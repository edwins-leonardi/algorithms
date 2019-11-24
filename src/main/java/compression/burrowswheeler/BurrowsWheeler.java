package compression.burrowswheeler;


import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class BurrowsWheeler {

    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray suffixArray = new CircularSuffixArray(s);
        int size = suffixArray.length();
        char[] array = new char[size];
        for (int i = 0; i < size; i++) {
            int index = suffixArray.index(i);
            array[i] = s.charAt((size + (index - 1)) % size);
            if (index == 0)
                BinaryStdOut.write(i);
        }
        for (int i = 0; i < size; i++) {
            BinaryStdOut.write(array[i], 8);
        }
        BinaryStdOut.close();
    }

    // apply Burrows-Wheeler inverse transform,
    // reading from standard input and writing to standard output
    public static void inverseTransform() {
        int firstCode = BinaryStdIn.readInt();
        char[] last = BinaryStdIn.readString().toCharArray();
        int n = last.length;
        char[] first = last.clone();
        Integer[] next = new Integer[n];
        Arrays.sort(first);
        int repetitions_count = 0;
        for (int i = 0; i < n; i++) {
            repetitions_count++;
            if (i > 0 && first[i] != first[i - 1])
                repetitions_count = 1;
            int skip = 1;
            for (int j = 0; j < n; j++) {
                if (first[i] == last[j]) {
                    if (skip != repetitions_count) {
                        skip++;
                        continue;
                    }
                    next[i] = j;
                    break;
                }
            }

        }
        for (int i = firstCode, count = 0; count < n; i = next[i], count++) {
            BinaryStdOut.write(first[i]);
        }
        BinaryStdOut.close();
    }

    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
