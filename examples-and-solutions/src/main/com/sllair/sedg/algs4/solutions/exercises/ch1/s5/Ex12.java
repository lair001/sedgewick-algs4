package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex12 {

    private final int[] parent;
    private int count;

    public Ex12(int n) {
        this.parent = new int[n];

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
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

        if (rootP == rootQ) {
            return;
        }

        this.parent[rootQ] = rootP;

        this.count--;
    }

    /* helper method to confirm that each file Ex12_has_path4_*.txt
     * produces a path of length 4 as desired.
     */
    private boolean hasPathOfLen4() {
        for (int i = 0; i < this.parent.length; i++) {
            int pLen = 0;

            int p = i;
            while (p != this.parent[p]) {
                p = this.parent[p];
                pLen++;
            }

            if (pLen == 4) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex12.class);

        int n = in.readInt();
        Ex12 compressedQuickUnion = new Ex12(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!compressedQuickUnion.connected(p, q)) {
                compressedQuickUnion.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", compressedQuickUnion.count()));

        if (args.length > 0 && args[0].contains("path4")) {
            if (compressedQuickUnion.hasPathOfLen4()) {
                StdOut.println("Has a path of length 4!");
            } else {
                StdOut.println("Doesn't have a path of length 4!");
            }
        }
    }

}
