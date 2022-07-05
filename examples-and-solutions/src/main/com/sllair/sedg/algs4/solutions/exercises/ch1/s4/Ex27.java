package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Ex27<Item> implements Iterable<Item> {

    private final Stack<Item> enqueueStack = new Stack<>();
    private final Stack<Item> dequeueStack = new Stack<>();

    public int size() {
        return this.enqueueStack.size() + this.dequeueStack.size();
    }

    public boolean isEmpty() {
        return enqueueStack.isEmpty() && dequeueStack.isEmpty();
    }

    public void enqueue(Item item) {
        this.enqueueStack.push(item);
    }

    public Item dequeue() {
        if (this.dequeueStack.isEmpty()) {
            moveFromEnqueueToDequeue();
        }
        return this.dequeueStack.pop();
    }

    public Item peek() {
        if (this.dequeueStack.isEmpty()) {
            moveFromEnqueueToDequeue();
        }
        return this.dequeueStack.peek();
    }

    private void moveFromEnqueueToDequeue() {
        while (!this.enqueueStack.isEmpty()) {
            this.dequeueStack.push(this.enqueueStack.pop());
        }
    }

    public Iterator<Item> iterator() {
        return new Ex27Iterator<>(this);
    }

    private static class Ex27Iterator<Item> implements Iterator<Item> {

        public Iterator<Item> dequeueStackIterator;
        public Stack<Item> reverseEnqueueStack;

        public Ex27Iterator(Ex27<Item> queue) {
            this.dequeueStackIterator = queue.dequeueStack.iterator();
            this.reverseEnqueueStack = new Stack<>();
            for (Item item : queue.enqueueStack) {
                this.reverseEnqueueStack.push(item);
            }
        }

        public boolean hasNext() {
            return dequeueStackIterator.hasNext() || !this.reverseEnqueueStack.isEmpty();
        }

        public Item next() {
            if (dequeueStackIterator.hasNext()) {
                return dequeueStackIterator.next();
            } else {
                return reverseEnqueueStack.pop();
            }
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

    public static void main(String[] args) {
        Ex27<Integer> queue = new Ex27<>();
        StdOut.println(queue);

        for (int i = 10; i < 20; i++) {
            StdOut.println(String.format("enqueue %d", i));
            queue.enqueue(i);
            StdOut.println(queue);
        }

        StdOut.println(String.format("dequeue %d", queue.dequeue()));
        StdOut.println(queue);

        for (int i = 20; i < 30; i++) {
            StdOut.println(String.format("enqueue %d", i));
            queue.enqueue(i);
            StdOut.println(queue);
        }

        while (!queue.isEmpty()) {
            StdOut.println(String.format("dequeue %d", queue.dequeue()));
            StdOut.println(queue);
        }
    }
}
