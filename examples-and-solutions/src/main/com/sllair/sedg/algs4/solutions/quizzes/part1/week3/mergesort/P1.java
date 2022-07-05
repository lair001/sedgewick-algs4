package com.sllair.sedg.algs4.solutions.quizzes.part1.week3.mergesort;

import com.sllair.sedg.algs4.examples.ch2.s2.Merge;
import com.sllair.sedg.algs4.utils.DoubleUtils;
import edu.princeton.cs.algs4.StdOut;

/*
 * Question Prompt:
 * Merging with smaller auxiliary array. Suppose that the subarray a[0] to a[n−1] is sorted and the
 * subarray a[n] to a[2∗n−1] is sorted. How can you merge the two subarrays so that a[0] to a[2∗n−1]
 * is sorted using an auxiliary array of length n (instead of 2n)?
 */
public class P1 {

    public static void mergeTwoSortedSubarraysOfEqualSize(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length / 2];

        for (int i = 0; i < aux.length; ++i) {
            aux[i] = a[i];
        }

        int left = 0;
        int right = aux.length;

        for (int i = 0; i < a.length; ++i) {

            if (left == aux.length) {
                a[i] = a[right++];
            } else if (right == a.length) {
                a[i] = aux[left++];
            } else if (less(a[right], aux[left])) {
                a[i] = a[right++];
            } else {
                a[i] = aux[left++];
            }
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Double[] a1 = DoubleUtils.getRandomArray(n);
        Merge.sort(a1);
        Double[] a2 = DoubleUtils.getRandomArray(n);
        Merge.sort(a2);

        Double[] a = new Double[2 * n];

        for (int i = 0; i < a.length; ++i) {
            if (i < n) {
                a[i] = a1[i];
            } else {
                a[i] = a2[i % n];
            }
        }

        show(a);
        mergeTwoSortedSubarraysOfEqualSize(a);
        StdOut.println();
        show(a);
    }
}
