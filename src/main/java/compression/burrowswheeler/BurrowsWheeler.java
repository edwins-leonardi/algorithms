package compression.burrowswheeler;


import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
    private static final int R = 256;

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
        int[] next = new int[n];
        char[] first = buildSortedArray(last, next);

        for (int i = firstCode, count = 0; count < n; i = next[i], count++) {
            BinaryStdOut.write(first[i]);
        }
        BinaryStdOut.close();

    }

    private static char[] buildSortedArray(char[] array, int[] next) {
        int[] count = new int[R + 1];
        int n = array.length;
        char[] aux = new char[n];

        for (int i = 0; i < n; i++)
            count[array[i] + 1]++;

        for (int r = 0; r < R; r++)
            count[r + 1] += count[r];

        for (int i = 0; i < n; i++) {
            int idx = count[array[i]]++;
            aux[idx] = array[i];
            next[idx] = i;
        }

        return aux;

    }


    // if args[0] is "-", apply Burrows-Wheeler transform
    // if args[0] is "+", apply Burrows-Wheeler inverse transform
    public static void main(String[] args) {
        if (args[0].equals("-")) transform();
        else if (args[0].equals("+")) inverseTransform();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}
