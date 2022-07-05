package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.interfaces.IIntSearch;
import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex10 {

    public static class BinarySearchMinIndex implements IIntSearch {
        public int rank(int key, int[] a) {
            int lo = 0;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (key > a[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return key == a[lo] ? lo : -1;
        }
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex10.class);
        int n = in.readInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }

        IIntSearch binarySearchMinIndex = new BinarySearchMinIndex();

        while (!in.isEmpty()) {
            int key = in.readInt();
            StdOut.println(String.format("%11d %d", key, binarySearchMinIndex.rank(key, a)));
        }
    }

}
