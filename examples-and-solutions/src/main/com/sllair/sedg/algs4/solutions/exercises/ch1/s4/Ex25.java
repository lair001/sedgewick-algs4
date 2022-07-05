package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Ex25 {

    public static class EggThrow2SqrtN implements IEggThrow {

        private int F;

        public EggThrow2SqrtN(int F) {
            this.F = F;
        }

        public boolean breaks(int floor) {
            return floor >= this.F;
        }

        /*
         * The advantage over Ex 24 is that we only lose 2 eggs.
         * This comes at the cost of more trials.
         */
        public int findF(int n) {
            int delta = (int) Math.ceil(Math.sqrt(n));
            int currFloor = delta;

            while (!this.breaks(currFloor)) {
                currFloor += delta;
            }

            // lost 1 egg. Only have 1 more to break!

            currFloor -= delta - 1;
            while (!this.breaks(currFloor)) {
                currFloor++;
            }

            return currFloor;
        }

    }

    public static class EggThrowCSqrtF implements IEggThrow {

        private int F;

        public EggThrowCSqrtF(int F) {
            this.F = F;
        }

        public boolean breaks(int floor) {
            return floor >= this.F;
        }

        /*
         * The advantage over Ex 24 is that we only lose 2 eggs.
         * This comes at the cost of more trials.
         */
        public int findF(int n) {
            int lowerBound = 1;
            int upperBound = 2;

            while (!this.breaks(upperBound) && upperBound < n) {
                lowerBound = upperBound;
                upperBound *= upperBound;
            }

            while (!this.breaks(lowerBound)) {
                lowerBound++;
            }

            return lowerBound;
        }
    }

    private static IEggThrow getExperiment(int F, String[] args) {
        if (args.length > 0 && "f".equals(args[0])) {
            return new EggThrowCSqrtF(F);
        } else {
            return new EggThrow2SqrtN(F);
        }
    }

    public static void main(String[] args) {
        int n = args.length > 1 ?
                Integer.parseInt(args[1]) :
                StdRandom.uniform(1, 200);

        int F = args.length > 2 ?
                Integer.parseInt(args[2]) :
                StdRandom.uniform(1, n);

        if (F > n) {
            StdOut.println("The building isn't tall enough to break an egg!");
        } else {
            IEggThrow experiment = getExperiment(F, args);

            Stopwatch timer = new Stopwatch();
            StdOut.println(String.format("%d %d", n, experiment.findF(n)));
            StdOut.println(String.format("Elapsed time: %.3f s", timer.elapsedTime()));
        }
    }


}
