package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/*
 * Comparisons of non-integral numeric types can be tricky and imprecise.
 * Therefore, to maintain the greatest amount of generality without
 * introducing this trickiness, I'm going to use Stack<Long>. long is
 * the largest of the integral data types and smaller ones like
 * byte, short, int, and char can be safely cast into it without losing
 * information. Unfortunately, Java does not offer a reference type that
 * is subclassed by only the integral reference types.
 */
public class ExW5 implements Iterable<Long> {

    private final Stack<Long> numbers = new Stack<>();
    private final Stack<MaximumRecord> maximums = new Stack<>();

    private static class Node {
        private Long item;
        private Node next;
    }

    private static class MaximumRecord {
        private Long value;
        private long count;
    }

    public boolean isEmpty() {
        return this.numbers.isEmpty();
    }

    public void push(Long item) {
        this.numbers.push(item);
        if (maximums.isEmpty() || item > maximums.peek().value) {
            MaximumRecord maximum = new MaximumRecord();
            maximum.value = item;
            maximum.count = 1L;
            maximums.push(maximum);
        } else {
            maximums.peek().count += 1L;
        }
    }

    public Long pop() {
        Long result = this.numbers.pop();
        this.maximums.peek().count -= 1L;
        if (this.maximums.peek().count == 0L) {
            this.maximums.pop();
        }
        return result;
    }

    public Long peek() {
        return this.numbers.peek();
    }

    public Long maximum() {
        return this.maximums.peek().value;
    }

    public Iterator<Long> iterator() {
        return this.numbers.iterator();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        sb.append(this.numbers.toString());
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW5.class);

        ExW5 collection = new ExW5();

        while (!in.isEmpty()) {
            String cmd = in.readString();

            if ("push".equals(cmd)) {
                Long i = in.readLong();
                StdOut.println(String.format("%s %d", cmd, i));
                collection.push(i);
            } else if ("max".equals(cmd)) {
                StdOut.println(String.format("%s %d", cmd, collection.maximum()));
            } else if ("pop".equals(cmd)) {
                StdOut.println(String.format("%s %d", cmd, collection.pop()));
            }

            StdOut.println(collection);
        }
    }

}
