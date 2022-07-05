package com.sllair.sedg.algs4.solutions.exercises.ch2.s1;

import com.sllair.sedg.algs4.examples.ch2.s1.Insertion;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Comparator;

/*
 * Run this program and you'll find that FasterInsertion is
 * about 1.4 times faster than Insertion.
 */
public class Ex25 {

    public static class FasterInsertion {

        // This class should not be instantiated.
        private FasterInsertion() {
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static void sort(Comparable[] a) {
            int n = a.length;
            for (int i = 1; i < n; i++) {
                Comparable tmp = a[i];
                int j = i;
                while (j > 0 && less(tmp, a[j - 1])) {
                    a[j] = a[j - 1];
                    --j;
                }
                a[j] = tmp;
                assert isSorted(a, 0, i);
            }
            assert isSorted(a);
        }

        /***************************************************************************
         *  Helper sorting functions.
         ***************************************************************************/

        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        // is v < w ?
        private static boolean less(Object v, Object w, Comparator comparator) {
            return comparator.compare(v, w) < 0;
        }

        /***************************************************************************
         *  Check if array is sorted - useful for debugging.
         ***************************************************************************/
        private static boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length);
        }

        // is the array a[lo..hi) sorted
        private static boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i < hi; i++)
                if (less(a[i], a[i - 1])) return false;
            return true;
        }

        // print array to standard output
        private static void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++) {
                StdOut.println(a[i]);
            }
        }
    }

    public static void main(String[] args) {
        String[] sortCompareArgs = new String[]{"FasterInsertion", "Insertion", null, "200"};

        int n = 128;

        while (n < Integer.MAX_VALUE / 2) {
            sortCompareArgs[2] = String.valueOf(n);
            sortCompare(sortCompareArgs);
            n *= 2;
        }

    }

    /*
     * I dumped sortCompare's code in here since a circular dependency was making
     * compilation complicated.
     */
    public static double time(String alg, Double[] a) {
        Stopwatch sw = new Stopwatch();
        if (alg.equals("Insertion")) Insertion.sort(a);
        else if (alg.equals("FasterInsertion")) Ex25.FasterInsertion.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return sw.elapsedTime();
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeRandomInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = StdRandom.uniform(0.0, 1.0);
            total += time(alg, a);
        }
        return total;
    }

    // Use alg to sort trials random arrays of length n.
    public static double timeSortedInput(String alg, int n, int trials) {
        double total = 0.0;
        Double[] a = new Double[n];
        // Perform one experiment (generate and sort an array).
        for (int t = 0; t < trials; t++) {
            for (int i = 0; i < n; i++)
                a[i] = 1.0 * i;
            total += time(alg, a);
        }
        return total;
    }

    private static void sortCompare(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int n = Integer.parseInt(args[2]);
        int trials = Integer.parseInt(args[3]);
        double time1, time2;
        if (args.length == 5 && args[4].equals("sorted")) {
            time1 = timeSortedInput(alg1, n, trials);   // Total for alg1.
            time2 = timeSortedInput(alg2, n, trials);   // Total for alg2.
        } else {
            time1 = timeRandomInput(alg1, n, trials);   // Total for alg1.
            time2 = timeRandomInput(alg2, n, trials);   // Total for alg2.
        }

        StdOut.printf("For %d random Doubles\n    %s is", n, alg1);
        StdOut.printf(" %.1f times faster than %s\n", time2 / time1, alg2);
    }

}
