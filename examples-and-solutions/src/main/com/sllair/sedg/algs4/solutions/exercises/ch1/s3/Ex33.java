package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex33 {

    private static final String DIVIDER = String.join("", Collections.nCopies(53, "-"));

    public interface IDeque<Item> extends Iterable<Item> {
        boolean isEmpty();

        int size();

        void pushLeft(Item item);

        void pushRight(Item item);

        Item popLeft();

        Item popRight();

        Item peekLeft();

        Item peekRight();

        Iterator<Item> reverseIterator();
    }

    public static class Deque<Item> implements IDeque<Item> {

        private static class Node<Item> {
            private Item item;
            private Node<Item> prev;
            private Node<Item> next;
        }

        public Deque() {

        }

        private Node<Item> left;
        private int size;

        public boolean isEmpty() {
            return this.left == null;
        }

        public int size() {
            return this.size;
        }

        private void push(Item item, boolean isLeft) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            Node<Item> newNode = new Node<>();
            newNode.item = item;

            if (isEmpty()) {
                newNode.next = newNode;
                newNode.prev = newNode;
                this.left = newNode;
            } else {
                newNode.next = this.left;
                newNode.prev = this.left.prev;
                this.left.prev.next = newNode;
                this.left.prev = newNode;
                if (isLeft) {
                    this.left = newNode;
                }
            }

            this.size++;
        }

        public void pushLeft(Item item) {
            this.push(item, true);
        }

        public void pushRight(Item item) {
            this.push(item, false);
        }

        private Item pop(Node<Item> node, boolean isLeft) {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            Item result = node.item;
            node.prev.next = node.next;
            node.next.prev = node.prev;
            if (isLeft) {
                this.left = node.next;
            }
            if (--this.size < 1) {
                this.left = null;
            }
            return result;
        }

        public Item popLeft() {
            return pop(this.left, true);
        }

        public Item popRight() {
            return pop(this.left.prev, false);
        }

        private Item peek(Node<Item> node) {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            return node.item;
        }

        public Item peekLeft() {
            return this.peek(this.left);
        }

        public Item peekRight() {
            return this.peek(this.left.prev);
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
            return new DequeIterator();
        }

        private class DequeIterator implements Iterator<Item> {

            private Node<Item> next = left;

            public boolean hasNext() {
                return this.next != null;
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                Item result = next.item;
                this.next = this.next.next == left ? null : this.next.next;
                return result;
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }

        public Iterator<Item> reverseIterator() {
            return new ReverseDequeIterator();
        }

        private class ReverseDequeIterator implements Iterator<Item> {

            private Node<Item> next = left == null ? null : left.prev;

            public boolean hasNext() {
                return this.next != null;
            }

            public Item next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                Item result = next.item;
                this.next = this.next.prev == left.prev ? null : this.next.prev;
                return result;
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    public static class ResizingArrayDeque<Item> implements IDeque<Item> {

        private Item[] d;
        private int sz;
        private int l;
        private int r;

        public ResizingArrayDeque() {
            this.d = (Item[]) new Object[2];
            this.sz = 0;
            this.l = 0;
            this.r = this.d.length - 1;
        }

        private void resize(int newSize) {
            Item[] newArray = (Item[]) new Object[newSize];

            int p = this.l - 1;

            for (int i = this.sz - 1; i >= 0; i--) {
                if (p < 0) {
                    p = this.d.length - 1;
                }
                newArray[i] = this.d[p];
                --p;
            }

            this.d = newArray;
            this.l = this.sz;
            this.r = this.d.length - 1;


//            for (int i = this.sz / 2 - 1; i >= 0; i--) {
//                if (p < 0) {
//                    p = this.d.length - 1;
//                }
//                newArray[i] = this.d[p];
//                --p;
//            }
//
//            for (int i = newArray.length - 1; i >= newArray.length - this.sz / 2; i--) {
//                if (p < 0) {
//                    p = this.d.length - 1;
//                }
//                newArray[i] = this.d[p];
//                --p;
//            }
//
//            this.l = this.sz / 2;
//            this.r = newArray.length - this.sz / 2 - 1;
//            this.d = newArray;
        }

        public boolean isEmpty() {
            return this.sz == 0;
        }

        public int size() {
            return this.sz;
        }

        public void pushLeft(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            if (this.sz == this.d.length) {
                this.resize(2 * this.d.length);
            }
            this.d[this.l++] = item;
            if ((this.l) >= this.d.length) {
                this.l = 0;
            }
            this.sz++;
        }

        public void pushRight(Item item) {
            if (item == null) {
                throw new IllegalArgumentException("Cannot push a null item!");
            }
            if (this.sz == this.d.length) {
                this.resize(2 * this.d.length);
            }
            this.d[this.r--] = item;
            if (this.r < 0) {
                this.r = this.d.length - 1;
            }
            this.sz++;
        }

        public Item popLeft() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            if (this.l <= 0) {
                this.l = this.d.length - 1;
            } else {
                --this.l;
            }
            Item result = this.d[l];
            this.d[l] = null;
            --this.sz;
            if (this.sz > 0 && this.sz < this.d.length / 4) {
                this.resize(this.d.length / 2);
            }

            return result;
        }

        public Item popRight() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot pop an item from an empty deque!"
                );
            }
            if (this.r >= this.d.length - 1) {
                this.r = 0;
            } else {
                ++this.r;
            }
            Item result = this.d[this.r];
            this.d[this.r] = null;
            --this.sz;
            if (this.sz > 0 && this.sz < this.d.length / 4) {
                this.resize(this.d.length / 2);
            }

            return result;
        }

        public Item peekLeft() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            int p = this.l - 1;
            if (p < 0) {
                p = this.d.length - 1;
            }
            return this.d[p];
        }

        public Item peekRight() {
            if (this.isEmpty()) {
                throw new NoSuchElementException(
                        "Cannot peek into an empty deque!"
                );
            }
            int p = this.r + 1;
            if (p >= this.d.length) {
                p = 0;
            }
            return this.d[p];
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

        public Iterator<Item> reverseIterator() {
            return new ReverseResizingArrayDequeIterator();
        }

        public Iterator<Item> iterator() {
            return new ResizingArrayDequeIterator();
        }

        public class ReverseResizingArrayDequeIterator implements Iterator<Item> {

            private int next;
            private int size;

            public ReverseResizingArrayDequeIterator() {
                this.next = r + 1;
                if (this.next >= d.length) {
                    this.next = 0;
                }
                this.size = sz;
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
                --this.size;
                if (this.next >= d.length) {
                    this.next = 0;
                }
                return d[this.next++];
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }

        public class ResizingArrayDequeIterator implements Iterator<Item> {
            private int next;
            private int size;

            public ResizingArrayDequeIterator() {
                this.next = l - 1;
                if (this.next < 0) {
                    this.next = d.length - 1;
                }
                this.size = sz;
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
                --this.size;
                if (this.next < 0) {
                    this.next = d.length - 1;
                }
                return d[this.next--];
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    private static IDeque<Integer> getDeque(String[] args) {
        if (args.length < 1 || "l".equals(args[0])) {
            return new Deque<>();
        } else if ("a".equals(args[0])) {
            return new ResizingArrayDeque<>();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        IDeque<Integer> deque = getDeque(args);
        if (deque == null) {
            StdOut.println(String.format("Invalid deque type provided: %s", args[0]));
            return;
        }
        StdOut.println(DIVIDER);
        StdOut.println(deque);

        for (int i = 0; i < 20; i++) {
            StdOut.println(DIVIDER);
            if (deque.size() % 2 == 0) {
                StdOut.println(String.format("pushLeft %d", i));
                deque.pushLeft(i);
                StdOut.println(String.format("peekRight %d", deque.peekRight()));
            } else {
                StdOut.println(String.format("pushRight %d", i));
                deque.pushRight(i);
                StdOut.println(String.format("peekLeft %d", deque.peekLeft()));
            }
            StdOut.println(deque);
        }

        while (!deque.isEmpty()) {
            StdOut.println(DIVIDER);
            if (deque.size() % 2 == 0) {
                StdOut.println(String.format("peekRight %d", deque.peekRight()));
                StdOut.println(String.format(
                        "popLeft %d", deque.popLeft())
                );
            } else {
                StdOut.println(String.format("peekLeft %d", deque.peekLeft()));
                StdOut.println(String.format(
                        "popRight %d", deque.popRight())
                );
            }
            StdOut.println(deque);
        }
        StdOut.println(DIVIDER);
    }

}
