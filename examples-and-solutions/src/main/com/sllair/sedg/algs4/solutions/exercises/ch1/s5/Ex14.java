package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class Ex14 {

    private final int[] parent;
    private final int[] height;
    private int count;
    private int maxHeight;


    public Ex14(int n) {
        this.parent = new int[n];
        this.height = new int[n];
        this.count = n;
        this.maxHeight = 0;

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
        }

    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        while (p != this.parent[p]) {
            p = this.parent[p];
        }

        return p;
    }

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public void union(int p, int q) {
        int rootP = this.find(p);
        int rootQ = this.find(q);

        if (this.height[rootP] == this.height[rootQ]) {
            this.parent[rootQ] = rootP;
            this.height[rootP]++;
            this.maxHeight = Math.max(this.maxHeight, this.height[rootP]);
        } else if (this.height[rootP] > this.height[rootQ]) {
            this.parent[rootQ] = rootP;
        } else {
            this.parent[rootP] = rootQ;
        }

        count--;
    }

    private int height() {
        return this.maxHeight;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex14.class);

        int expectedHeight = -1;
        int expectedCount = -1;

        if (args.length > 0 && args[0].contains("_test")) {
            if (args[0].contains("_test")) {
                expectedHeight = in.readInt();
                expectedCount = in.readInt();
            }
        }

        int n = in.readInt();
        Ex14 weightedQuickUnionByHeight = new Ex14(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!weightedQuickUnionByHeight.connected(p, q)) {
                weightedQuickUnionByHeight.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", weightedQuickUnionByHeight.count()));

        if (expectedHeight >= 0) {
            if (weightedQuickUnionByHeight.height() == expectedHeight) {
                StdOut.println(String.format("Has a height of %d, as expected!", expectedHeight));
            } else {
                StdOut.println(String.format("Error! Has a height of %d but a height of %d was expected!",
                        weightedQuickUnionByHeight.height(),
                        expectedHeight
                ));
            }
        }

        if (expectedCount >= 0) {
            if (weightedQuickUnionByHeight.count() == expectedCount) {
                StdOut.println(String.format("Has a count of %d, as expected!", expectedCount));
            } else {
                StdOut.println(String.format("Error! Has a count of %d but a count of %d was expected!",
                        weightedQuickUnionByHeight.count(),
                        expectedCount
                ));
            }
        }
    }

}
