package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import com.sllair.sedg.algs4.utils.StringUtils;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/*
 * There's a solution to this exercise on the booksite, so I spiced up my
 * solution by making it an example of customizing a standard data structure
 * for the given problem.
 */
public class Ex37 {

    public static class Josephus {

        private static class CircularQueue {
            private static class Node {
                int item;
                Node next;
            }

            private Node last;
            private Node prev;
            private int size;

            public int size() {
                return this.size;
            }

            public boolean isEmpty() {
                return this.last == null;
            }

            public void enqueue(int i) {
                Node newNode = new Node();
                newNode.item = i;
                if (this.isEmpty()) {
                    newNode.next = newNode;
                } else {
                    newNode.next = this.last.next;
                    this.last.next = newNode;
                }
                this.last = newNode;
                /*
                 * This would break if we interleaved enqueue and dequeue.
                 * However, this isn't a concern as the Josephus problem is
                 * simply a sequence of enqueues followed by a sequence of
                 * dequeues.
                 */
                this.prev = this.last;
                this.size++;
            }

            public int dequeue(int jumps) {
                for (int j = 0; j < jumps - 1; j++) {
                    this.prev = this.prev.next;
                }
                int result = this.prev.next.item;
                this.prev.next = this.prev.next.next;
                this.size--;

                return result;
            }
        }
    }

    public static void main(String[] args) {
        int jumps;
        int N;

        if (args.length > 1 && StringUtils.isInteger(args[0]) && StringUtils.isInteger(args[1])) {
            jumps = Integer.parseInt(args[0]);
            N = Integer.parseInt(args[1]);
        } else {
            In in = InFactory.get(args, Ex37.class);
            jumps = in.readInt();
            N = in.readInt();
        }

        Josephus.CircularQueue queue = new Josephus.CircularQueue();

        for (int i = 0; i < N; i++) {
            queue.enqueue(i);
        }

        while (queue.size > 1) {
            StdOut.print(queue.dequeue(jumps));
            if (queue.size > 1) {
                StdOut.print(' ');
            }
        }

        StdOut.print('\n');
        StdOut.println(String.format("survivor %d", queue.dequeue(jumps)));
    }

}
