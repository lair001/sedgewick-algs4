package com.sllair.sedg.algs4.solutions.exercises.ch2.s2;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Ex19 {

    /*
     * A bottom up merge instrumented to count the number of inversions it encounters.
     */
    private static class CountingMergeBU {

        // This class should not be instantiated.
        private CountingMergeBU() {
        }

        // stably merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi]
        private static int merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }
            int inversions = 0;
            // merge back to a[]
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];  // this copying is unneccessary
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) {
                    inversions += mid - i + 1;
                    a[k] = aux[j++];
                } else a[k] = aux[i++];
            }
            return inversions;
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static int sort(Comparable[] a) {
            int n = a.length;
            Comparable[] aux = new Comparable[n];
            int inversions = 0;
            for (int len = 1; len < n; len *= 2) {
                for (int lo = 0; lo < n - len; lo += len + len) {
                    int mid = lo + len - 1;
                    int hi = Math.min(lo + len + len - 1, n - 1);
                    inversions += merge(a, aux, lo, mid, hi);
                }
            }
            assert isSorted(a);
            return inversions;
        }

        /***********************************************************************
         *  Helper sorting functions.
         ***************************************************************************/

        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }


        /***************************************************************************
         *  Check if array is sorted - useful for debugging.
         ***************************************************************************/
        private static boolean isSorted(Comparable[] a) {
            for (int i = 1; i < a.length; i++)
                if (less(a[i], a[i - 1])) return false;
            return true;
        }

        // print array to standard output
        private static void show(Comparable[] a) {
            for (int i = 0; i < a.length; i++) {
                StdOut.println(a[i]);
            }
        }

        /**
         * Reads in a sequence of strings from standard input; bottom-up
         * mergesorts them; and prints them to standard output in ascending order.
         *
         * @param args the command-line arguments
         */
        public static void main(String[] args) {
            String[] a = StdIn.readAllStrings();
            com.sllair.sedg.algs4.examples.ch2.s2.MergeBU.sort(a);
            show(a);
        }
    }

    public static int countInversions(Comparable[] a) {
        // copy the array so we don't mutate the original
        return CountingMergeBU.sort(a.clone());
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex19.class);
        String[] a = in.readAllStrings();
        StdOut.println(countInversions(a));
        CountingMergeBU.show(a);
    }
}
