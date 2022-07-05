package com.sllair.sedg.algs4.utils;

import edu.princeton.cs.algs4.Point2D;

public class Point2DUtils {
    public static String toString(Point2D[] points) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < points.length; i++) {
            sb.append(toString(points[i]));
            if (i < points.length - 1) {
                sb.append(',');
            }
            sb.append(' ');
        }
        sb.append('}');

        return sb.toString();
    }

    public static String toString(Point2D point) {
        return point == null ? "null" : String.format("(%.3f, %.3f)", point.x(), point.y());
    }
}
