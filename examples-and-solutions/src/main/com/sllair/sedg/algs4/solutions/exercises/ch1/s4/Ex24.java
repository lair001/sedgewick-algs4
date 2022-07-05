package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class Ex24 {

    public static class EggThrowLgN implements IEggThrow {

        private int F;

        public EggThrowLgN(int F) {
            this.F = F;
        }

        public boolean breaks(int floor) {
            return floor >= this.F;
        }

        public int findF(int n) {
            int lo = 1;
            int hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (this.breaks(mid)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            return this.breaks(lo) ? lo : -1;
        }
    }

    public static class EggThrow2LgF implements IEggThrow {
        private int F;

        public EggThrow2LgF(int F) {
            this.F = F;
        }

        public boolean breaks(int floor) {
            return floor >= this.F;
        }

        /*
         * In the real world, F is much smaller than n for
         * large n. In this variant, we obtain 2lg(F) time
         * complexity by doubling until we find an upper
         * bound for F and then switch to a binary search.
         */
        public int findF(int n) {
            int upperBoundForF = 1;

            while (upperBoundForF < n && !this.breaks(upperBoundForF)) {
                upperBoundForF += upperBoundForF;
            }

            int lo = 0;
            int hi = Math.min(upperBoundForF, n);

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (this.breaks(mid)) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            return this.breaks(lo) ? lo : -1;
        }
    }

    private static IEggThrow getExperiment(int F, String[] args) {
        if (args.length > 0 && "f".equals(args[0])) {
            return new EggThrow2LgF(F);
        } else {
            return new EggThrowLgN(F);
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
