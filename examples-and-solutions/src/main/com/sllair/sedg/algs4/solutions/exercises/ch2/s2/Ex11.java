package com.sllair.sedg.algs4.solutions.exercises.ch2.s2;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex11 {

    private static final int INSERTION_CUTOFF = 5;

    public static class EnhancedMerge {

        // This class should not be instantiated.
        private EnhancedMerge() {
        }

        // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
        private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
            // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
            assert isSorted(a, lo, mid);
            assert isSorted(a, mid + 1, hi);


            // merge back to a[]
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) aux[k] = a[j++];
                else if (j > hi) aux[k] = a[i++];
                else if (less(a[j], a[i])) aux[k] = a[j++];
                else aux[k] = a[i++];
            }

            // postcondition: dst[lo .. hi] is sorted subarray
            assert isSorted(aux, lo, hi);
        }

        private static void insertSort(Comparable[] a, int lo, int hi) {
            for (int i = lo; i <= hi; ++i) {
                Comparable tmp = a[i];
                int j = i;
                while (j > lo && less(tmp, a[j - 1])) {
                    a[j] = a[j - 1];
                    --j;
                }
                a[j] = tmp;
                assert isSorted(a, lo, i);
            }
            assert isSorted(a, lo, hi);
        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
            if (hi <= lo) return;
            if (hi - lo < INSERTION_CUTOFF) {
                insertSort(aux, lo, hi);
            } else {
                int mid = lo + (hi - lo) / 2;
                sort(aux, a, lo, mid);
                sort(aux, a, mid + 1, hi);
                if (less(a[mid + 1], a[mid])) {
                    merge(a, aux, lo, mid, hi);
                } else {
                    for (int i = lo; i <= hi; ++i) {
                        aux[i] = a[i];
                    }
                }
            }
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static void sort(Comparable[] a) {
            Comparable[] aux = a.clone();
            sort(aux, a, 0, a.length - 1);
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
        // stably merge a[lo .. mid] with a[mid+1 .. hi] using aux[lo .. hi]
        private static void merge(Comparable[] a, int[] index, int[] aux, int lo, int mid, int hi) {

            // merge back to a[]
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) aux[k] = index[j++];
                else if (j > hi) aux[k] = index[i++];
                else if (less(a[index[j]], a[index[i]])) aux[k] = index[j++];
                else aux[k] = index[i++];
            }
        }

        private static void insertionIndexSort(Comparable[] a, int[] index, int lo, int hi) {
            for (int i = lo; i <= hi; ++i) {
                Comparable tmp = a[index[i]];
                int j = i;
                while (j > lo && less(tmp, a[index[j - 1]])) {
                    index[j] = index[j - 1];
                    --j;
                }
                index[j] = i;
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

            int[] aux = index.clone();
            sort(a, aux, index, 0, n - 1);
            return index;
        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private static void sort(Comparable[] a, int[] index, int[] aux, int lo, int hi) {
            if (hi <= lo) return;
            if (hi - lo < INSERTION_CUTOFF) {
                insertionIndexSort(a, aux, lo, hi);
            } else {
                int mid = lo + (hi - lo) / 2;
                sort(a, aux, index, lo, mid);
                sort(a, aux, index, mid + 1, hi);
                if (less(a[index[mid + 1]], a[index[mid]])) {
                    merge(a, index, aux, lo, mid, hi);
                } else {
                    for (int i = lo; i <= hi; ++i) {
                        aux[i] = index[i];
                    }
                }
            }
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
        int[] index = EnhancedMerge.indexSort(a);
        EnhancedMerge.sort(a);
        EnhancedMerge.show(a);
        StdOut.println(String.format("%12s %12s", "Orig. Index", "Value"));
        for (int i = 0; i < a.length; ++i) {
            StdOut.println(String.format("%12d %12s", index[i], a[i]));
        }
    }

}
