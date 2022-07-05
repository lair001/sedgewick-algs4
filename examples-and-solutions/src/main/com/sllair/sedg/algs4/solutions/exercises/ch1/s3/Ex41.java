package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class Ex41 {

    public static class IterativeCopy<Item> extends Queue<Item> {
        IterativeCopy() {
            super();
        }

        IterativeCopy(Queue<Item> q) {
            this();
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Item curr = q.dequeue();
                q.enqueue(curr);
                this.enqueue(curr);
            }
        }
    }

    public static class RecursiveCopy<Item> extends Queue<Item> {
        RecursiveCopy() {
            super();
        }

        RecursiveCopy(Queue<Item> q) {
            this();
            recursiveConstruction(q, 0);
        }

        /*
         * Unlike some languages like C++ and Scala, Java does not support tail recursion.
         * Unfortunately, this version will get pushed to the call stack unnecessarily increasing
         * the memory complexity to ~N. Use an iterative solution instead, which has ~1 memory
         * complexity.
         */
        private void recursiveConstruction(Queue<Item> q, int i) {
            if (i == q.size()) {
                return;
            }

            Item curr = q.dequeue();
            q.enqueue(curr);
            this.enqueue(curr);

            recursiveConstruction(q, ++i);
        }
    }

    private static <Item> Queue<Item> copyQueue(String[] args, Queue<Item> q) {
        if (args.length < 1 || "i".equals(args[0])) {
            return new IterativeCopy<>(q);
        } else if ("r".equals(args[0])) {
            return new RecursiveCopy<>(q);
        } else {
            return null;
        }
    }

    private static <Item> void println(Queue<Item> q) {
        println(q, null, null);
    }

    private static <Item> void println(Queue<Item> q, Queue<Item> icq, Queue<Item> rcq) {
        StdOut.println("Original");
        StdOut.println(q);
        if (icq != null) {
            StdOut.println("Iterative Copy");
            StdOut.println(icq);
        }
        if (rcq != null) {
            StdOut.println("Recursive Copy");
            StdOut.println(rcq);
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        for (int i = 0; i < 10; i++) {
            q.enqueue(i);
        }
        println(q);

        StdOut.println("Copying ...");
        Queue<Integer> icq = new IterativeCopy<>(q);
        Queue<Integer> rcq = new RecursiveCopy<>(q);
        println(q, icq, rcq);

        StdOut.println(String.format("Dequeue original: %d", q.dequeue()));
        println(q, icq, rcq);

        StdOut.println(String.format("Dequeue iterative copy: %d", icq.dequeue()));
        println(q, icq, rcq);

        StdOut.println(String.format("Dequeue recursive copy: %d", rcq.dequeue()));
        println(q, icq, rcq);

        StdOut.println("Enqueue original: 10");
        q.enqueue(10);
        println(q, icq, rcq);

        StdOut.println("Enqueue iterative copy: 11");
        icq.enqueue(11);
        println(q, icq, rcq);

        StdOut.println("Enqueue recursive copy: 12");
        rcq.enqueue(12);
        println(q, icq, rcq);
    }

}
