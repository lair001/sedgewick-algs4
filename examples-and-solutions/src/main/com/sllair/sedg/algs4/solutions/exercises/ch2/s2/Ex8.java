package com.sllair.sedg.algs4.solutions.exercises.ch2.s2;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Question Prompt:
 * Suppose that Algorithm 2.4 is modified to skip the call on merge() whenever
 * a[mid] <= a[mid+1]. Prove that the number of compares used to mergesort a sorted
 * array is linear.
 *
 * Solution:
 * For a sorted array, the modified merge sort will make only one compare per subarray.
 * For simplicity, let us consider the number of subarrays produced when N is even:
 * 2 + 4 + 8 + ... + N = 2*(N - 1). This is a linear function of N.
 *
 * Below is an implementation of this modified merge sort.
 */
public class Ex8 {
    public static class Merge {
        private static Comparable[] aux; // auxiliary array for merges

        public static void sort(Comparable[] a) {
            aux = new Comparable[a.length]; // Allocate space just once.
            sort(a, 0, a.length - 1);
            assert isSorted(a);
        }

        private static void sort(Comparable[] a, int lo, int hi) { // Sort a[lo..hi].
            if (hi <= lo) return;
            int mid = lo + (hi - lo) / 2;
            sort(a, lo, mid); // Sort left half.
            sort(a, mid + 1, hi); // Sort right half.
            if (less(a[mid + 1], a[mid])) {
                merge(a, lo, mid, hi); // Merge results
            }
        }

        public static void merge(Comparable[] a, int lo, int mid, int hi) { // Merge a[lo..mid] with a[mid+1..hi].
            assert isSorted(a, lo, mid);
            assert isSorted(a, mid + 1, hi);
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
                aux[k] = a[k];
            for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i])) a[k] = aux[j++];
                else a[k] = aux[i++];
            assert isSorted(a, lo, hi);
        }

        // is v < w ?
        private static boolean less(Comparable v, Comparable w) {
            return v.compareTo(w) < 0;
        }

        private static boolean isSorted(Comparable[] a) {
            return isSorted(a, 0, a.length - 1);
        }

        private static boolean isSorted(Comparable[] a, int lo, int hi) {
            for (int i = lo + 1; i <= hi; i++)
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

    /**
     * Reads in a sequence of strings from standard input; mergesorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = InFactory.get(args, Ex8.class);
        String[] a = in.readAllStrings();
        Merge.sort(a);
        Merge.show(a);
    }
}
