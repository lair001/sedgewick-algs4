package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex48 {

    private static final String REC_DIVIDER = String.join("", Collections.nCopies(53, "~"));
    private static final String SEC_DIVIDER = String.join("", Collections.nCopies(53, "-"));
    private static final String SIZE_FMT = "size %d";
    private static final String PEEK_FMT = "peek %s";

    public interface IStack<Item> extends Iterable<Item> {
        boolean isEmpty();

        int size();

        void push(Item item);

        Item pop();

        Item peek();
    }

    private static class LeftStack<Item> implements IStack<Item> {

        private final Ex33.IDeque<Item> deque;
        private int size;

        public LeftStack(Ex33.IDeque<Item> deque) {
            this.deque = deque;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int size() {
            return this.size;
        }

        public void push(Item item) {
            ++this.size;
            this.deque.pushLeft(item);

        }

        public Item pop() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty stack!"
                );
            }
            --this.size;
            return this.deque.popLeft();
        }

        public Item peek() {
            return this.deque.peekLeft();
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
            return new LeftStackIterator<>(this);
        }

        private static class LeftStackIterator<Item> implements Iterator<Item> {

            private final Iterator<Item> dequeIterator;
            private int size;

            public LeftStackIterator(LeftStack<Item> leftStack) {
                this.dequeIterator = leftStack.deque.iterator();
                this.size = leftStack.size;
            }

            public boolean hasNext() {
                return this.size > 0;
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                this.size--;
                return dequeIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    private static class RightStack<Item> implements IStack<Item> {

        private Ex33.IDeque<Item> deque;
        private int size;

        public RightStack(Ex33.IDeque<Item> deque) {
            this.deque = deque;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        public int size() {
            return this.size;
        }

        public void push(Item item) {
            ++this.size;
            this.deque.pushRight(item);

        }

        public Item pop() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty stack!"
                );
            }
            --this.size;
            return this.deque.popRight();
        }

        public Item peek() {
            return this.deque.peekRight();
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
            return new RightStackIterator<>(this);
        }

        private static class RightStackIterator<Item> implements Iterator<Item> {

            private final Iterator<Item> reverseDequeIterator;
            private int size;

            public RightStackIterator(RightStack<Item> rightStack) {
                this.reverseDequeIterator = rightStack.deque.reverseIterator();
                this.size = rightStack.size;
            }

            public boolean hasNext() {
                return this.size > 0;
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                this.size--;
                return reverseDequeIterator.next();
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    private static <T> T[] makeArray(T... a) {
        return a;
    }

    private static <Item> IStack<Item>[] getStacks(Ex33.IDeque<Item> deque) {
        IStack<Item> left = new LeftStack<>(deque);
        IStack<Item> right = new RightStack<>(deque);

        return makeArray(left, right);
    }

    public static <Item> IStack<Item>[] getListStacks() {
        return getStacks(new Ex33.Deque<>());
    }

    private static Ex33.IDeque<Integer> getDeque(String[] args) {
        if (args.length < 1 || "l".equals(args[0])) {
            return new Ex33.Deque<>();
        } else if ("a".equals(args[0])) {
            return new Ex33.ResizingArrayDeque<>();
        } else {
            return null;
        }
    }

    private static <Item> void println(Ex33.IDeque<Item> deque, IStack<Item>[] stacks) {
        StdOut.println("Deque");
        StdOut.println(deque);
        StdOut.println(String.format(SIZE_FMT, deque.size()));
        StdOut.println(SEC_DIVIDER);
        StdOut.println("Left Stack");
        StdOut.println(stacks[0]);
        StdOut.println(String.format(SIZE_FMT, stacks[0].size()));
        if (stacks[0].size() > 0) {
            StdOut.println(String.format(PEEK_FMT, stacks[0].peek()));
        }
        StdOut.println(SEC_DIVIDER);
        StdOut.println("Right Stack");
        StdOut.println(stacks[1]);
        StdOut.println(String.format(SIZE_FMT, stacks[1].size()));
        if (stacks[1].size() > 0) {
            StdOut.println(String.format(PEEK_FMT, stacks[1].peek()));
        }
    }

    public static void main(String[] args) {
        Ex33.IDeque<Integer> deque = getDeque(args);
        if (deque == null) {
            StdOut.println(String.format("Invalid deque type provided: %s", args[0]));
            return;
        }
        IStack<Integer>[] stacks = getStacks(deque);
        StdOut.println(REC_DIVIDER);
        println(deque, stacks);

        for (int i = 0; i < 20; i++) {
            StdOut.println(REC_DIVIDER);
            if (deque.size() % 2 == 0) {
                StdOut.println(String.format("push on left stack %d", i));
                StdOut.println(SEC_DIVIDER);
                stacks[0].push(i);
            } else {
                StdOut.println(String.format("push on right stack %d", i));
                StdOut.println(SEC_DIVIDER);
                stacks[1].push(i);
            }
            println(deque, stacks);
        }

        while (!deque.isEmpty()) {
            StdOut.println(REC_DIVIDER);
            if (stacks[0].size() > 0 && deque.size() % 2 == 0) {
                StdOut.println(String.format(
                        "pop left stack %d", stacks[0].pop())
                );
                StdOut.println(SEC_DIVIDER);
            } else {
                StdOut.println(String.format(
                        "pop right stack %d", stacks[1].pop())
                );
                StdOut.println(SEC_DIVIDER);
            }
            println(deque, stacks);
        }
        StdOut.println(REC_DIVIDER);
    }

}
