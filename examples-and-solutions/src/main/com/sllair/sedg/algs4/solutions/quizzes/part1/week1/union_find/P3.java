package com.sllair.sedg.algs4.solutions.quizzes.part1.week1.union_find;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Successor with delete. Given a set of nn integers S={ 0, 1, ..., n−1} and a sequence of requests of
 * the following form:
 *
 * +  Remove xx from SS
 * +  Find the successor of x: the smallest y in S such that y ≥ x.
 *
 * design a data type so that all operations (except construction)  take logarithmic time or better in the worst case.
 *
 */
public class P3 {

    private enum Command {

        REMOVE("r", "remove"),
        SUCCESSOR("s", "successor");

        private final String value;
        private final String label;

        Command(String value, String label) {
            this.value = value;
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public static Command parse(String value) {
            for (Command cmd : Command.values()) {
                if (cmd.value.equals(value)) {
                    return cmd;
                }
            }
            return null;
        }

    }

    private final static String CMD_OUTPUT_FMT = "%-9s %10d %s";

    int[] parent;
    int[] size;
    int[] largest;
    int count;

    public P3(int n) {

        /*
         * We initialize an extra site to represent when there
         * is no successor for the removed number.
         */
        int sites = n + 1;
        this.parent = new int[sites];
        this.size = new int[sites];
        this.largest = new int[sites];
        this.count = sites;

        for (int i = 0; i < sites; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
            this.largest[i] = i;
        }
    }

    public int count() {
        return this.count;
    }

    private int root(int p) {
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

    private boolean connected(int p, int q) {
        return this.root(p) == this.root(q);
    }

    private void union(int p, int q) {
        int rootP = this.root(p);
        int rootQ = this.root(q);

        if (this.size[rootP] < this.size[rootQ]) {
            this.parent[rootP] = rootQ;
            this.size[rootQ] += this.size[rootP];
            this.largest[rootQ] = Math.max(this.largest[rootP], this.largest[rootQ]);
        } else {
            this.parent[rootQ] = rootP;
            this.size[rootP] += this.size[rootQ];
            this.largest[rootP] = Math.max(this.largest[rootP], this.largest[rootQ]);
        }

        count--;
    }

    private int find(int i) {
        return this.largest[this.root(i)];
    }

    public boolean remove(int x) {
        if (!this.connected(x, x + 1)) {
            this.union(x, x + 1);
            return true;
        } else {
            return false;
        }
    }

    public int successor(int x) {
        int largest = this.find(x);

        /*
         * If the largest is our extra site, we return -1 to indicate
         * that x has no successor left in S
         */
        return largest == this.parent.length - 1 ? -1 : this.find(x);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        int n = in.readInt();
        P3 successorDelete = new P3(n);

        while (!in.isEmpty()) {
            Command command = Command.parse(in.readString());
            int x = in.readInt();

            if (Command.REMOVE.equals(command)) {
                StdOut.println(String.format(CMD_OUTPUT_FMT,
                        command.getLabel(),
                        x,
                        successorDelete.remove(x)
                ));
            } else if (Command.SUCCESSOR.equals(command)) {
                StdOut.println(String.format(CMD_OUTPUT_FMT,
                        command.getLabel(),
                        x,
                        successorDelete.successor(x)
                ));
            }
        }

        StdOut.println(String.format("%d elements remain in S", successorDelete.count() - 1));
    }
}
