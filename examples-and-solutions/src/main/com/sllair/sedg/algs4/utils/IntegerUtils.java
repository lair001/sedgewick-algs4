package com.sllair.sedg.algs4.utils;

public class IntegerUtils {
    public static String toString(Integer[] ints) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < ints.length; i++) {
            sb.append(ints[i]);
            if (i < ints.length - 1) {
                sb.append(',');
            }
            sb.append(' ');
        }
        sb.append('}');

        return sb.toString();
    }
}
