package percolation;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] avgs;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        avgs = new double[trials];
        for (int i = 0; i < trials; i++) {
            avgs[i] = simulatePercolation(n);
        }
    }

    public double mean() {
        return StdStats.mean(avgs);
    }

    public double stddev() {
        return StdStats.stddev(avgs);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(avgs.length));
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(avgs.length));
    }

    public static void main(String[] args) {
        int n = Integer.valueOf(args[0]);
        int trials = Integer.valueOf(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.print("mean                    = ");
        StdOut.println(stats.mean());
        StdOut.print("stddev                  = ");
        StdOut.println(stats.stddev());
        StdOut.print("95% confidence interval = ");
        StdOut.println(stats.confidenceLo() + ", " + stats.confidenceHi());
    }

    private double simulatePercolation(int n) {
        Percolation p = new Percolation(n);
        double iterations = 0.0;
        double total = n * n;
        while (!p.percolates()) {
            int i = StdRandom.uniform(n);
            int j = StdRandom.uniform(n);
            i++;
            j++;
            if (p.isOpen(i, j))
                continue;
            p.open(i, j);
            iterations++;
        }
        return iterations / total;
    }
}
