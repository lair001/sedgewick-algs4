package com.sllair.sedg.algs4.examples.ch2.s1;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Sort3.java
 *  Execution:    java Sort3 a b c
 *
 *  Reads in three integers and prints them out in sorted order without
 *  using a loop.
 *
 *  % java Sort3 33 22 18
 *  18 22 33
 *
 *  % java Sort3 43 12 8
 *  8 12 43
 *
 ******************************************************************************/

public class Sort3 {
    public static void main(String[] args) {

        // read in 3 command-line integers to sort
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        // 3 compare-exchanges
        if (b < a) {
            int t = b;
            b = a;
            a = t;
        }
        if (c < b) {
            int t = c;
            c = b;
            b = t;
        }
        if (b < a) {
            int t = b;
            b = a;
            a = t;
        }

        // print out results
        StdOut.println(a + " " + b + " " + c);
    }

}
