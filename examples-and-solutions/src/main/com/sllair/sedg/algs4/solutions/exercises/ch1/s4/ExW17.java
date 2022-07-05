package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ExW17 {
    /*
     * Thanks to Geek for Geeks for saving me on this one:
     * https://www.geeksforgeeks.org/search-in-row-wise-and-column-wise-sorted-matrix/
     *
     * Maybe I should save difficult "bonus" problems for review after I finish the course.
     */
    public static boolean isIn(int x, int[][] monotone) {
        int row = 0;
        int col = monotone.length - 1;

        while (row <= monotone.length - 1 && col >= 0) {
            if (x == monotone[row][col]) {
                /*
                 * Would be easy to return the coords
                 * but the problem just wants us to
                 * determine whether x is in the array.
                 */
                return true;
            } else if (x > monotone[row][col]) {
                ++row;
            } else {
                --col;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW16.class);

        int x = in.readInt();

        int N = in.readInt();

        int[][] a = new int[N][];

        for (int row = 0; row < N; row++) {
            a[row] = new int[N];
            for (int col = 0; col < N; col++) {
                a[row][col] = in.readInt();
            }
        }
        StdOut.println(isIn(x, a));
    }

}
