import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Queue;

import java.util.Comparator;

public class FastCollinearPoints {

    private static class LineSegRecord {
        public static final Comparator<LineSegRecord> SLOPE_ORDER = new SlopeOrder();
        public static final Comparator<LineSegRecord> MAX_ORDER = new MaxOrder();

        LineSegRecord(Point min, Point max, double slope) {
            this.min = min;
            this.max = max;
            this.slope = slope;
        }

        Point min;
        Point max;
        double slope;

        private static class SlopeOrder implements Comparator<LineSegRecord> {

            public int compare(LineSegRecord v, LineSegRecord w) {
                if (v.slope < w.slope) {
                    return -1;
                }
                else if (v.slope > w.slope) {
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }

        private static class MaxOrder implements Comparator<LineSegRecord> {

            public int compare(LineSegRecord v, LineSegRecord w) {
                return v.max.compareTo(w.max);
            }
        }

    }

    private LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException(
                    "The array of points cannot be null!"
            );
        }
        if (points.length > 0 && points[0] == null) {
            throw new IllegalArgumentException(
                    "None of the points can be null!"
            );
        }
        Point[] pointsCopy = points.clone();
        try {
            MergeX.sort(pointsCopy);
        }
        catch (NullPointerException e) {
            throw new IllegalArgumentException(
                    "None of the points can be null!"
            );
        }

        for (int i = 1; i < pointsCopy.length; ++i) {
            Point a = pointsCopy[i - 1];
            Point b = pointsCopy[i];
            if (a.compareTo(b) == 0) {
                throw new IllegalArgumentException(
                        "Cannot have duplicate points!"
                );
            }
        }

        Queue<LineSegRecord> lineSegRecordQueue = new Queue<>();

        for (int i = 0; i < pointsCopy.length - 3; ++i) {
            Point currPoint = pointsCopy[i];
            Comparator<Point> currComp = currPoint.slopeOrder();
            MergeStable.sort(pointsCopy, currComp, i + 1, pointsCopy.length - 1);
            for (int j = i + 1; j < pointsCopy.length - 2; ++j) {
                int start = j;
                Point pointJ = pointsCopy[j];

                Point min;
                Point max;
                if (currPoint.compareTo(pointJ) < 0) {
                    min = currPoint;
                    max = pointJ;
                }
                else {
                    min = pointJ;
                    max = currPoint;
                }
                Double currSlope = currPoint.slopeTo(pointJ);
                while (j < pointsCopy.length - 1
                        && currSlope ==
                        currPoint.slopeTo(pointsCopy[j + 1])) {
                    ++j;
                    pointJ = pointsCopy[j];
                    if (pointJ.compareTo(min) < 0) {
                        min = pointJ;
                    }
                    else if (pointJ.compareTo(max) > 0) {
                        max = pointJ;
                    }
                }
                // remember, the current point at i is one of the four (or more)
                if (j - start >= 2) {
                    lineSegRecordQueue.enqueue(new LineSegRecord(min, max, currSlope));
                }
            }
            MergeXSubarray.sort(pointsCopy, i + 1, pointsCopy.length - 1);
        }

        LineSegRecord[] lineSegRecords =
                new LineSegRecord[lineSegRecordQueue.size()];

        for (int i = 0; i < lineSegRecords.length; ++i) {
            lineSegRecords[i] = lineSegRecordQueue.dequeue();
        }

        MergeX.sort(lineSegRecords, LineSegRecord.SLOPE_ORDER);
        MergeStable.sort(lineSegRecords, LineSegRecord.MAX_ORDER);

        for (int i = 0; i < lineSegRecords.length; i++) {
            LineSegRecord maximal = lineSegRecords[i];
            Point currMax = maximal.max;
            double currSlope = maximal.slope;

            while (i < lineSegRecords.length - 1 && lineSegRecords[i + 1].max == currMax
                    && lineSegRecords[i + 1].slope == currSlope) {
                if (lineSegRecords[i + 1].min.compareTo(maximal.min) < 0) {
                    maximal = lineSegRecords[i + 1];
                }
                ++i;
            }
            lineSegRecordQueue.enqueue(maximal);
        }

        lineSegRecords = null;

        this.lineSegments = new LineSegment[lineSegRecordQueue.size()];

