package challenges.math.basic;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {
    public static int count(int[] a) {
        int count = 0;
        int N = a.length;
        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
        System.out.println("count " + count);
        return count;
    }

    public static void main(String[] args) {
        In in = new In("math/4kints.txt");
        int a[] = in.readAllInts();
        Stopwatch w = new Stopwatch();
        StdOut.println(count(a));
        System.out.println("Elapsed time " + w.elapsedTime());
    }
}
