package challenges.math.basic;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class TwoSumFast {
    public static int count(int[] a) {
        int count = 0;
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++)
            if (BinarySearch.indexOf(a, a[i]) > i)
                count++;
        return count;
    }

    public static void main(String[] args) {
        In in = new In("math/8kints.txt");
        int a[] = in.readAllInts();
        Stopwatch w = new Stopwatch();
        StdOut.println(count(a));
        System.out.println("Elapsed time " + w.elapsedTime());
    }

}
