package com.sllair.sedg.algs4.solutions.quizzes.part1.week2.elementary_sorts;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

/*
 * Question Prompt:
 * Intersection of two sets. Given two arrays a[] and b[], each containing n distinct 2D points
 * in the plane, design a subquadratic algorithm to count the number of points that are contained
 * both in array a[] and array b[].
 */
public class P1 {

    private static final XYOrder XY_ORDER = new XYOrder();

    private static class XYOrder implements Comparator<Point2D> {

        public int compare(Point2D o1, Point2D o2) {
            if (o1.x() < o2.x()) {
                return -1;
            } else if (o1.x() > o2.x()) {
                return 1;
            } else if (o1.y() < o2.y()) {
                return -1;
            } else if (o1.y() > o2.y()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    private static boolean less(Point2D v, Point2D w) {
        return XY_ORDER.compare(v, w) < 0;
    }

    private static class Point2DXYCoordsShell {

        public static void sort(Point2D[] a) {
            int n = a.length;

            int h = 1;

            while (h < n / 3) {
                h = 3 * h + 1;
            }

            while (h >= 1) {
                for (int i = h; i < n; i++) {
                    for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
                        exch(a, j, j - h);
                    }
                }
                assert isHsorted(a, h);
                h /= 3;
            }
            assert isSorted(a);
        }

        private static void exch(Point2D[] a, int i, int j) {
            Point2D swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

        private static boolean isSorted(Point2D[] a) {
            for (int i = 1; i < a.length; i++) {
                if (less(a[i], a[i - 1])) {
                    return false;
                }
            }
            return true;
        }

        private static boolean isHsorted(Point2D[] a, int h) {
            for (int i = h; i < a.length; i++) {
                if (less(a[i], a[i - h])) {
                    return false;
                }
            }
            return true;
        }
    }

    private static class Point2DXYCoordsBinarySearch {
        public static int indexOf(Point2D[] a, Point2D key) {
            int lo = 0;
            int hi = a.length - 1;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (less(a[mid], key)) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
            return XY_ORDER.compare(a[lo], key) == 0 ? lo : -1;
        }
    }

    public static int numOfPointsInBoth(Point2D[] a, Point2D[] b) {
        Point2DXYCoordsShell.sort(a);
        Point2DXYCoordsShell.sort(b);

        int count = 0;
        for (int i = 0; i < a.length; ++i) {
            if (Point2DXYCoordsBinarySearch.indexOf(b, a[i]) >= 0) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, P1.class);

        int n = in.readInt();

        Point2D[] a = new Point2D[n];

        for (int i = 0; i < n; ++i) {
            a[i] = new Point2D(in.readDouble(), in.readDouble());
        }

        Point2D[] b = new Point2D[n];

        for (int i = 0; i < n; ++i) {
            b[i] = new Point2D(in.readDouble(), in.readDouble());
        }

        StdOut.println(numOfPointsInBoth(a, b));

    }

}
