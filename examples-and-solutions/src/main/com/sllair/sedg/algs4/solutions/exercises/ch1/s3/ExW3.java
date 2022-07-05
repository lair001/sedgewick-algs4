package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class ExW3 {

    public interface IRemovableCollection<Item> extends Iterable<Item> {
        boolean isEmpty();

        void insert(Item item);

        Item remove(int i);
    }

    public static class Array<Item> implements IRemovableCollection<Item> {

        private Item[] items;
        private int n;

        public Array() {
            this.items = (Item[]) new Object[1];
            this.n = 0;
        }

        // ~1
        public boolean isEmpty() {
            return this.n > 0;
        }

        // Amortized ~1; ~N worst case
        public void insert(Item item) {
            if (this.n == this.items.length) {
                resize(this.items.length * 2);
            }
            this.items[this.n++] = item;
        }

        /*
         * Assuming that i = 0 means to remove the last element
         * and i = 1 means to remove next to last element
         * Amortized ~N; ~2N worst case
         */
        public Item remove(int i) {
            if (i < 0 || i > this.n - 1) {
                throw new IllegalArgumentException(
                        String.format("i = %d is out of bounds for a collection of size %d!", i, this.n)
                );
            }

            int indexToRemove = this.n - 1 - i;
            Item result = this.items[indexToRemove];
            for (int j = indexToRemove; j < this.n - 1; j++) {
                this.items[j] = this.items[j + 1];
            }
            this.items[--this.n] = null;
            if (this.n < this.items.length / 4) {
                this.resize(this.items.length / 2);
            }
            return result;
        }

        private void resize(int newSize) {
            Item[] newItems = (Item[]) new Object[newSize];
            for (int i = 0; i < this.n; i++) {
                newItems[i] = this.items[i];
            }
            this.items = newItems;
        }

        private static class ArrayIterator<Item> implements Iterator<Item> {

            private int i;
            private final Array<Item> collection;

            public ArrayIterator(Array<Item> collection) {
                this.i = 0;
                this.collection = collection;
            }

            public boolean hasNext() {
                return i < collection.n;
            }

            public Item next() {
                return collection.items[i++];
            }
        }

        public Iterator<Item> iterator() {
            return new ArrayIterator<>(this);
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

    public static class LinkedList<Item> implements IRemovableCollection<Item> {

        private static class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        private Node<Item> head;
        private Node<Item> tail;
        private int n;

        // ~1
        public boolean isEmpty() {
            return this.head == null;
        }

        // ~1
        public void insert(Item item) {
            Node<Item> oldTail = tail;
            tail = new Node<>();
            tail.item = item;
            if (this.isEmpty()) {
                head = tail;
            } else {
                oldTail.next = tail;
            }
            this.n++;
        }

        /*
         * Assuming that i = 0 means to remove the last element
         * and i = 1 means to remove next to last element
         * ~N
         */
        public Item remove(int i) {
            if (i < 0 || i > this.n - 1) {
                throw new IllegalArgumentException(
                        String.format("i = %d is out of bounds for a collection of size %d!", i, this.n)
                );
            }

            Node<Item> prev = null;
            Node<Item> curr = head;

            for (int j = 0; j < this.n - 1 - i; j++) {
                prev = curr;
                curr = prev.next;
            }

            if (prev == null) {
                head = null;
                tail = null;
            } else {
                prev.next = curr.next;
                if (prev.next == null) {
                    tail = prev;
                }
            }

            this.n--;
            return curr.item;
        }

        public Iterator<Item> iterator() {
            return new LinkedListIterator<>(this);
        }

        private static class LinkedListIterator<Item> implements Iterator<Item> {

            private Node<Item> next;

            LinkedListIterator(LinkedList<Item> collection) {
                this.next = collection.head;
            }

            public boolean hasNext() {
                return next != null;
            }

            public Item next() {
                Item result = next.item;
                this.next = this.next.next;
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

    private static IRemovableCollection<Integer> getRemovable(String[] args) {
        if (args.length < 2 || "a".equals(args[1])) {
            return new ExW3.Array<>();
        } else if ("l".equals(args[1])) {
            return new ExW3.LinkedList<>();
        } else {
            return null;
        }
    }


    public static void main(String[] args) {
        In in = InFactory.get(args, ExW3.class);
        IRemovableCollection<Integer> collection = getRemovable(args);

        if (collection == null) {
            StdOut.println(String.format("Invalid removable collection type provided: %s", args[0]));
            return;
        }

        while (!in.isEmpty()) {
            String cmd = in.readString();
            Integer i = in.readInt();

            StdOut.println(String.format("%s %d", cmd, i));

            if ("i".equals(cmd)) {
                collection.insert(i);
            } else if ("r".equals(cmd)) {
                StdOut.println(collection.remove(i));
            }

            StdOut.println(collection);
        }
    }

}
