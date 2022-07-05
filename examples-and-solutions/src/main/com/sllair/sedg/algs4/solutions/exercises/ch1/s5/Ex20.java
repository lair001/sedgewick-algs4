package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex20 {

    private int[] parent;
    private int[] height;
    private int count;
    private int numSites;
    private int maxHeight;

    public Ex20(int initialCapacity) {
        this.parent = new int[initialCapacity];
        this.height = new int[initialCapacity];
        this.count = 0;
        this.numSites = 0;
        this.maxHeight = 0;
    }

    public Ex20() {
        this(32);
    }

    private int numSites() {
        return this.numSites;
    }

    public int newSite() {
        this.doubleCapacityIfNeeded();

        int site = this.numSites();

        this.parent[site] = site;

        this.count++;
        this.numSites++;

        return site;
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        int root = p;

        while (root != this.parent[root]) {
            root = this.parent[root];
        }

        int next = this.parent[p];
        while (p != next) {
            this.parent[p] = root;
            p = next;
            next = this.parent[p];
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public void union(int p, int q) {
        int rootP = this.find(p);
        int rootQ = this.find(q);

        if (this.height[rootP] > this.height[rootQ]) {
            this.parent[rootQ] = rootP;
        } else if (this.height[rootQ] > this.height[rootP]) {
            this.parent[rootP] = rootQ;
        } else {
            this.parent[rootQ] = rootP;
            this.height[rootP]++;
            this.maxHeight = Math.max(this.maxHeight, this.height[rootP]);
        }
        count--;
    }

    private int height() {
        return this.maxHeight;
    }

    private void doubleCapacityIfNeeded() {
        if (this.parent.length <= this.numSites) {
            int[] newParent = new int[2 * this.numSites];

            for (int i = 0; i < this.parent.length; i++) {
                newParent[i] = this.parent[i];
            }

            this.parent = newParent;
        }

        if (this.height.length <= this.numSites) {
            int[] newHeight = new int[2 * this.numSites];

            for (int i = 0; i < this.height.length; i++) {
                newHeight[i] = this.height[i];
            }

            this.height = newHeight;
        }
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex20.class);
        int expectedHeight = -1;
        int expectedCount = -1;


        if (args.length > 0) {
            if (args[0].contains("_test")) {
                expectedHeight = in.readInt();
                expectedCount = in.readInt();
            }
        }

        int n = in.readInt();

        Ex20 dynamicWeightedQuickUnion;

        if (expectedHeight >= 0 || expectedCount >= 0) {
            // init capacity fixed at 32 so we can test resizing
            dynamicWeightedQuickUnion = new Ex20();
        } else {
            dynamicWeightedQuickUnion = new Ex20(n);
        }

        for (int i = 0; i < n; i++) {
            dynamicWeightedQuickUnion.newSite();
        }

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!dynamicWeightedQuickUnion.connected(p, q)) {
                dynamicWeightedQuickUnion.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", dynamicWeightedQuickUnion.count()));

        if (expectedHeight >= 0) {
            if (dynamicWeightedQuickUnion.height() == expectedHeight) {
                StdOut.println(String.format("Has a height of %d, as expected!", expectedHeight));
            } else {
                StdOut.println(String.format("Error! Has a height of %d but a height of %d was expected!",
                        dynamicWeightedQuickUnion.height(),
                        expectedHeight
                ));
            }
        }

        if (expectedCount >= 0) {
            if (dynamicWeightedQuickUnion.count() == expectedCount) {
                StdOut.println(String.format("Has a count of %d, as expected!", expectedCount));
            } else {
                StdOut.println(String.format("Error! Has a count of %d but a count of %d was expected!",
                        dynamicWeightedQuickUnion.count(),
                        expectedCount
                ));
            }
        }
    }

}
