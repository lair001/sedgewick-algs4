package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex40 {

    /*
     * This problem could be easily handled with a hash table. However, this data
     * structure hasn't been introduced yet. We can also use a an array of references
     * to linked list nodes if we limit ourselves to a set of supported characters.
     * Assuming that we're on a 64-bit system where an object reference costs 8 bytes,
     * supporting ASCII (which has 128 characters) would cost us 128 * 8 = 1 KB plus 24
     * bytes of overhead for the array (which is itself an object).  For another KB,
     * we also support the Latin-1 Supplement, giving us all of the standard control
     * codes plus nearly all of the characters commonly used by speakers of modern
     * Germanic and Western Romance languages. With the glaring exception of the euro
     * symbol, which you could make a special mapping for if you really must have it.
     *
     * Non-embedded engineers will almost certainly use a hash table in the real world.
     * However, if you're an embedded engineer who needs to implement this algorithm
     * using a small set of unique characters on a system with limited memory, you may
     * want to consider this approach. Granted, you probably won't be using the JVM or
     * a 64-bit OS in that situation.
     *
     * This note was written using only ASCII characters, btw.
     */
    public static class MoveToFront implements Iterable<Character> {

        private static final String UNSUPPORTED_CHAR_MSG_FMT = "%c is not supported by this program.";

        private class Node {
            private char item;
            private Node next;
            private Node prev;
        }

        private final Node[] charsToNode = new Node[257];
        private Node head;

        private char charToIndex(char c) {
            if (c > 256 && c != '€') {
                throw new IllegalArgumentException(String.format(UNSUPPORTED_CHAR_MSG_FMT, c));
            } else if (c == '€') { // Kludge to satisfy irate European customers.
                return 256;
            } else {
                return c;
            }
        }

        private boolean isEmpty() {
            return this.head == null;
        }

        public void moveToFront(char c) {
            Node nodeForC = this.charsToNode[this.charToIndex(c)];
            if (nodeForC == this.head && !this.isEmpty()) {
                return;
            } else if (nodeForC == null) {
                nodeForC = new Node();
                nodeForC.item = c;
                this.charsToNode[this.charToIndex(c)] = nodeForC;
                nodeForC.prev = null;
                nodeForC.next = this.head;
                if (!this.isEmpty()) {
                    this.head.prev = nodeForC;
                }
            } else {
                nodeForC.prev.next = nodeForC.next;
                nodeForC.next.prev = nodeForC.prev;
                nodeForC.next = this.head;
                this.head.prev = nodeForC;
            }
            this.head = nodeForC;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Character item : this) {
                sb.append(item);
            }

            return sb.toString();
        }

        public Iterator<Character> iterator() {
            return new MoveToFrontIterator(this);
        }

        private static class MoveToFrontIterator implements Iterator<Character> {

            private Node next;

            public MoveToFrontIterator(MoveToFront mtf) {
                this.next = mtf.head;
            }

            public boolean hasNext() {
                return next != null;
            }

            public Character next() {
                if (!this.hasNext()) {
                    throw new NoSuchElementException(
                            "This iterator is out of items!"
                    );
                }
                Character result = next.item;
                next = next.next;
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
        In in = InFactory.get(args, Ex40.class);
        MoveToFront mtf = new MoveToFront();
        StdOut.println(mtf);

        while (!in.isEmpty()) {
            char c = in.readChar();
            StdOut.println(String.format("move to front: %c", c));
            mtf.moveToFront(c);
            StdOut.println(mtf);
        }
    }
}
