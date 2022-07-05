package com.sllair.sedg.algs4.solutions.quizzes.part1.week1.union_find;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Question Prompt:
 * Social network connectivity. Given a social network containing nn members and a log file containing mm timestamps at
 * which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all
 * members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log
 * file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should
 * be m * lg(n) or better and use extra space proportional to n.
 */
public class P1 {
    /*
     *  I'm going to assume that timestamps are long variables
     *  containing the number of milliseconds since the beginning
     *  of January 1, 1970. See, e.g., java.lang.System.currentTimeMillis()
     */

    int[] parent;
    int[] size;
    int count;
    long earliestTimeOfCompleteConnection;

    public P1(int n) {
        this.parent = new int[n];
        this.size = new int[n];
        this.count = n;
        this.earliestTimeOfCompleteConnection = Long.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.size[i] = 1;
        }
    }

    public int count() {
        return count;
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

    public void union(int p, int q, long time) {
        int rootP = this.find(p);
        int rootQ = this.find(q);

        if (this.size[rootP] < this.size[rootQ]) {
            this.parent[rootP] = rootQ;
            this.size[rootQ] += this.size[rootP];
        } else {
            this.parent[rootQ] = rootP;
            this.size[rootP] += this.size[rootQ];
        }

        this.count--;

        if (this.count == 1) {
            this.earliestTimeOfCompleteConnection = time;
        }
    }

    public long earliestTimeOfCompleteConnection() {
        return this.earliestTimeOfCompleteConnection;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);

        long expectedEarliestTimeOfCompleteConnection = in.readLong();
        long actualEarliestTimeOfCompleteConnection = Long.MIN_VALUE;
        int n = in.readInt();
        P1 uf = new P1(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();
            long time = in.readLong();

            if (!uf.connected(p, q)) {
                uf.union(p, q, time);
            }

            if (uf.earliestTimeOfCompleteConnection() > Long.MIN_VALUE) {
                actualEarliestTimeOfCompleteConnection = uf.earliestTimeOfCompleteConnection();
                break;
            }
        }

        Date actualCompletionDate = new Date(actualEarliestTimeOfCompleteConnection);
        Date expectedCompletionDate = new Date(expectedEarliestTimeOfCompleteConnection);
        Format format = new SimpleDateFormat("EEE MMM dd, yyyy 'at' HH:mm:ss z");
        if (actualEarliestTimeOfCompleteConnection > Long.MIN_VALUE) {
            StdOut.println(String.format("Everyone became connected with each other on %s!",
                    format.format(actualCompletionDate)));
        } else {
            StdOut.println("Everyone isn't connected to each other yet. Bummer!");
        }

        if (expectedEarliestTimeOfCompleteConnection == actualEarliestTimeOfCompleteConnection) {
            StdOut.println(String.format(
                    "Everyone became connected with each other on %s, as expected!",
                    format.format(actualCompletionDate)
            ));
        } else if (actualEarliestTimeOfCompleteConnection == Long.MIN_VALUE) {
            StdOut.println("Error! Everyone isn't connected to each other yet, but ...");
            StdOut.println(String.format(
                    "we expected this to occur on %s.",
                    format.format(expectedCompletionDate)
            ));
        } else {

            StdOut.println(String.format(
                    "Error! Everyone became connected with each other on %s, but ...",
                    format.format(actualCompletionDate)
            ));
            StdOut.println(String.format(
                    "we expected this to occur on %s instead.",
                    format.format(expectedCompletionDate)
            ));
        }
    }
}
