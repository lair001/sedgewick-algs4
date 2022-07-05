package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Ex12 {

    public static Queue<Integer> numsInBoth(int[] aSorted, int[] bSorted) {
        int ap = 0;
        int bp = 0;

        Integer lastSeen = null;

        Queue<Integer> result = new Queue<>();

        while (ap < aSorted.length && bp < bSorted.length) {
            int ea = aSorted[ap];
            int eb = bSorted[bp];

            if (ea == eb && (lastSeen == null || !lastSeen.equals(ea))) {
                result.enqueue(ea);
                lastSeen = ea;

                ++ap;
                ++bp;
            } else if (ea < eb) {
                ++ap;
            } else {
                ++bp;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex12.class);
        int N = in.readInt();

        int[] a = new int[N];
        int[] b = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = in.readInt();
        }
        /*
         * The problem says to assume that the arrays
         * are sorted, but I'm putting these sorts in
         * here so that there are minimal requirements
         * on the input the program can accept. A good
         * sorting algorithm should spend constant time
         * on data that is already sorted.
         *
         * The method numsInBoth should only take sorted
         * arrays and will run in ~2N worst case time.
         */
        Arrays.sort(a);

        for (int i = 0; i < N; i++) {
            b[i] = in.readInt();
        }
        Arrays.sort(b);

        StdOut.println(numsInBoth(a, b));
    }

}
