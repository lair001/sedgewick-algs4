import edu.princeton.cs.algs4.InsertionX;
import edu.princeton.cs.algs4.MergeX;
import edu.princeton.cs.algs4.Queue;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {
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

        Queue<LineSegment> lineSegQueue = new Queue<>();

        Point[] a = new Point[4];

        for (int p = 0; p < pointsCopy.length - 3; ++p) {
            for (int q = p + 1; q < pointsCopy.length - 2; ++q) {
                for (int r = q + 1; r < pointsCopy.length - 1; ++r) {
                    for (int s = r + 1; s < pointsCopy.length; ++s) {
                        a[0] = pointsCopy[p];
                        a[1] = pointsCopy[q];
                        a[2] = pointsCopy[r];
                        a[3] = pointsCopy[s];

                        InsertionX.sort(a);

                        double slope01 = a[0].slopeTo(a[1]);
                        double slope12 = a[1].slopeTo(a[2]);
                        double slope23 = a[2].slopeTo(a[3]);

                        if (slope01 == slope12 && slope12 == slope23) {
                            lineSegQueue.enqueue(new LineSegment(a[0], a[3]));
                        }
                    }
                }
            }
        }
        this.lineSegments = new LineSegment[lineSegQueue.size()];

        for (int i = 0; i < this.lineSegments.length; ++i) {
            this.lineSegments[i] = lineSegQueue.dequeue();
        }
    }

    public int numberOfSegments() {
        return this.lineSegments.length;
    }

    public LineSegment[] segments() {
        return this.lineSegments.clone();
    }

}
