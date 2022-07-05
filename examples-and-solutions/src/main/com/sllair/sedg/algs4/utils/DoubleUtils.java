package com.sllair.sedg.algs4.utils;

import edu.princeton.cs.algs4.StdRandom;

public class DoubleUtils {

    public static Double[] getRandomArray(int N) {
        Double[] a = new Double[N];

        for (int i = 0; i < N; ++i) {
            a[i] = StdRandom.uniform();
        }

        return a;
    }

}
