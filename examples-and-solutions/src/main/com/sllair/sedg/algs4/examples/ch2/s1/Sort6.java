package com.sllair.sedg.algs4.examples.ch2.s1;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac Sort6.java
 *  Execution:    java Sort6 a b c d e f
 *
 *  Reads in six integers and prints them out in sorted order without
 *  using a loop. Uses 12 compare-exchanges.
 *
 *  Solution submitted by Sailesh Dhungana '11.
 *
 ******************************************************************************/

public class Sort6 {
    public static void main(String[] args) {

        // read in 6 command-line integers to sort
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);
        int e = Integer.parseInt(args[4]);
        int f = Integer.parseInt(args[5]);
        if (a > b) {
            int t = b;
            b = a;
            a = t;
        }
        if (c > d) {
            int t = d;
            d = c;
            c = t;
        }
        if (e > f) {
            int t = f;
            f = e;
            e = t;
        }
        if (c > e) {
            int t = e;
            e = c;
            c = t;
        }
        if (a > c) {
            int t = c;
            c = a;
            a = t;
        }
        if (b > d) {
            int t = d;
            d = b;
            b = t;
        }
        if (d > f) {
            int t = f;
            f = d;
            d = t;
        }
        if (b > c) {
            int t = c;
            c = b;
            b = t;
        }
        if (d > e) {
            int t = e;
            e = d;
            d = t;
        }
        if (b > d) {
            int t = d;
            d = b;
            b = t;
        }
        if (c > e) {
            int t = e;
            e = c;
            c = t;
        }
        if (c > d) {
            int t = d;
            d = c;
            c = t;
        }
        StdOut.println(a + " " + b + " " + c + " " + d + " " + e + " " + f);
    }
}
