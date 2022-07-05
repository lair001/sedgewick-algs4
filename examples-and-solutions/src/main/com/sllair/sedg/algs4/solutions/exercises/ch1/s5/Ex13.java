package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex13 {

    private final int[] parent;
    private final int[] size;
    private int count;

    public Ex13(int n) {
        this.parent = new int[n];
        this.size = new int[n];

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }

        this.count = n;
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        int root = p;

        while (root != this.parent[root]) {
            root = this.parent[root];
        }


        while (p != this.parent[p]) {
            int next = this.parent[p];
            this.parent[p] = root;
            p = next;
        }

        return root;
    }

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public void union(int p, int q) {
        int rootP = this.find(p);
        int rootQ = this.find(q);

        if (this.size[rootP] > this.size[rootQ]) {
            this.parent[rootQ] = rootP;
            this.size[rootP] += this.size[rootQ];
        } else {
            this.parent[rootP] = rootQ;
            this.size[rootQ] += this.size[rootP];
        }

        count--;
    }

    private int height() {
        int maxPLen = 0;

        for (int i = 0; i < this.parent.length; i++) {
            int pLen = 0;

            int p = i;
            while (p != this.parent[p]) {
                p = this.parent[p];
                pLen++;
            }

            maxPLen = Math.max(maxPLen, pLen);
        }

        return maxPLen;
    }

    /* helper method to confirm that each file Ex13_has_height4_*.txt
     * produces a height of 4 as desired.
     */
    private boolean hasHeightOf4() {
        return this.height() == 4;
    }

    /*
     * Added and given default visibility to help with Web Exercise 2
     * A copy is made and return to prevent potential bugs due to a
     * client unexpectedly modifying the parent array.
     */
    int[] copyParent() {
        int[] copy = new int[this.parent.length];
        System.arraycopy(this.parent, 0, copy, 0, this.parent.length);
        return copy;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex13.class);

        int n = in.readInt();
        Ex13 compressedWeightedQuickUnion = new Ex13(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!compressedWeightedQuickUnion.connected(p, q)) {
                compressedWeightedQuickUnion.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", compressedWeightedQuickUnion.count()));

        if (args.length > 0 && args[0].contains("height4")) {
            if (compressedWeightedQuickUnion.hasHeightOf4()) {
                StdOut.println("Has a height of 4!");
            } else {
                StdOut.println("Doesn't have a height of 4!");
            }
        }
    }

}
