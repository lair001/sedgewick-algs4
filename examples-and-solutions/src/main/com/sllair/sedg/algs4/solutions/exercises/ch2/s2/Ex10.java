package com.sllair.sedg.algs4.solutions.exercises.ch2.s2;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex10 {

    public static class FasterMerge {

        // This class should not be instantiated.
        private FasterMerge() {
        }

        // unstably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
        private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
            assert isSorted(a, lo, mid);
            assert isSorted(a, mid + 1, hi);

            for (int k = lo; k <= mid; ++k) {
                aux[k] = a[k];
            }

            for (int k = hi; k > mid; --k) {
                aux[hi - k + mid + 1] = a[k];
            }

            int i = lo;
            int j = lo;
            int k = hi;
            while (j <= mid) {
                if (less(aux[k], aux[j])) {
                    a[i] = aux[k];
                    --k;
                } else {
                    a[i] = aux[j];
                    ++j;
                }
                ++i;
            }

            // postcondition: a[lo .. hi] is sorted
            assert isSorted(a, lo, hi);
        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid);
            sort(a, aux, mid + 1, hi);
            merge(a, aux, lo, mid, hi);
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static void sort(Comparable[] a) {
            Comparable[] aux = new Comparable[a.length];
            sort(a, aux, 0, a.length - 1);
            assert isSorted(a);
        }


        /***************************************************************************
         *  Helper sorting function.
         ***************************************************************************/

        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        /***************************************************************************
         *  Check if array is sorted - useful for debugging.
         ***************************************************************************/
        private static boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length - 1);
        }

        private static boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++)
                if (less(a[i], a[i - 1])) return false;
            return true;
        }

        /***************************************************************************
         *  Index mergesort.
         ***************************************************************************/
        // unstably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
        private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {
            for (int k = lo; k <= mid; ++k) {
                aux[k] = index[k];
            }

            for (int k = hi; k > mid; --k) {
                aux[hi - k + mid + 1] = index[k];
            }

            int i = lo;
            int j = lo;
            int k = hi;
            while (j <= mid) {
                if (less(a[aux[k]], a[aux[j]])) {
                    index[i] = aux[k];
                    --k;
                } else {
                    index[i] = aux[j];
                    ++j;
                }
                ++i;
            }
        }

        /**
         * Returns a permutation that gives the elements in the array in ascending order.
         *
         * @param a the array
         * @return a permutation {@code p[]} such that {@code a[p[0]]}, {@code a[p[1]]},
         * ..., {@code a[p[N-1]]} are in ascending order
         */
        public static int[] indexSort(Comparable[] a) {
            int n = a.length;
            int[] index = new int[n];
            for (int i = 0; i < n; i++)
                index[i] = i;

            int[] aux = new int[n];
            sort(a, index, aux, 0, n - 1);
            return index;
        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, index, aux, lo, mid);
            sort(a, index, aux, mid + 1, hi);
            merge(a, index, aux, lo, mid, hi);
        }

        // print array to standard output
        private static void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++) {
                StdOut.println(a[i]);
            }
        }
    }

    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = InFactory.get(args, Ex10.class);
        String[] a = in.readAllStrings();
        int[] index = FasterMerge.indexSort(a);
        FasterMerge.sort(a);
        StdOut.println(String.format("%12s %12s", "Orig. Index", "Value"));
        for (int i = 0; i < a.length; ++i) {
            StdOut.println(String.format("%12d %12s", index[i], a[i]));
        }
    }

}
