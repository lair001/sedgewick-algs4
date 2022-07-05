package com.sllair.sedg.algs4.solutions.quizzes.part1.week1.union_find;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Question Prompt:
 * Union-find with specific canonical element. Add a method find() to the union-find data type so that find(i) returns
 * the largest element in the connected component containing ii. The operations, union(), connected(), and find() should
 * all take logarithmic time or better.
 *
 * For example, if one of the connected components is { 1, 2, 6, 9 }, then the find() method should return 9 for each of
 * the four elements in the connected components.
 */
public class P2 {

    private int[] parent;
    private int[] size;
    private int[] largest;
    private int count;

    public P2(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        this.largest = new int[n];
        this.count = n;

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
            this.largest[i] = i;
        }
    }

    public int count() {
        return this.count;
    }

    /*
     * Confusingly, the find method in the book corresponds to the root method in the lectures.
     */
    public int root(int p) {
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
        return this.root(p) == this.root(q);
    }

    public void union(int p, int q) {
        int rootP = this.root(p);
        int rootQ = this.root(q);

        if (this.size[rootP] < this.size[rootQ]) {
            this.parent[p] = rootQ;
            this.size[rootQ] += this.size[rootP];
            this.largest[rootQ] = Math.max(this.largest[rootP], this.largest[rootQ]);
        } else {
            this.parent[q] = rootP;
            this.size[rootP] += this.size[rootQ];
            this.largest[rootP] = Math.max(this.largest[rootP], this.largest[rootQ]);
        }

        count--;
    }

    /*
     * Not to be confused with the find method in the book!
     */
    public int find(int i) {
        return this.largest[this.root(i)];
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        int n = in.readInt();
        P2 uf = new P2(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(String.format("%d %d %d", p, q, uf.find(p)));
            }
        }
        StdOut.println(String.format("%d components", uf.count()));
    }
}
