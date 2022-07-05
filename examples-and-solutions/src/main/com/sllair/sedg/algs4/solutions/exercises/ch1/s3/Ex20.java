package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Ex20 {

    public static class LinkedList<Item> implements Iterable<Item> {

        public static class Node<Item> {
            public Item item;
            public Node<Item> next;
        }

        private Node<Item> head;
        private Node<Item> tail;

        public boolean isEmpty() {
            return this.head == null;
        }

        public void add(Item item) {
            Node<Item> oldTail = tail;
            tail = new Node<>();
            tail.item = item;
            if (isEmpty()) {
                head = tail;
            } else {
                oldTail.next = tail;
            }
        }

        Node<Item> getNode(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("i must be greater than or equal to 0!");
            }

            Node<Item> curr = head;

            int j = 1;
            while (j <= i && curr != null) {
                curr = curr.next;
                j++;
            }

            return curr;
        }

        public Item get(int i) {
            Node<Item> node = this.getNode(i);
            return node == null ? null : node.item;
        }

        public boolean delete(int k) {
            if (k < 0) {
                throw new IllegalArgumentException("k must be greater than or equal to 0!");
            }

            if (k == 0) {
                this.head = head.next;
                if (isEmpty()) {
                    this.tail = null;
                }
            } else {
                Node<Item> prev = this.getNode(k - 1);
                if (prev == null || prev.next == null) {
                    return false;
                } else {
                    prev.next = prev.next.next;
                    if (prev.next == null) {
                        this.tail = prev;
                    }
                }
            }
            return true;
        }

        public Iterator<Item> iterator() {
            return new LinkedListIterator();
        }

        private class LinkedListIterator implements Iterator<Item> {

            Node<Item> next = head;

            public boolean hasNext() {
                return next != null;
            }

            public Item next() {
                Item result = next.item;
                next = next.next;
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

        LinkedList<Integer> list = new LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        StdOut.println(list);

        StdOut.println("delete 9");
        list.delete(9);
        StdOut.println(list);
        StdOut.println("delete 0");
        list.delete(0);
        StdOut.println(list);
        StdOut.println("delete 0");
        list.delete(0);
        StdOut.println(list);
        StdOut.println("delete 4");
        list.delete(4);
        StdOut.println(list);
    }


}
