/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final String MEAN_LABEL = "mean                    =";
    private static final String STDD_LABEL = "stddev                  =";
    private static final String CIVL_LABEL = "95% confidence interval =";
    private static final String MEAN_STDD_FMT = "%s %f";
    private static final String CIVL_FMT = "%s [%f, %f]";

    private double mean;
    private double stddev;
    private double confidenceLo;
    private double confidenceHi;

    public PercolationStats(int n, int trials) {
        if (trials < 1) {
            throw new IllegalArgumentException("trials must be at least 1!");
        }

        int randomRangeEnd = n + 1;
        double nSqr = n * n;
        double[] percThresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);

            while (!perc.percolates()) {
                perc.open(
                        StdRandom.uniform(1, randomRangeEnd),
                        StdRandom.uniform(1, randomRangeEnd)
                );
            }
            percThresholds[i] = perc.numberOfOpenSites() / nSqr;
        }

        this.mean = StdStats.mean(percThresholds);
        this.stddev = StdStats.stddev(percThresholds);
        double confidenceDelta = 1.96 * this.stddev / Math.sqrt(trials);
        this.confidenceLo = this.mean - confidenceDelta;
        this.confidenceHi = this.mean + confidenceDelta;
    }

    public double mean() {
        return this.mean;
    }

    public double stddev() {
        return this.stddev;
    }

    public double confidenceLo() {
        return this.confidenceLo;
    }

    public double confidenceHi() {
        return this.confidenceHi;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        StdOut.println(String.format(
                MEAN_STDD_FMT, MEAN_LABEL, stats.mean()
        ));
        StdOut.println(String.format(
                MEAN_STDD_FMT, STDD_LABEL, stats.stddev()
        ));
        StdOut.println(String.format(
                CIVL_FMT, CIVL_LABEL, stats.confidenceLo(), stats.confidenceHi()
        ));
    }
}
