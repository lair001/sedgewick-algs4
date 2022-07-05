package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class ExW6 {

    int[] parent;
    int[] label;
    int count;

    public ExW6(int n) {
        this.parent = new int[n];
        this.label = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.label[i] = StdRandom.uniform(n);
        }
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

        if (this.label[p] > this.label[q]) {
            this.parent[rootQ] = rootP;
        } else {
            this.parent[rootP] = rootQ;
        }

        count--;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW6.class);

        int n = in.readInt();
        ExW6 compressedRandomQuickUnion = new ExW6(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!compressedRandomQuickUnion.connected(p, q)) {
                compressedRandomQuickUnion.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", compressedRandomQuickUnion.count()));
    }

}
