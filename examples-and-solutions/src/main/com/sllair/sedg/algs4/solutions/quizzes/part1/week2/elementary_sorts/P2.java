package com.sllair.sedg.algs4.solutions.quizzes.part1.week2.elementary_sorts;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Shell;
import edu.princeton.cs.algs4.StdOut;

/*
 * Question Prompt:
 * Permutation. Given two integer arrays of size nn, design a subquadratic algorithm to determine whether one is a
 * permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.
 */
public class P2 {
    public static boolean isPermutation(Integer[] a, Integer[] b) {
        if (a.length != b.length) {
            return false;
        }

        Shell.sort(a);
        Shell.sort(b);

        for (int i = 0; i < a.length; i++) {
            if (!a[i].equals(b[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, P2.class);

        int n = in.readInt();

        Integer[] a = new Integer[n];

        for (int i = 0; i < n; ++i) {
            a[i] = in.readInt();
        }

        Integer[] b = new Integer[n];

        for (int i = 0; i < n; ++i) {
            b[i] = in.readInt();
        }

        StdOut.println(isPermutation(a, b));
    }

}
