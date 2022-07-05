package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ExW2 {

    private static int ceiling(int x, int[] a) {
        int lo = 0;
        int hi = a.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (a[mid] >= x) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return a[lo] >= x ? a[lo] : Integer.MIN_VALUE;
    }

    private static int floor(int x, int[] sortedArray) {
        int lo = 0;
        int hi = sortedArray.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (sortedArray[mid] <= x) {
                if (lo == mid) {
                    return sortedArray[mid + 1] <= x ? sortedArray[mid + 1] :
                            sortedArray[mid];
                } else {
                    lo = mid;
                }
            } else {
                hi = mid - 1;
            }
        }
        return sortedArray[lo] <= x ? sortedArray[lo] : Integer.MAX_VALUE;
    }

    public static int[] floorAndCeiling(int x, int[] sortedArray) {
        return new int[]{floor(x, sortedArray), ceiling(x, sortedArray)};
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, 1, Ex22.class);

        int n = in.readInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }

        while (!in.isEmpty()) {
            int x = in.readInt();
            int[] floorAndCeiling = floorAndCeiling(x, a);
            StdOut.println(String.format("%d %d %d", x, floorAndCeiling[0], floorAndCeiling[1]));
        }
    }

}
