package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex44 {

    public static class Buffer implements Iterable<Character> {

        private final Stack<Character> left;
        private final Stack<Character> right;

        public Buffer() {
            this.left = new Stack<>();
            this.right = new Stack<>();
        }

        public void insert(char c) {
            this.left.push(c);
        }

        public char delete() {
            /*
             * I.e. do nothing if you hit delete at the end of a file
             * like a text editor would.
             */
            return this.right.isEmpty() ? Character.MIN_VALUE : this.right.pop();
        }

        public void left(int k) {
            while (k > 0 && !this.left.isEmpty()) {
                this.right.push(this.left.pop());
                k--;
            }
        }

        public void right(int k) {
            while (k > 0 && !this.right.isEmpty()) {
                this.left.push(this.right.pop());
                k--;
            }
        }

        public int size() {
            return this.left.size() + this.right.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Character item : this) {
                sb.append(item);
            }

            return sb.toString();
        }

        public Iterator<Character> iterator() {
            return new BufferIterator(this);
        }

        private static class BufferIterator implements Iterator<Character> {

            Stack<Character> reverseLeft;
            Iterator<Character> rightIterator;

            public BufferIterator(Buffer buffer) {
                this.reverseLeft = new Stack<>();
                for (Character item : buffer.left) {
                    reverseLeft.push(item);
                }
                this.rightIterator = buffer.right.iterator();
            }

            public boolean hasNext() {
                return !this.reverseLeft.isEmpty() || this.rightIterator.hasNext();
            }

            public Character next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                return reverseLeft.isEmpty() ? rightIterator.next() : reverseLeft.pop();
            }

            public void remove() {
                throw new UnsupportedOperationException(
                        "Sedgewick does not believe in iterators removing items!"
                );
            }
        }
    }

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        buffer.insert('T');
        buffer.insert('h');
        buffer.insert('e');
        buffer.insert(' ');
        buffer.insert('c');
        buffer.insert('a');
        buffer.insert('t');
        buffer.insert(' ');
        buffer.insert('i');
        buffer.insert('n');
        buffer.insert(' ');
        buffer.insert('t');
        buffer.insert('h');
        buffer.insert('e');
        buffer.insert(' ');
        buffer.insert('r');
        buffer.insert('a');
        buffer.insert('t');
        StdOut.println(buffer);
        StdOut.println(String.format("size %d", buffer.size()));

        buffer.left(3);
        StdOut.println(String.format("delete %c", buffer.delete()));
        StdOut.println(String.format("size %d", buffer.size()));
        buffer.insert('H');
        StdOut.println(buffer);
        StdOut.println(String.format("size %d", buffer.size()));

        buffer.left(12);
        StdOut.println(String.format("delete %c", buffer.delete()));
        StdOut.println(String.format("size %d", buffer.size()));
        buffer.insert('C');
        StdOut.println(buffer);
        StdOut.println(String.format("size %d", buffer.size()));

        buffer.right(10);
        buffer.insert('V');
        buffer.insert('e');
        buffer.insert('r');
        buffer.insert('y');
        buffer.insert(' ');
        buffer.insert('B');
        buffer.insert('i');
        buffer.insert('g');
        buffer.insert(' ');
        StdOut.println(buffer);
        StdOut.println(String.format("size %d", buffer.size()));

        buffer.right(7);
        buffer.insert(' ');
        buffer.insert('w');
        buffer.insert('e');
        buffer.insert('n');
        buffer.insert('t');
        buffer.insert(' ');
        buffer.insert('s');
        buffer.insert('u');
        buffer.insert('n');
        buffer.insert('n');
        buffer.insert('i');
        buffer.insert('n');
        buffer.insert('g');
        buffer.insert('.');
        StdOut.println(buffer);
        StdOut.println(String.format("size %d", buffer.size()));

        buffer.left(8);
        StdOut.println(String.format("delete %c", buffer.delete()));
        StdOut.println(String.format("size %d", buffer.size()));
        buffer.insert('r');
        StdOut.println(buffer);
        StdOut.println(String.format("size %d", buffer.size()));
    }

}
