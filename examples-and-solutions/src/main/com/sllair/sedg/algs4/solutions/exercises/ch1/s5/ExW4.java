package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * I'm using weight by height/rank so we can test using the
 * test cases developed for Ex14 and Ex20
 */
public class ExW4 {

    private final int[] parent;
    private final int[] rank;
    private int count;
    private int maxRank;

    public ExW4(int n) {
        this.parent = new int[n];

        for (int i = 1; i < n; i++) {
            this.parent[i] = i;
        }

        this.rank = new int[n];
        this.count = n;
        this.maxRank = 0;
    }

    public int count() {
        return this.count;
    }

    public int maxRank() {
        return this.maxRank;
    }

    public int find(int p) {
        while (p != this.parent[p]) {
            this.parent[p] = this.parent[this.parent[p]];
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

        if (this.rank[rootP] == this.rank[rootQ]) {
            this.parent[rootQ] = rootP;
            this.rank[rootP]++;
            this.maxRank = Math.max(this.maxRank, this.rank[rootP]);
        } else if (this.rank[rootP] > this.rank[rootQ]) {
            this.parent[rootQ] = rootP;
            this.rank[rootP]++;
        } else {
            this.parent[rootP] = rootQ;
            this.rank[rootQ]++;
        }

        count--;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW4.class);
        int expectedMaxRank = -1;
        int expectedCount = -1;

        if (args.length > 0) {
            if (args[0].contains("_test")) {
                expectedMaxRank = in.readInt();
                expectedCount = in.readInt();
            }
        }

        int n = in.readInt();
        ExW4 weightedQuickUnionPathHalving = new ExW4(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!weightedQuickUnionPathHalving.connected(p, q)) {
                weightedQuickUnionPathHalving.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", weightedQuickUnionPathHalving.count()));

        if (expectedMaxRank >= 0) {
            if (weightedQuickUnionPathHalving.maxRank() == expectedMaxRank) {
                StdOut.println(String.format("Has a max rank of %d, as expected!", expectedMaxRank));
            } else {
                StdOut.println(String.format("Error! Has a max rank of %d but a max rank of %d was expected!",
                        weightedQuickUnionPathHalving.maxRank(),
                        expectedMaxRank
                ));
            }
        }

        if (expectedCount > 0) {
            if (weightedQuickUnionPathHalving.count() == expectedCount) {
                StdOut.println(String.format("Has a count of %d, as expected!", expectedCount));
            } else {
                StdOut.println(String.format("Error! Has a count of %d but a count of %d was expected!",
                        weightedQuickUnionPathHalving.count(),
                        expectedCount
                ));
            }
        }
    }
}
