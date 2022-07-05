package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex17 {

    public static double[] findFarthestPair(double[] a) {
        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        return new double[]{min, max};
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex16.class);
        double[] result = findFarthestPair(in.readAllDoubles());
        StdOut.println(String.format("%f %f", result[0], result[1]));
    }

}
