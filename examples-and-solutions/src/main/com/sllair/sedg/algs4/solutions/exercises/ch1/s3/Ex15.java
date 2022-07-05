package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;


public class Ex15 {

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex15.class);

        int k = in.readInt();

        Queue<String> queue = new Queue<>();

        while (!in.isEmpty()) {
            /*
             * Assuming that k = 0 means to return the last element
             * and k = 1 means to return next to last element
             */
            if (queue.size() == k + 1) {
                queue.dequeue();
            }
            queue.enqueue(in.readString());
        }

        if (queue.size() < k) {
            StdOut.println(String.format("There are fewer than %d string in the input!", k));
        } else {
            StdOut.println(String.format("%d%s to last string:", k, getOrdinalSuffix(k)));
            StdOut.println(queue.dequeue());
        }
    }

    private static String getOrdinalSuffix(int k) {
        int kModulo10 = k % 10;
        int kDivide10 = k / 10;
        if (kModulo10 == 1 && kDivide10 != 1) {
            return "st";
        } else if (kModulo10 == 2 && kDivide10 != 1) {
            return "nd";
        } else {
            return "th";
        }
    }
}
