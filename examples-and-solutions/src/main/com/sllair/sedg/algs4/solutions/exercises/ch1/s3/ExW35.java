package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExW35 {

    private static final String DIVIDER = String.join("", Collections.nCopies(53, "-"));

    public static class Steque<Item> implements Iterable<Item> {

        private Stack<Item> pushStack = new Stack<>();
        private Stack<Item> enqueueStack = new Stack<>();

        public boolean isEmpty() {
            return this.pushStack.isEmpty() && this.enqueueStack.isEmpty();
        }

        public int size() {
            return this.pushStack.size() + this.enqueueStack.size();
        }

        public void push(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            this.pushStack.push(item);
        }

        public void enqueue(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot enqueue a null item!");
            }
            this.enqueueStack.push(item);
        }

        public Item pop() {
            if (this.pushStack.isEmpty()) {
                while (!this.enqueueStack.isEmpty()) {
                    this.pushStack.push(this.enqueueStack.pop());
                }
            }

            if (!this.pushStack.isEmpty()) {
                return this.pushStack.pop();
            } else {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty steque!"
                );
            }
        }

        private Item peek() {
            if (this.pushStack.isEmpty()) {
                while (!this.enqueueStack.isEmpty()) {
                    this.pushStack.push(this.enqueueStack.pop());
                }
            }

            if (!this.pushStack.isEmpty()) {
                return this.pushStack.peek();
            } else {
                throw new NoSuchElementException(
                        "Cannot peek into an empty steque!"
                );
            }
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
            return new StequeIterator<>(this);
        }

        private static class StequeIterator<Item> implements Iterator<Item> {

            private final Iterator<Item> pushStackIterator;
            private final Iterator<Item> reverseEnqueueStackIterator;

            public StequeIterator(Steque<Item> steque) {
                this.pushStackIterator = steque.pushStack.iterator();

                Stack<Item> reverseEnqueueStack = new Stack<>();
                for (Item item : steque.enqueueStack) {
                    reverseEnqueueStack.push(item);
                }

                this.reverseEnqueueStackIterator = reverseEnqueueStack.iterator();
            }

            public boolean hasNext() {
                return this.pushStackIterator.hasNext() || this.reverseEnqueueStackIterator.hasNext();
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                if (this.pushStackIterator.hasNext()) {
                    return this.pushStackIterator.next();
                } else {
                    return this.reverseEnqueueStackIterator.next();
                }
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    public static void main(String[] args) {
        Steque<Integer> steque = new Steque<>();

        StdOut.println(DIVIDER);
        StdOut.println(steque);

        for (int i = 0; i < 20; i++) {
            StdOut.println(DIVIDER);
            if (steque.size() % 2 == 0) {
                StdOut.println(String.format("push %d", i));
                steque.push(i);
                StdOut.println(String.format("peek %d", steque.peek()));
            } else {
                StdOut.println(String.format("enqueue %d", i));
                steque.enqueue(i);
                StdOut.println(String.format("peek %d", steque.peek()));
            }
            StdOut.println(steque);
        }

        while (!steque.isEmpty()) {
            StdOut.println(DIVIDER);

            StdOut.println(String.format("peek %d", steque.peek()));
            StdOut.println(String.format("pop %d", steque.pop()));

            StdOut.println(steque);
        }
        StdOut.println(DIVIDER);

    }

}
