package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import edu.princeton.cs.algs4.StdOut;

public class Ex21 {

    public static void main(String[] args) {
        /*
         * n is upper bounded so that 200 trials for each n
         * can be completed in a reasonable amount of time.
         *
         * n = 1 should be avoided due to a
         * divide by zero issue.
         */
        int n = 2;
        while (n <= 4194304) {
            /*
             * Test the hypothesis for each N by averaging
             * the ratio count/(0.5*N*ln(N)) over 200 trials.
             * We want to see averages close to 1 for large N.
             */

            double ratioSum = 0.0;

            for (int i = 0; i < 200; i++) {
                ratioSum += Ex17.count(n) / (0.5 * n * Math.log(n));
            }

            StdOut.println(String.format("%10d %.5f",
                    n,
                    ratioSum / 200.0
            ));
            n += n;
        }
    }

}
