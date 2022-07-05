package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex18 {

    public static int findALocalMinimum(int[] a) {
        if (a.length == 1) {
            return a[0];
        } else if (a[0] < a[1]) {
            return a[0];
        } else if (a[a.length - 1] < a[a.length - 2]) {
            return a[a.length - 1];
        }

        /*
         * If none of the easy cases work, we'll just
         * have to find a local minimum using binary search.
         */

        int lo = 1;
        int hi = a.length - 2;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (a[mid - 1] < a[mid + 1]) {
                if (a[mid] < a[mid - 1]) {
                    return a[mid];
                } else {
                    hi = mid - 1;
                }
            } else {
                if (a[mid] < a[mid + 1]) {
                    return a[mid];
                } else {
                    lo = mid + 1;
                }
            }
        }
        /*
         * No local minimum found.  Should only happen if all
         * elements are equal.
         */
        return -1;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex16.class);
        StdOut.println(findALocalMinimum(in.readAllInts()));
    }

}
