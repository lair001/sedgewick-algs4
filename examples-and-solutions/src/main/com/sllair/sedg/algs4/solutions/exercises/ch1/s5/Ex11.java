package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * Weighted Quick Find is the same as Quick Find except that
 * union() always changes the component id of the sites in the
 * smaller component to the id of the larger id. (Quick Find
 * makes no such guarantee.) This doesn't change the order of
 * the time or memory complexities of any of the methods, but
 * it does change some constants. Union will now make N/2
 * array value assignments to the id[] in the worst case instead
 * of N - 1. This comes at the cost of a new size[] containing N
 * integers and additional operations to manage the size[].
 * The net result is a small improvement in runtime for large N
 * while approximately doubling memory requirements.
 *
 * The memory hit could be avoided by omitting the size[] and
 * instead having union() compute the size of the components
 * by iterating through the id[] array. However, runtime would
 * then be worse than that of Quick Find since that would
 * introduce N additional array value reads on id[].
 */
public class Ex11 {

    private final int[] id;
    private final int[] size;
    int count;

    public Ex11(int n) {
        id = new int[n];
        size = new int[n];
        count = n;

        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public void union(int p, int q) {
        int largerComp;
        int smallerSmaller;

        if (size[id[p]] > size[id[q]]) {
            largerComp = this.find(p);
            smallerSmaller = this.find(q);
        } else {
            largerComp = this.find(q);
            smallerSmaller = this.find(p);
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == smallerSmaller) {
                id[i] = largerComp;
            }
        }

        size[largerComp] += size[smallerSmaller];
        this.count--;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex11.class);
        int n = in.readInt();

        Ex11 weightedQuickFindUF = new Ex11(n);

        while (!in.isEmpty()) {
            int p = in.readInt();
            int q = in.readInt();

            if (!weightedQuickFindUF.connected(p, q)) {
                weightedQuickFindUF.union(p, q);
                StdOut.println(String.format("%d %d", p, q));
            }
        }
        StdOut.println(String.format("%d components", weightedQuickFindUF.count()));
    }

}
