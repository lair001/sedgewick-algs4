package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex8 {

    public static int getNumOfEqualPairs(int[] a) {
        int result = 0;

        Arrays.sort(a);

        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
                result++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex8.class);
        StdOut.println(getNumOfEqualPairs(in.readAllInts()));
    }

}
