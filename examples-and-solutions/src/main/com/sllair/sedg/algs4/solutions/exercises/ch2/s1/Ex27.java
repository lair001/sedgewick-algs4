package com.sllair.sedg.algs4.solutions.exercises.ch2.s1;

import com.sllair.sedg.algs4.examples.ch2.s1.SortCompare;
import edu.princeton.cs.algs4.StdOut;

public class Ex27 {

    /*
     * As N increases, the ratios of the runtimes of the quadratic
     * algorithms Insertion and Selection with the runtime of
     * Shell increases rather than remaining constant. This
     * shows that the runtime of Shell is subquadratic.
     *
     * In contrast, the ratio of the runtime of Selection to the
     * runtime of Insertion stabilizes around 1.7, which is
     * expected since they both have quadratic runtime complexity.
     */
    public static void main(String[] args) {
        String alg1 = "Insertion";
        String alg2 = "Selection";
        String alg3 = "Shell";
        int N = 128;

        while (N < Integer.MAX_VALUE / 2) {

            int trials = Integer.parseInt(args[0]);
            double time1, time2, time3;

            time1 = SortCompare.timeRandomInput(alg1, N, trials);   // Total for alg1.
            time2 = SortCompare.timeRandomInput(alg2, N, trials);   // Total for alg2.
            time3 = SortCompare.timeRandomInput(alg3, N, trials);   // Total for alg3.

            StdOut.printf("For %d random Doubles\n    %s is", N, alg2);
            StdOut.printf(" %.1f times faster than %s\n", time1 / time2, alg1);

            StdOut.printf("For %d random Doubles\n    %s is", N, alg3);
            StdOut.printf(" %.1f times faster than %s\n", time1 / time3, alg1);

            StdOut.printf("For %d random Doubles\n    %s is", N, alg3);
            StdOut.printf(" %.1f times faster than %s\n", time2 / time3, alg2);

            N *= 2;
        }
    }
}
