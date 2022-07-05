package com.sllair.sedg.algs4.solutions.quizzes.part1.week2.elementary_sorts;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * Question Prompt:
 * Dutch national flag. Given an array of nn buckets, each containing a red, white, or blue pebble, sort them by color.
 * The allowed operations are:
 *
 * +  swap(i, j)swap(i,j):  swap the pebble in bucket i with the pebble in bucket j.
 * +  color(i)color(i): determine the color of the pebble in bucket i.
 *
 * The performance requirements are as follows:
 *
 * +  At most n calls to color()color().
 * +  At most n calls to swap()swap().
 * +  Constant extra space.
 */
public class P3 {
    /*
     * This problem is on LeetCode:
     * https://leetcode.com/problems/sort-colors/
     *
     * Here is one of my successful submissions:
     * https://leetcode.com/submissions/detail/749809749/
     */
    public enum Color {
        RED(0),
        WHITE(1),
        BLUE(2);

        private final static Map<String, Color> colorMap = Arrays.stream(Color.values()).collect(Collectors.toMap(
                color -> color.name(),
                color -> color
        ));

        private final int value;

        Color(int value) {
            this.value = value;
        }

        public boolean lessThan(Color c) {
            return this.value < c.value;
        }

        public boolean greaterThan(Color c) {
            return this.value > c.value;
        }

        public static Color parse(String s) {
            return colorMap.get(s.toUpperCase());
        }

        public static String toString(Color[] colors) {
            StringBuilder sb = new StringBuilder();
            sb.append("{ ");
            for (int i = 0; i < colors.length; i++) {
                sb.append(colors[i]);
                if (i < colors.length - 1) {
                    sb.append(',');
                }
                sb.append(' ');
            }
            sb.append('}');

            return sb.toString();

        }
    }

    public static class ColorSorter {

        private final Color[] colors;

        public ColorSorter(Color[] colors) {
            this.colors = colors;
        }


        public void sort() {
            int rp = 0;
            int wp = 0;
            int bp = this.colors.length - 1;

            while (wp <= bp) {
                Color curr = color(wp);

                if (curr.lessThan(Color.WHITE)) {
                    swap(wp, rp);
                    ++rp;
                    ++wp;
                } else if (curr.greaterThan(Color.WHITE)) {
                    swap(wp, bp);
                    --bp;
                } else {
                    ++wp;
                }
            }
        }

        private Color color(int i) {
            return this.colors[i];
        }

        private void swap(int i, int j) {
            Color swap = this.colors[i];
            this.colors[i] = this.colors[j];
            this.colors[j] = swap;
        }
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, P3.class);

        int N = in.readInt();

        Color[] colors = new Color[N];

        for (int i = 0; i < N; ++i) {
            colors[i] = Color.parse(in.readString());
        }

        ColorSorter sorter = new ColorSorter(colors);
        sorter.sort();

        StdOut.println(Color.toString(colors));
    }
}
