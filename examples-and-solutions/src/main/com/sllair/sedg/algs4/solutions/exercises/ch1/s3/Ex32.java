package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex32 {

    public static class Steque<Item> implements Iterable<Item> {

        private static class Node<Item> {
            Item item;
            Node<Item> next;
        }

        private Node<Item> last;
        private int size;

        public boolean isEmpty() {
            return last == null;
        }

        public int size() {
            return this.size;
        }

        public void push(Item item) {
            Node<Item> newNode = new Node<>();
            newNode.item = item;
            if (isEmpty()) {
                this.last = newNode;
                this.last.next = this.last;
            } else {
                newNode.next = this.last.next;
                this.last.next = newNode;
            }
            this.size++;
        }

        public Item pop() {
            if (isEmpty()) {
                throw new NoSuchElementException("Steque underflow!");
            }
            Item result = this.last.next.item;
            this.last.next = this.last.next.next;
            if (--this.size < 1) {
                this.last = null;
            }
            return result;
        }

        public Item peek() {
            if (isEmpty()) {
                throw new NoSuchElementException("Steque underflow!");
            }
            return this.last.next.item;
        }

        public void enqueue(Item item) {
            Node<Item> newNode = new Node<>();
            newNode.item = item;
            if (isEmpty()) {
                newNode.next = newNode;
            } else {
                newNode.next = this.last.next;
                this.last.next = newNode;
            }
            this.last = newNode;
            this.size++;
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
            return new StequeIterator();
        }

        private class StequeIterator implements Iterator<Item> {

            private Node<Item> next;

            public StequeIterator() {
                this.next = isEmpty() ? null : last.next;
            }

            public boolean hasNext() {
                return next != null;
            }

            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                Item result = next.item;
                next = next == last ? null : next.next;
                return result;
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
        StdOut.println(steque);

        for (int i = 10; i < 20; i++) {
            if (steque.size() % 2 == 0) {
                StdOut.println(String.format("push %d", i));
                steque.push(i);
            } else {
                StdOut.println(String.format("enqueue %d", i));
                steque.enqueue(i);
            }
            StdOut.println(steque);
        }

        while (!steque.isEmpty()) {
            StdOut.println(String.format(
                    "pop %d", steque.pop())
            );

            StdOut.println(steque);
        }

        for (int i = 20; i < 30; i++) {
            if (steque.size() % 2 == 1) {
                StdOut.println(String.format("push %d", i));
                steque.push(i);
            } else {
                StdOut.println(String.format("enqueue %d", i));
                steque.enqueue(i);
            }
            StdOut.println(steque);
        }

        while (!steque.isEmpty()) {
            StdOut.println(String.format(
                    "pop %d", steque.pop())
            );

            StdOut.println(steque);
        }
    }

}
