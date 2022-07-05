package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class Ex15 {

    public static class ThreeSumFaster {

        private static int twoCount(int[] a, int i) {
            int count = 0;
            int target = -a[i];
            int lo = i + 1;
            int hi = a.length - 1;

            while (lo < hi) {
                int sum = a[lo] + a[hi];

                if (sum < target) {
                    lo++;
                } else if (sum > target) {
                    hi--;
                } else {
                    count++;
                    StdOut.println(String.format("%d %d %d", a[i], a[lo], a[hi]));
                    /*
                     * Since there are no duplicates and the array is sorted, we can safely increment lo and decrement hi.
                     * Take A to be an integer greater than T. Then A + B = T -> B = T - A < T. I.e. for every
                     * integer A > T, there is exactly one integer B < T such that the sum of the pair is T.
                     */
                    lo++;
                    hi--;
                }
            }

            return count;
        }

        public static int count(int[] a) {
            int count = 0;

            Arrays.sort(a);
            /*
             * Sedgewick defines the two sum and three sum problems such that the input
             * consists of distinct values.
             */
            if (containsDuplicates(a)) {
                throw new IllegalArgumentException("array contains duplicate integers");
            }

            for (int i = 0; i < a.length; i++) {
                count += twoCount(a, i);
            }
            return count;
        }

        // returns true if the sorted array a[] contains any duplicated integers
        private static boolean containsDuplicates(int[] a) {
            for (int i = 1; i < a.length; i++) {
                if (a[i] == a[i - 1]) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class TwoSumFaster {
        public static int count(int[] a) {
            int count = 0;
            int lo = 0;
            int hi = a.length - 1;

            Arrays.sort(a);
            /*
             * Sedgewick defines the two sum and three sum problems such that the input
             * consists of distinct values. We could, however, handle duplicates by making
             * two recursive calls when a zero sum is found.
             */
            if (containsDuplicates(a)) {
                throw new IllegalArgumentException("array contains duplicate integers");
            }
            while (lo < hi) {
                if (a[lo] >= 0 || a[hi] <= 0) {
                    break;
                }

                int sum = a[lo] + a[hi];

                if (sum < 0) {
                    lo++;
                } else if (sum > 0) {
                    hi--;
                } else {
                    count++;
                    StdOut.println(String.format("%d %d", a[lo], a[hi]));
                    /*
                     * Since there are no duplicates and the array is sorted, we can safely increment lo and decrement hi.
                     * Take A to be an integer greater zero. Then A + B = 0 -> B = -A < 0. I.e. for every
                     * positive integer A, there is exactly one negative integer B such that the sum of the pair is zero.
                     */
                    lo++;
                    hi--;
                }
            }

            return count;
        }

        // returns true if the sorted array a[] contains any duplicated integers
        private static boolean containsDuplicates(int[] a) {
            for (int i = 1; i < a.length; i++) {
                if (a[i] == a[i - 1]) {
                    return true;
                }
            }
            return false;
        }
    }

    private static boolean shouldRunTwoSumFaster(String[] args) {
        return args.length > 1 && "2".equals(args[1]);
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex15.class);
        int[] a = in.readAllInts();
        boolean shouldRunTwoSumFaster = shouldRunTwoSumFaster(args);
        StdOut.println(String.format("shouldRunTwoSumFaster %s", shouldRunTwoSumFaster));
        StdOut.println();
        Stopwatch timer = new Stopwatch();
        int count = shouldRunTwoSumFaster ?
                Ex15.TwoSumFaster.count(a) :
                Ex15.ThreeSumFaster.count(a);
        StdOut.println(String.format("elapsed time = %.3f s", timer.elapsedTime()));
        StdOut.println(count);
    }

}
