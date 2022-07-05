package com.sllair.sedg.algs4.examples.ch2.s2;
/******************************************************************************
 *  Compilation:  javac MergeWorstCase.java
 *  Execution:    java MergeWorstCase n
 *  Dependencies: StdOut.java StdIn.java
 *
 *  Create a worst-case input for mergesort that makes the maximum
 *  number of compares possible.
 *
 *  % java MergeWorstCase 8
 *  [0, 2, 1, 6, 4, 5, 3, 7]
 *
 *  % java MergeWorstCase 16
 *  [0, 2, 1, 6, 4, 5, 3, 14, 8, 10, 9, 13, 11, 12, 7, 15]
 *
 *  % java MergeWorstCase 32
 *  [0, 2, 1, 6, 4, 5, 3, 14, 8, 10, 9, 13, 11, 12, 7, 30, 16, 18, 17, 22, 20, 21, 19, 29, 24, 26, 25, 28, 23, 27, 15, 31]
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class MergeWorstCase {

    public static void worst(int[] a) {
        worst(a, 0, a.length - 1);
    }

    // precondition a[lo..hi] contains distinct integers, with largest at a[hi]
    private static void worst(int[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;

        // put second largest integer at a[mid]
        int max = lo;
        for (int i = lo + 1; i < hi; i++) {
            if (a[i] > a[max]) max = i;
        }
        exch(a, max, mid);
        worst(a, lo, mid);
        worst(a, mid + 1, hi);
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] a = new int[n];
        for (int i = 0; i < n; i++)
            a[i] = i;
        worst(a);
        StdOut.println(Arrays.toString(a));
    }
}
