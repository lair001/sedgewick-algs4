package com.sllair.sedg.algs4.solutions.quizzes.part1.week1.anyls_of_algs;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

/*
 * Question Prompt:
 * Egg drop. Suppose that you have an nn-story building (with floors 1 through nn) and plenty of eggs. An egg breaks if
 * it is dropped from floor T or higher and does not break otherwise. Your goal is to devise a strategy to determine the
 * value of  T given the following limitations on the number of eggs and tosses:
 *
 * +  Version 0: 1 egg, ≤T tosses.
 * +  Version 1: ∼1*lg(n) eggs and  ~1*lg(n) tosses.
 * +  Version 2: ∼lg(T) eggs and ∼2*lg(T) tosses.
 * +  Version 3: 2 eggs and  ∼2*sqrt(n) tosses.
 * +  Version 4: 2 eggs and  ≤c*sqrt(T) tosses for some fixed constant cc.
 */
public class P3V0 {

    private int T;

    public P3V0(int T) {
        this.T = T;
    }

    public boolean breaks(int floor) {
        return floor >= T;
    }

    public static void main(String[] args) {
        int n = args.length > 0 ?
                Integer.parseInt(args[0]) :
                StdRandom.uniform(1, 200);

        int T = args.length > 1 ?
                Integer.parseInt(args[1]) :
                StdRandom.uniform(1, n);

        P3V0 experiment = new P3V0(T);

        for (int i = 1; i <= n; i++) {
            Stopwatch timer = new Stopwatch();
            if (experiment.breaks(i)) {
                StdOut.println(String.format("%d %d", n, i));
                StdOut.println(String.format("Elapsed time: %.3f s", timer.elapsedTime()));
                break;
            }
        }
    }
}
