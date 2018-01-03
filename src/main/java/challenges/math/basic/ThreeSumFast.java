package challenges.math.basic;

import java.util.Arrays;

import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSumFast {

    public static int count(int[] a) {
        Arrays.sort(a);
        int N = a.length;
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                if (BinarySearch.indexOf(a, -a[i] - a[j]) > j)
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
