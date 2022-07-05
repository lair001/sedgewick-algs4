package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ex29 {

    public static class CircularQueue<Item> implements Iterable<Item> {

        private static class Node<Item> {
            public Item item;
            public Node<Item> next;
        }

        private Node<Item> last;
        private int n;

        public boolean isEmpty() {
            return this.last == null;
        }

        public int size() {
            return n;
        }

        public void enqueue(Item item) {
            Node<Item> oldLast = last;
            last = new Node<>();
            last.item = item;
            if (oldLast == null) {
                last.next = last;
            } else {
                last.next = oldLast.next;
                oldLast.next = last;
            }
            this.n++;
        }

        public Item dequeue() {
            if (isEmpty()) {
                throw new NoSuchElementException("Queue underflow");
            }
            Item result = this.last.next.item;
            if (last == last.next) {
                last = null;
            } else {
                last.next = last.next.next;
            }
            this.n--;
            return result;
        }


        public Iterator<Item> iterator() {
            return new CircularQueueIterator();
        }

        private class CircularQueueIterator implements Iterator<Item> {

            Node<Item> next = isEmpty() ? null : last.next;

            public boolean hasNext() {
                return next != null;
            }

            public Item next() {
                Item result = next.item;
                next = next.next == last.next ? null : next.next;
                return result;
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
    }

    public static void main(String[] args) {

        CircularQueue<Integer> queue = new CircularQueue<>();

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            StdOut.println(queue);
        }

        while (!queue.isEmpty()) {
            StdOut.println(queue.dequeue());
            StdOut.println(queue);
        }
    }

}
