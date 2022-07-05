package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex16 {

    public static double[] findClosestPair(double[] a) {
        double[] result = new double[2];
        double smallestDiff = Double.MAX_VALUE;

        Arrays.sort(a);

        for (int i = 0; i < a.length - 1; i++) {
            double diff = a[i + 1] - a[i];
            if (diff < smallestDiff) {
                smallestDiff = diff;
                result[0] = a[i];
                result[1] = a[i + 1];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex16.class);
        double[] result = findClosestPair(in.readAllDoubles());
        StdOut.println(String.format("%f %f", result[0], result[1]));
    }

}
