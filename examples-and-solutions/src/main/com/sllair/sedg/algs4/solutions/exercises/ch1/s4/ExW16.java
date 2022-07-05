package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ExW16 {

    /*
     * See nihaldps's response on this thread:
     * https://www.careercup.com/question?id=12849666
     *
     * The worst case is if the last row is all
     * zeros. You'd check N rows reaching it and N
     * columns reaching its end for a worst case runtime
     * of ~2N (or O(N)).
     */
    public static int findRowWithMostZeroes(int a[][]) {
        int row = 0;
        int col = a.length - 1;
        int result = row;

        while (row <= a.length - 1 && col >= 0) {
            if (a[row][col] == 0) {
                --col;
                result = row;
            } else {
                ++row;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW16.class);

        int N = in.readInt();

        int[][] a = new int[N][];

        for (int row = 0; row < N; row++) {
            a[row] = new int[N];
            for (int col = 0; col < N; col++) {
                a[row][col] = in.readInt();
            }
        }
        StdOut.println(findRowWithMostZeroes(a));
    }

}
