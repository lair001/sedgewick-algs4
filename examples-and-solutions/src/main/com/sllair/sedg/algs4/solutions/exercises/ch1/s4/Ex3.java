package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.examples.ch1.s4.ThreeSum;
import edu.princeton.cs.algs4.*;

/*
 * Proper scaling is difficult, but I think I have something
 * tolerable for this exercise.  I may revisit it if plotting
 * is useful for later exercises.
 *
 * It would be helpful if there was an explanation of how
 * Sedgewick's scaling works. Maybe its in his intro to cs
 * book.
 */
public class Ex3 {

    private static final int MAXIMUM_INTEGER = 1000000;

    public static class DataPlotter {

        private static final double MARGIN_SCALE = 0.05;

        private final Queue<Point2D> points;
        private final boolean isLog;
        private double minX = Integer.MAX_VALUE;
        private double maxX = Integer.MIN_VALUE;
        private double minY = Integer.MAX_VALUE;
        private double maxY = Integer.MIN_VALUE;

        DataPlotter(boolean isLog) {
            this.points = new Queue<>();
            this.isLog = isLog;
        }

        public void add(double x, double y) {
            if (this.isLog) {
                x = Math.log(x);
                y = Math.log(y);
            }
            this.minX = Math.min(this.minX, x);
            this.minY = Math.min(this.minY, y);
            this.maxX = Math.max(this.maxX, x);
            this.maxY = Math.max(this.maxY, y);
            this.points.enqueue(new Point2D(x, y));
            if (this.points.size() > 1) {
                this.drawPlot();
            }
        }

        private double getMinScale(double value) {
            if (value < 0.0) {
                return (1.0 + MARGIN_SCALE) * value;
            } else {
                return (1.0 - MARGIN_SCALE) * value;
            }
        }

        private double getMaxScale(double value) {
            if (value < 0.0) {
                return (1.0 - MARGIN_SCALE) * value;
            } else {
                return (1.0 + MARGIN_SCALE) * value;
            }
        }

        private double getMinXScale() {
            return this.getMinScale(this.minX);
        }

        private double getMaxXScale() {
            return this.getMaxScale(this.maxX);
        }

        private double getMinYScale() {
            return this.getMinScale(this.minY);
        }

        private double getMaxYScale() {
            return this.getMaxScale(this.maxY);
        }

        private void drawPlot() {
            StdDraw.clear();
            int canvasSize = 640;
            StdDraw.setCanvasSize(canvasSize, canvasSize);
            StdDraw.setPenRadius(0.012);
            StdDraw.setXscale(this.getMinXScale(), this.getMaxXScale());
            StdDraw.setYscale(this.getMinYScale(), this.getMaxYScale());

            Point2D prev = null;

            for (Point2D curr : this.points) {
                if (prev != null) {
                    StdDraw.line(prev.x(), prev.y(), curr.x(), curr.y());
                }
                prev = curr;
            }
        }
    }

    /**
     * Returns the amount of time to call {@code ThreeSum.count()} with <em>n</em>
     * random 6-digit integers.
     *
     * @param n the number of integers
     * @return amount of time (in seconds) to call {@code ThreeSum.count()}
     * with <em>n</em> random 6-digit integers
     */
    public static double timeTrial(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = StdRandom.uniform(-MAXIMUM_INTEGER, MAXIMUM_INTEGER);
        }
        Stopwatch timer = new Stopwatch();
        ThreeSum.count(a);
        return timer.elapsedTime();
    }

    /**
     * Prints table of running times to call {@code ThreeSum.count()}
     * for arrays of size 250, 500, 1000, 2000, and so forth.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        DataPlotter plotter = new DataPlotter(
                args.length > 0 && "log".equals(args[0])
        );
        for (int n = 250; true; n += n) {
            double time = timeTrial(n);
            plotter.add(time, n);

            StdOut.printf("%7d %7.1f\n", n, time);
        }
    }

}
