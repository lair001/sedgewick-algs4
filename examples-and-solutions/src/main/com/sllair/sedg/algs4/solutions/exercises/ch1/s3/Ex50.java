package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class Ex50 {

    /*
     * Implementing this as a wrapper due to some complications of overriding
     * methods with generic types when the subtype is a nested/inner class.
     */
    public static class StackWrapper<Item> implements Iterable<Item> {

        private final Stack<Item> stack;
        private int numPopCalls;
        private int numPushCalls;

        StackWrapper() {
            this.stack = new Stack<Item>();
            this.numPopCalls = 0;
            this.numPushCalls = 0;
        }

        public boolean isEmpty() {
            return this.stack.isEmpty();
        }

        public int size() {
            return stack.size();
        }

        public void push(Item item) {
            ++this.numPushCalls;
            stack.push(item);
        }

        public Item pop() {
            ++this.numPopCalls;
            return stack.pop();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (Item item : this) {
                sb.append(item);
                sb.append(' ');
            }
            sb.append(']');

            return sb.toString();
        }

        public Iterator<Item> iterator() {
            return new FailFastStackIterator();
        }

        private class FailFastStackIterator implements Iterator<Item> {

            private final Iterator<Item> standardStackIterator = stack.iterator();
            private final int originalPushCalls = numPushCalls;
            private final int originalPopCalls = numPopCalls;

            private void throwExceptionIfStackHasBeenModified() {
                if (numPushCalls != this.originalPushCalls || numPopCalls != this.originalPopCalls) {
                    throw new ConcurrentModificationException();
                }
            }

            public boolean hasNext() {
                throwExceptionIfStackHasBeenModified();
                return this.standardStackIterator.hasNext();
            }

            public Item next() {
                throwExceptionIfStackHasBeenModified();
                return this.standardStackIterator.next();
            }

            public void remove() {
                /*
                 * This is going to throw an unsupported method
                 * exception anyway.
                 */
                this.standardStackIterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        StackWrapper<Integer> stack = new StackWrapper<>();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        StdOut.println(stack);
        StdOut.println(String.format("pop %d", stack.pop()));
        StdOut.print(stack);

        try {
            Iterator<Integer> iterator = stack.iterator();
            StdOut.println(iterator.hasNext());
            StdOut.println(iterator.next());
            stack.push(127);
            StdOut.println(iterator.hasNext());
            throw new RuntimeException("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            StdOut.println("Exception thrown:");
            StdOut.println(e);
            StdOut.println("Just as expected!");
        }

        StdOut.println(stack);

        try {
            Iterator<Integer> iterator = stack.iterator();
            StdOut.println(iterator.hasNext());
            StdOut.println(iterator.next());
            StdOut.println(stack.pop());
            StdOut.print(iterator.next());
            throw new RuntimeException("Expected ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            StdOut.println("Exception thrown:");
            StdOut.println(e);
            StdOut.println("Just as expected!");
        }

        StdOut.println(stack);

    }

}
