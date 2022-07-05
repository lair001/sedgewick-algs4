package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class ExW2 {

    private static final String indicesLabel = "0 1 2 3 4 5 6 7 8 9";
    private static final String divider = "-------------------";

    private static void printParent(Ex13 compressedWeightedQuickUnion) {
        StdOut.println(ExW2.indicesLabel);
        StdOut.println(ExW2.divider);

        StringBuilder values = new StringBuilder();

        for (int i : compressedWeightedQuickUnion.copyParent()) {
            values.append(i);
            values.append(' ');
        }

        StdOut.println(values.toString());
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW2.class);

        int n = in.readInt();
        Ex13 compressedWeightedQuickUnion = new Ex13(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!compressedWeightedQuickUnion.connected(p, q)) {
                compressedWeightedQuickUnion.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
                ExW2.printParent(compressedWeightedQuickUnion);
                StdOut.println();
            }
        }
        StdOut.println(String.format("%d components", compressedWeightedQuickUnion.count()));
        ExW2.printParent(compressedWeightedQuickUnion);
    }

}
