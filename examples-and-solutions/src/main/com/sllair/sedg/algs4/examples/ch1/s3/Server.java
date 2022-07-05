package com.sllair.sedg.algs4.examples.ch1.s3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac Server.java
 *  Execution:    java Server n
 *  Dependencies: Queue.java
 *
 *  Load balancing example.
 *
 ******************************************************************************/

public class Server {
    private Queue<String> list = new Queue<String>();      // list of users
    private int load;                                      // load

    // add a new user to the list
    public void add(String user) {
        list.enqueue(user);
        load++;
    }

    // string representation
    public String toString() {
        // String s = String.format("%5d:  ", load);
        String s = "";
        for (String user : list)
            s += user + " ";
        return s;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        Server[] servers = new Server[n];
        for (int i = 0; i < n; i++)
            servers[i] = new Server();

        // generate n random jobs and assign to a random processor
        for (int j = 0; j < n; j++) {
            String user = "user" + j;
            int i = StdRandom.uniform(n);
            servers[i].add(user);
        }

        // see how even the distribution is by printing out the
        // contents of each server
        for (int i = 0; i < n; i++)
            StdOut.println(i + ": " + servers[i]);
    }
}