        for (int i = 0; i < this.lineSegments.length; ++i) {
            LineSegRecord currRecord = lineSegRecordQueue.dequeue();
            this.lineSegments[i] =
                    new LineSegment(currRecord.min, currRecord.max);
        }
    }

    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {
        return this.lineSegments.clone();
    }

    /*
     * MergeX doesn't make its subarray sorting methods public so I
     * had to copy paste some of its code down here.
     */
    private static class MergeXSubarray {
        private static final int CUTOFF = 7;  // cutoff to insertion sort

        private static void sort(Point[] a, int lo, int hi) {
            Point[] aux = a.clone();
            sort(aux, a, lo, hi);
        }

        private static void merge(Point[] src, Point[] dst, int lo, int mid, int hi) {
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) dst[k] = src[j++];
                else if (j > hi) dst[k] = src[i++];
                else if (less(src[j], src[i])) dst[k] = src[j++];
                else dst[k] = src[i++];
            }
        }

        private static void sort(Point[] src, Point[] dst,
                                 int lo, int hi) {
            // if (hi <= lo) return;
            if (hi <= lo + CUTOFF) {
                insertionSort(dst, lo, hi);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(dst, src, lo, mid);
            sort(dst, src, mid + 1, hi);

            // using System.arraycopy() is a bit faster than the above loop
            if (!less(src[mid + 1], src[mid])) {
                System.arraycopy(src, lo, dst, lo, hi - lo + 1);
                return;
            }

            merge(src, dst, lo, mid, hi);
        }

        // is a[i] < a[j]?
        private static boolean less(Point a, Point b) {
            return a.compareTo(b) < 0;
        }

        // exchange a[i] and a[j]
        private static void exch(Point[] a, int i, int j) {
            Point swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }

        // sort from a[lo] to a[hi] using insertion sort
        private static void insertionSort(Point[] a, int lo, int hi) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                    exch(a, j, j - 1);
        }
    }

    /*
     * Unfortunately, since MergeX is unstable due to the aux array swapping and
     * the library Merge sort doesn't support comparators, we're going to have to
     * dump more merge sort code down here.
     */
    private static class MergeStable {
        private static final int CUTOFF = 7;  // cutoff to insertion sort

        private static void sort(Point[] a, Comparator<Point> comparator, int lo, int hi) {
            Point[] aux = a.clone();
            sort(a, aux, lo, hi, comparator);
        }

        private static <T> void merge(T[] a, T[] aux, int lo, int mid, int hi,
                                      Comparator<T> comparator) {

            // copy to aux[]
            for (int k = lo; k <= hi; k++) {
                aux[k] = a[k];
            }

            // merge back to a[]
            int i = lo, j = mid + 1;
            for (int k = lo; k <= hi; k++) {
                if (i > mid) a[k] = aux[j++];
                else if (j > hi) a[k] = aux[i++];
                else if (less(aux[j], aux[i], comparator)) a[k] = aux[j++];
                else a[k] = aux[i++];
            }

        }

        // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
        private static <T> void sort(T[] a, T[] aux, int lo, int hi,
                                     Comparator<T> comparator) {
            if (hi <= lo + CUTOFF) {
                insertionSort(a, lo, hi, comparator);
                return;
            }
            int mid = lo + (hi - lo) / 2;
            sort(a, aux, lo, mid, comparator);
            sort(a, aux, mid + 1, hi, comparator);
            if (less(a[mid + 1], a[mid], comparator)) {
                merge(a, aux, lo, mid, hi, comparator);
            }
        }

        /**
         * Rearranges the array in ascending order, using the natural order.
         *
         * @param a the array to be sorted
         */
        public static <T> void sort(T[] a, Comparator<T> comparator) {
            T[] aux = a.clone();
            sort(a, aux, 0, a.length - 1, comparator);
        }

        // sort from a[lo] to a[hi] using insertion sort
        private static <T> void insertionSort(T[] a, int lo, int hi,
                                              Comparator<T> comparator) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && less(a[j], a[j - 1], comparator); j--)
                    exch(a, j, j - 1);
        }

        // is a[i] < a[j]?
        private static <T> boolean less(T a, T b, Comparator<T> comparator) {
            return comparator.compare(a, b) < 0;
        }

        // exchange a[i] and a[j]
        private static <T> void exch(T[] a, int i, int j) {
            T swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
    }


}
