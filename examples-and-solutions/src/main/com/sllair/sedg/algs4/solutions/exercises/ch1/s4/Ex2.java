package com.sllair.sedg.algs4.solutions.exercises.ch1.s4; /******************************************************************************
 *  Compilation:  javac ThreeSum.java
 *  Execution:    java ThreeSum input.txt
 *  Dependencies: In.java StdOut.java Stopwatch.java
 *  Data files:   https://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                https://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program with cubic running time. Reads n integers
 *  and counts the number of triples that sum to exactly 0
 *  (ignoring integer overflow).
 *
 *  % java ThreeSum 1Kints.txt
 *  70
 *
 *  % java ThreeSum 2Kints.txt
 *  528
 *
 *  % java ThreeSum 4Kints.txt
 *  4039
 *
 ******************************************************************************/

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * The {@code ThreeSum} class provides static methods for counting
 * and printing the number of triples in an array of integers that sum to 0
 * (ignoring integer overflow).
 * <p>
 * This implementation uses a triply nested loop and takes proportional to n^3,
 * where n is the number of integers.
 * <p>
 * For additional documentation, see <a href="https://algs4.cs.princeton.edu/14analysis">Section 1.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */


public class Ex2 {

    public static class ThreeSumWithOverflowPrevention {

        // Do not instantiate.
        private ThreeSumWithOverflowPrevention() {
        }

        /**
         * Prints to standard output the (i, j, k) with {@code i < j < k}
         * such that {@code a[i] + a[j] + a[k] == 0}.
         *
         * @param a the array of integers
         */
        public static void printAll(int[] a) {
            int n = a.length;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (sumEqualsZeroWithoutOverflow(a[i], a[j], a[k])) {
                            StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                        }
                    }
                }
            }
        }

        private static boolean canSumWithoutOverflow(int x, int y) {
            return !((x > 0 && y > Integer.MAX_VALUE - x)
                    || (x < 0 && y < Integer.MIN_VALUE - x));
        }

        private static boolean sumEqualsZeroWithoutOverflowInOrder(int a, int b, int c) {
            if (canSumWithoutOverflow(a, b)) {
                int sum = a + b;
                if (canSumWithoutOverflow(sum, c)) {
                    sum += c;
                    return sum == 0;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        private static boolean sumEqualsZeroWithoutOverflow(int ai, int aj, int ak) {
            return sumEqualsZeroWithoutOverflowInOrder(ai, aj, ak)
                    || sumEqualsZeroWithoutOverflowInOrder(ai, ak, aj)
                    || sumEqualsZeroWithoutOverflowInOrder(ak, aj, ai);
        }

        /**
         * Returns the number of triples (i, j, k) with {@code i < j < k}
         * such that {@code a[i] + a[j] + a[k] == 0}.
         *
         * @param a the array of integers
         * @return the number of triples (i, j, k) with {@code i < j < k}
         * such that {@code a[i] + a[j] + a[k] == 0}
         */
        public static int count(int[] a) {
            int n = a.length;
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        if (sumEqualsZeroWithoutOverflow(a[i], a[j], a[k])) {
                            count++;
                        }
                    }
                }
            }
            return count;
        }
    }

    public static class ThreeSumFastWithOverflowPrevention {

        // Do not instantiate.
        private ThreeSumFastWithOverflowPrevention() {
        }

        // returns true if the sorted array a[] contains any duplicated integers
        private static boolean containsDuplicates(int[] a) {
            for (int i = 1; i < a.length; i++)
                if (a[i] == a[i - 1]) return true;
            return false;
        }

        /**
         * Prints to standard output the (i, j, k) with {@code i < j < k}
         * such that {@code a[i] + a[j] + a[k] == 0}.
         *
         * @param a the array of integers
         * @throws IllegalArgumentException if the array contains duplicate integers
         */
        public static void printAll(int[] a) {
            int n = a.length;
            Arrays.sort(a);
            if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if ((a[i] > 0 && a[j] > Integer.MAX_VALUE - a[i])
                            || (a[i] < 0 && a[j] <= Integer.MIN_VALUE - a[i])) {
                        continue;
                    }

                    int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                    if (k > j) StdOut.println(a[i] + " " + a[j] + " " + a[k]);
                }
            }
        }

        /**
         * Returns the number of triples (i, j, k) with {@code i < j < k}
         * such that {@code a[i] + a[j] + a[k] == 0}.
         *
         * @param a the array of integers
         * @return the number of triples (i, j, k) with {@code i < j < k}
         * such that {@code a[i] + a[j] + a[k] == 0}
         */
        public static int count(int[] a) {
            int n = a.length;
            Arrays.sort(a);
            if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if ((a[i] > 0 && a[j] > Integer.MAX_VALUE - a[i])
                            || (a[i] < 0 && a[j] <= Integer.MIN_VALUE - a[i])) {
                        continue;
                    }

                    int k = Arrays.binarySearch(a, -(a[i] + a[j]));
                    if (k > j) count++;
                }
            }
            return count;
        }
    }

    private static boolean shouldRunFast(String args[]) {
        return args.length > 1 && "f".equals(args[1]);
    }

    /**
     * Reads in a sequence of integers from a file, specified as a command-line argument;
     * counts the number of triples sum to exactly zero; prints out the time to perform
     * the computation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws URISyntaxException {
        In in = InFactory.get(args, Ex2.class);
        int[] a = in.readAllInts();

        boolean shouldRunFast = shouldRunFast(args);

        StdOut.println(String.format("should run fast: %s", shouldRunFast));
        StdOut.println();

        Stopwatch timer = new Stopwatch();
        int count = shouldRunFast ?
                ThreeSumFastWithOverflowPrevention.count(a) :
                ThreeSumWithOverflowPrevention.count(a);
        StdOut.println(String.format("elapsed time = %.3f s", timer.elapsedTime()));
        StdOut.println(count);

        if ("Overflow.txt".equals(args[0])) {
            if (shouldRunFast) {
                ThreeSumFastWithOverflowPrevention.printAll(a);
            } else {
                ThreeSumWithOverflowPrevention.printAll(a);
            }
        }
    }
}
