package com.sllair.sedg.algs4.examples.ch2.s2;

/******************************************************************************
 *  Compilation:  javac MergeSizes.java
 *  Execution:    java MergeSizes n
 *  Dependencies: StdOut.java
 *
 *  Prints the sequence of subarrays after each merging operation in both
 *  top-down and bottom-up mergesort for an array of length n.
 *
 *  % java MergeSizes 39
 *  2 3 2 5 2 3 2 5 10 2 3 2 5 2 3 2 5 10 20 2 3 2 5 2 3 2 5 10 2 3 2 5 2 2 4 9 19 39
 *  2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 2 4 4 4 4 4 4 4 4 4 3 8 8 8 8 7 16 16 32 39
 *
 ******************************************************************************/


public class MergeSizes {

    // print size of resuting subarray
    private static void merge(int lo, int mid, int hi) {
        System.out.print(hi - lo + 1);
        System.out.print(" ");
    }

    // top-down mergesort a[lo..hi]
    private static void topDownMergesort(int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        topDownMergesort(lo, mid);
        topDownMergesort(mid + 1, hi);
        merge(lo, mid, hi);
    }

    public static void topDownMergesort(Comparable[] a) {
        topDownMergesort(0, a.length - 1);
    }

    public static void bottomUpMergesort(Comparable[] a) {
        int n = a.length;
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n - len; lo += len + len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + len + len - 1, n - 1);
                merge(lo, mid, hi);
            }
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        String[] a = new String[n];
        MergeSizes.topDownMergesort(a);
        System.out.println();
        MergeSizes.bottomUpMergesort(a);
        System.out.println();
    }
}
