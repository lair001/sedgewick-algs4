package com.sllair.sedg.algs4.examples.ch1.s3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac MultiwordSearch.java
 *  Execution:    java MultiwordSearch query1 query2 ... < input.txt
 *  Dependencies: Queue.java StdIn.java
 *
 *  Find the shortest interval (number of words) in the input file
 *  that contains the query words in the order specified on the command line.
 *
 ******************************************************************************/

public class MultiwordSearch {
    public static void main(String[] args) {
        String[] words = StdIn.readAllStrings();

        // construct queues[j] = sequence of positions of jth query word
        Queue<Integer>[] queues = (Queue<Integer>[]) new Queue[args.length];
        for (int j = 0; j < args.length; j++) {
            queues[j] = new Queue<Integer>();
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < args.length; j++) {
                if (words[i].equals(args[j])) queues[j].enqueue(i);
            }
        }

        // repeatedly find smallest interval starting at position of queues[0]
        boolean done = false;
        int bestlo = -1, besthi = words.length;
        while (!queues[0].isEmpty()) {
            int lo = queues[0].dequeue();
            int hi = lo;
            for (int j = 1; j < args.length; j++) {
                while (!queues[j].isEmpty() && queues[j].peek() <= hi) {
                    queues[j].dequeue();
                }
                if (queues[j].isEmpty()) {
                    done = true;
                    break;
                } else hi = queues[j].peek();
            }
            if (!done && hi - lo < besthi - bestlo) {
                besthi = hi;
                bestlo = lo;
            }

        }

        if (bestlo >= 0) {
            for (int i = bestlo; i <= besthi; i++)
                StdOut.print(words[i] + " ");
            StdOut.println();
        } else
            StdOut.println("NOT FOUND");
    }
}

