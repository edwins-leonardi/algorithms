package binarysearch;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class BinarySearch {

    public static int rank(double value, double[] array) throws InterruptedException {

        int lo = 0;
        int hi = array.length - 1;
        StdDraw.setPenColor(StdDraw.RED);

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            System.out.println(mid);
            printColumn(mid, array);
            Thread.sleep(3000);
            if (value < array[mid])
                hi = mid - 1;
            else if (value > array[mid])
                lo = mid + 1;
            else
                return mid;
        }

        return -1;
    }

    public static void main(String[] args) throws InterruptedException {
        int N = 50;
        double[] array = new double[N];
        for (int i = 0; i < N; i++)
            array[i] = StdRandom.uniform();

        Arrays.sort(array);
        for (int i = 0; i < N; i++) {
            printColumn(i, array);
        }

        StdDraw.setPenColor(StdDraw.ORANGE);
        printColumn(33, array);
        int r = rank(array[33], array);
        System.out.println("---------------------");
        System.out.println(r);
    }

    public static void printColumn(int index, double array[]) {
        double x = 1.0 * index / array.length;
        double y = array[index] / 2.0;
        double rw = 0.5 / array.length;
        double rh = array[index] / 2.0;
        StdDraw.filledRectangle(x, y, rw, rh);
    }
}
