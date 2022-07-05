package com.sllair.sedg.algs4.examples.ch2.s1;
/******************************************************************************
 *  Compilation:  javac SortTransactions.java
 *  Execution:    java SortTransactions < input.txt
 *  Dependencies: StdOut.java
 *  Data file:    https://algs4.cs.princeton.edu/21elementary/tinyBatch.txt
 *
 *  % java SortTransactions < transactions.txt
 *  Turing     1/11/2002    66.10
 *  Knuth      6/14/1999   288.34
 *  Turing     6/17/1990   644.08
 *  Dijkstra   9/10/2000   708.95
 *  Dijkstra  11/18/1995   837.42
 *  Hoare      8/12/2003  1025.70
 *  Bellman   10/26/2007  1358.62
 *  Knuth      7/25/2008  1564.55
 *  Turing     2/11/1991  2156.86
 *  Tarjan    10/13/1993  2520.97
 *  Dijkstra   8/22/2007  2678.40
 *  Hoare      5/10/1993  3229.27
 *  Knuth     11/11/2008  3284.33
 *  Turing    10/12/1993  3532.36
 *  Hoare      2/10/2005  4050.20
 *  Tarjan     3/26/2002  4121.85
 *  Hoare      8/18/1992  4381.21
 *  Tarjan     1/11/1999  4409.74
 *  Tarjan     2/12/1994  4732.35
 *  Thompson   2/27/2000  4747.08
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.util.Arrays;

public class SortTransactions {
    public static Transaction[] readTransactions() {
        Queue<Transaction> queue = new Queue<Transaction>();
        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction transaction = new Transaction(line);
            queue.enqueue(transaction);
        }

        int n = queue.size();
        Transaction[] transactions = new Transaction[n];
        for (int i = 0; i < n; i++)
            transactions[i] = queue.dequeue();

        return transactions;
    }

    public static void main(String[] args) {
        Transaction[] transactions = readTransactions();
        Arrays.sort(transactions);
        for (int i = 0; i < transactions.length; i++)
            StdOut.println(transactions[i]);
    }
}
