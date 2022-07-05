package com.sllair.sedg.algs4.solutions.exercises.ch2.s1;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.awt.*;

public class Ex17 {

    public static class AnimatedInsertion {

        // This class should not be instantiated.
        private AnimatedInsertion() {
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static void sort(Double[] a) {
            show(a);
            int n = a.length;
            for (int i = 1; i < n; i++) {
                for (int j = i; j > 0 && less(a, i, j); j--) {
                    exch(a, j, j - 1);
                }
                assert isSorted(a, 0, i);
            }
            assert isSorted(a);
            show(a);
        }

        /***************************************************************************
         *  Helper sorting functions.
         ***************************************************************************/

        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static boolean less(Double[] a, int i, int j) {
            show(a, i, j);
            return less(a[j], a[j - 1]);
        }

        // exchange a[i] and a[j]
        private static void exch(Object[] a, int i, int j) {
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
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
        private static void show(Double[] a, int i, int j) {
            StdDraw.clear();

            for (int k = 0; k < a.length; k++) {
                if (i == -1 && j == -1) {
                    StdDraw.setPenColor(Color.BLACK);
                } else if (k == j) {
                    StdDraw.setPenColor(Color.RED);
                } else if (k <= i && k > j) {
                    StdDraw.setPenColor(Color.BLACK);
                } else {
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                }

                StdDraw.line(k, 0, k, a[k]);
            }
        }

        private static void show(Double[] a) {
            show(a, -1, -1);
        }
    }

    public static class AnimatedSelection {

        // This class should not be instantiated.
        private AnimatedSelection() {
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static void sort(Double[] a) {
            show(a);
            int n = a.length;
            for (int i = 0; i < n; i++) {
                int min = i;
                for (int j = i + 1; j < n; j++) {
                    if (less(a[j], a[min])) min = j;
                }
                show(a, i, min);
                exch(a, i, min);
                assert isSorted(a, 0, i);
            }
            show(a);
            assert isSorted(a);
        }


        /***************************************************************************
         *  Helper sorting functions.
         ***************************************************************************/

        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        // exchange a[i] and a[j]
        private static void exch(Object[] a, int i, int j) {
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }


        /***************************************************************************
         *  Check if array is sorted - useful for debugging.
         ***************************************************************************/

        // is the array a[] sorted?
        private static boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length - 1);
        }

        // is the array sorted from a[lo] to a[hi]
        private static boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(a[i], a[i - 1])) return false;
            return true;
        }


        // print array to standard output
        private static void show(Double[] a, int i, int min) {
            StdDraw.clear();

            for (int k = 0; k < a.length; k++) {
                if (i == -1) {
                    StdDraw.setPenColor(Color.BLACK);
                } else if (k == min) {
                    StdDraw.setPenColor(Color.RED);
                } else if (k >= i) {
                    StdDraw.setPenColor(Color.BLACK);
                } else {
                    StdDraw.setPenColor(Color.LIGHT_GRAY);
                }

                StdDraw.line(k, 0, k, a[k]);
            }
        }

        private static void show(Double[] a) {
            show(a, -1, Integer.MIN_VALUE);
        }

    }


    private static Double[] getArray(int N) {
        Double[] a = new Double[N];
        for (int i = 0; i < N; i++) {
            a[i] = StdRandom.uniform(0.0, 1.0);
        }
        return a;
    }

    public static double animate(String alg, Double[] a) {
        Stopwatch sw = new Stopwatch();
        if (alg.equals("Insertion")) AnimatedInsertion.sort(a);
        else if (alg.equals("Selection")) AnimatedSelection.sort(a);
        else throw new IllegalArgumentException("Invalid algorithm: " + alg);
        return sw.elapsedTime();
    }

    /*
     * I recommend running for 40 <= N <= 200
     */
    public static void main(String[] args) {
        int N = Integer.parseInt(args[1]);
        int width = 8 * N;
        int height = 4 * N;
        StdDraw.setCanvasSize(width, height);
        StdDraw.setPenRadius(.0015 * width / N);
        StdDraw.setXscale(-0.7, N - 0.3);
        StdDraw.setYscale(-1.0 / N, 1 + 1.0 / N);
        animate(args[0], getArray(N));
    }


}
