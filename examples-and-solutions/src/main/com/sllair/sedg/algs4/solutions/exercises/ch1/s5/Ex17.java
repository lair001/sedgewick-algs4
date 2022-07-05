package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import com.sllair.sedg.algs4.utils.StringUtils;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex17 {

    public static int count(int n) {

        /*
         * Using Ex13 since it implements size weighted quick
         * union with path compression, one of the fastest
         * versions of union find
         */
        Ex13 uf = new Ex13(n);

        int numPairs = 0;

        while (uf.count() > 1) {
            numPairs++;

            int p = StdRandom.uniform(n);
            int q = StdRandom.uniform(n);

            if (!uf.connected(p, q)) {
                uf.union(p, q);
            }
        }

        return numPairs;
    }

    public static void main(String[] args) {
        int n;
        if (args.length > 0 && StringUtils.isInteger(args[0])) {
            n = Integer.parseInt(args[0]);
        } else {
            In in = InFactory.get(args, Ex17.class);
            n = in.readInt();
        }
        StdOut.println(Ex17.count(n));
    }

}
