package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExW37 {

    public static class ThreeStackDeque<Item> implements Iterable<Item> {

        private final Stack<Item> left = new Stack<>();
        private final Stack<Item> right = new Stack<>();
        private final Stack<Item> tmp = new Stack<>();

        private void rebalance(Stack<Item> full, Stack<Item> empty) {
            for (int i = 0; i < full.size() / 2; i++) {
                this.tmp.push(full.pop());
            }
            while (!full.isEmpty()) {
                empty.push(full.pop());
            }
            while (!tmp.isEmpty()) {
                full.push(tmp.pop());
            }
        }

        public boolean isEmpty() {
            return this.left.isEmpty() && this.right.isEmpty();
        }

        public int size() {
            return this.left.size() + this.right.size();
        }

        public void pushLeft(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            this.left.push(item);
        }

        public void pushRight(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            this.right.push(item);
        }

        public Item popLeft() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            if (this.left.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalance(this.right, this.left);
                } else {
                    return this.right.pop();
                }
            }

            return this.left.pop();
        }

        public Item popRight() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            if (this.right.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalance(this.left, this.right);
                } else {
                    return this.left.pop();
                }
            }

            return this.right.pop();
        }

        public Item peekLeft() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            if (this.left.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalance(this.right, this.left);
                } else {
                    return this.right.peek();
                }
            }

            return this.left.peek();
        }

        public Item peekRight() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            if (this.right.isEmpty()) {
                if (this.size() > 1) {
                    this.rebalance(this.left, this.right);
                } else {
                    return this.left.peek();
                }
            }

            return this.right.peek();
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
            return new ThreeStackDequeIterator<>(this);
        }

        private static class ThreeStackDequeIterator<Item> implements Iterator<Item> {

            private final Iterator<Item> leftIterator;
            private final Iterator<Item> reverseRightIterator;

            public ThreeStackDequeIterator(ThreeStackDeque<Item> deque) {
                this.leftIterator = deque.left.iterator();
                Stack<Item> tmp = new Stack<>();
                for (Item item : deque.right) {
                    tmp.push(item);
                }
                this.reverseRightIterator = tmp.iterator();
            }

            public boolean hasNext() {
                return leftIterator.hasNext() || reverseRightIterator.hasNext();
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                return this.leftIterator.hasNext() ? this.leftIterator.next() : this.reverseRightIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    public static void main(String[] args) {
        ThreeStackDeque<Integer> deque = new ThreeStackDeque<>();
        StdOut.println(deque);

        for (int i = 0; i < 20; i++) {
            StdOut.println(String.format("pushRight %d", i));
            deque.pushRight(i);
            StdOut.println(deque);
        }

        StdOut.println(String.format("popLeft %d", deque.popLeft()));
        StdOut.println(deque);

        for (int i = 20; i < 40; i++) {
            StdOut.println(String.format("pushLeft %d", i));
            deque.pushLeft(i);
            StdOut.println(deque);
        }

        for (int i = 0; i < 11; i++) {
            StdOut.println(String.format("popRight %d", deque.popRight()));
            StdOut.println(deque);
        }

        while (!deque.isEmpty()) {
            StdOut.println(String.format("popLeft %d", deque.popLeft()));
            StdOut.println(deque);
        }

        StdOut.println(String.format("pushLeft %d", 0));
        deque.pushLeft(0);
        StdOut.println(deque);

        StdOut.println(String.format("popRight %d", deque.popRight()));
        StdOut.println(deque);

        StdOut.println(String.format("pushRight %d", 0));
        deque.pushRight(0);
        StdOut.println(deque);

        StdOut.println(String.format("popLeft %d", deque.popLeft()));
        StdOut.println(deque);

        for (int i = 0; i < 10; i++) {
            StdOut.println(String.format("pushLeft %d", i));
            deque.pushLeft(i);
            StdOut.println(deque);
        }

        while (!deque.isEmpty()) {
            StdOut.println(String.format("popRight %d", deque.popRight()));
            StdOut.println(deque);
        }
    }
}
