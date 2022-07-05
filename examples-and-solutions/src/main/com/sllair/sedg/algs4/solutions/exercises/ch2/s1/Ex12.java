package com.sllair.sedg.algs4.solutions.exercises.ch2.s1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.*;

/*
 * Running this program, we can see that the compares:n ratio converges for small increments.
 * E.g. 2.8 for h = 1 and 4.8 for h = 4. The larger increments probably converge as well but
 * at a value of N that exhausts my patience and my computer's memory.
 */

public class Ex12 {

    private static final String DIVIDER = String.join("", Collections.nCopies(53, "-"));

    private static class CompareRecord {
        public CompareRecord(int arraySize) {
            this.arraySize = arraySize;
        }

        public int arraySize;
        public int compares;

        public double getComparesDividedByN() {
            return this.compares / (double) this.arraySize;
        }
    }

    public static void sort(Comparable[] a, Map<Integer, List<CompareRecord>> records) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;

        while (h >= 1) {
            CompareRecord record = new CompareRecord(n);
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h], record); j -= h) {
                    exch(a, j, j - h);
                }
            }
            if (!records.containsKey(h)) {
                records.put(h, new ArrayList<>());
            }
            records.get(h).add(record);
            assert isHsorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }


    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    // is v < w ?
    private static boolean less(Comparable v, Comparable w, CompareRecord record) {
        if (record != null) {
            ++record.compares;
        }
        return v.compareTo(w) < 0;
    }

    private static boolean less(Comparable v, Comparable w) {
        return less(v, w, null);
    }

    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean isHsorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i - h])) return false;
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    private static Double[] getArray(int n) {
        Double[] a = new Double[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(0.0, 1.0);
        }
        return a;
    }

    public static void main(String[] args) {
        int n = 100;
        int T = 200;
        while (n < 100000000) {
            StdOut.println(DIVIDER);
            StdOut.println(n);
            StdOut.println(DIVIDER);
            Map<Integer, List<CompareRecord>> records = new TreeMap<>();
            for (int i = 0; i < T; i++) {
                Ex12.sort(getArray(n), records);
            }

            for (Map.Entry<Integer, List<CompareRecord>> entry : records.entrySet()) {
                StdOut.println(String.format("%9d %.3f", entry.getKey(), computeRatioAverage(entry.getValue())));
            }

            n *= 10;
        }
    }

    private static double computeRatioAverage(List<CompareRecord> records) {
        double sum = 0;
        for (CompareRecord record : records) {
            sum += record.getComparesDividedByN();
        }

        return sum / records.size();
    }

}
