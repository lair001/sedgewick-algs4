package com.sllair.sedg.algs4.examples.ch1.s3;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/******************************************************************************
 *  Compilation:  javac Grid.java
 *  Execution:    java Grid n d
 *  Dependencies: Queue.java
 *
 *  Generate n random Euclidean points in unit box (coorinates
 *  between 0 and 1) and print out all pairs that are at
 *  distance <= d.
 *
 ******************************************************************************/

public class Grid {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        double d = Double.parseDouble(args[1]);

        int rows = (int) (Math.ceil(1.0 / d));    // # rows in grid
        int cols = (int) (Math.ceil(1.0 / d));    // # columns in grid

        // initialize data structure
        Queue<Point2D>[][] grid = (Queue<Point2D>[][]) new Queue[rows + 2][cols + 2];
        for (int i = 0; i <= rows + 1; i++)
            for (int j = 0; j <= cols + 1; j++)
                grid[i][j] = new Queue<Point2D>();

        // generate random points and check if any previous point <= d
        for (int k = 0; k < n; k++) {
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);
            Point2D p = new Point2D(x, y);
            int row = 1 + (int) (x * rows);
            int col = 1 + (int) (y * cols);
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    for (Point2D q : grid[i][j])
                        if (p.distanceTo(q) <= d)
                            StdOut.println(p + " <--> " + q);
                }
            }
            grid[row][col].enqueue(p);
        }
    }
}

