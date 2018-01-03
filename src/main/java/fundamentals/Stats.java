package fundamentals;

import edu.princeton.cs.algs4.StdIn;

public class Stats {

    public static void main(String[] args) {
        Bag<Double> numbers = new Bag<>();
        while (!StdIn.isEmpty())
            numbers.add(StdIn.readDouble());
        int n = numbers.size();
        double sum = 0.0;
        for (double x : numbers)
            sum += x;

        double mean = sum / n;
        sum = 0.0;
        for (double x : numbers)
            sum += (x - mean) * (x - mean);

        double std = Math.sqrt(sum / n - 1);

        System.out.printf("%.2f\n", mean);
        System.out.printf("%.2f\n", std);

    }

}
