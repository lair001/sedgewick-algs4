package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Exercises 20, 21, 24-26 and this one, 31, demonstrate how you should not
 * implement an api. Exposing the list nodes breaks encapsulation and this
 * can easily introduce bugs.  E.g., a linked list can't maintain and rely on
 * a size variable since client code can insert and remove nodes without updating
 * the size variable.
 */
public class Ex31 {

    public static class DoublyLinkedList<Item> implements Iterable<Item> {

        public static class DoubleNode<Item> {
            Item item;
            DoubleNode<Item> next;
            DoubleNode<Item> prev;
        }

        private DoubleNode<Item> head;
        private DoubleNode<Item> tail;

        public boolean isEmpty() {
            return this.head == null;
        }

        public DoubleNode<Item> getNode(int i) {
            if (i < 0) {
                throw new IllegalArgumentException("i must be greater than or equal to 0!");
            }

            DoubleNode<Item> curr;
            curr = head;

            for (int j = 1; j <= i; j++) {
                curr = curr.next;
            }

            return curr;
        }

        public Item get(int i) {
            return this.getNode(i).item;
        }

        public Iterator<Item> iterator() {
            return new DoublyLinkedListIterator();
        }

        public class DoublyLinkedListIterator implements Iterator<Item> {

            private DoubleNode<Item> next = head;

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

        public static <Item> void insertAtBeginning(DoublyLinkedList<Item> list, DoubleNode<Item> node) {
            if (list.isEmpty()) {
                list.head = list.tail = node;
            } else {
                node.next = list.head;
                list.head.prev = node;
                list.head = node;
            }
        }

        public static <Item> void insertAtEnd(DoublyLinkedList<Item> list, DoubleNode<Item> node) {
            if (list.isEmpty()) {
                list.head = list.tail = node;
            } else {
                node.prev = list.tail;
                list.tail.next = node;
                list.tail = node;
            }
        }

        public static <Item> DoubleNode<Item> removeFromBeginning(DoublyLinkedList<Item> list) {
            if (list.isEmpty()) {
                throw new NoSuchElementException("List underflow!");
            }

            DoubleNode<Item> result = list.head;
            list.head = list.head.next;
            if (list.isEmpty()) {
                list.tail = null;
            } else {
                list.head.prev = null;
            }
            result.next = null;

            return result;
        }

        public static <Item> DoubleNode<Item> removeFromEnd(DoublyLinkedList<Item> list) {
            if (list.isEmpty()) {
                throw new NoSuchElementException("List underflow!");
            }

            DoubleNode<Item> result = list.tail;
            list.tail = list.tail.prev;
            if (list.tail == null) {
                list.head = null;
            } else {
                list.tail.next = null;
            }
            result.prev = null;

            return result;
        }

        public static <Item> void insertBefore(DoubleNode<Item> first, DoubleNode<Item> second) {
            if (first == null || second == null) {
                return;
            } else {
                second.next = first;
                second.prev = first.prev;
                if (second.prev != null) {
                    second.prev.next = second;
                }
                first.prev = second;
            }
        }

        public static <Item> void insertAfter(DoubleNode<Item> first, DoubleNode<Item> second) {
            if (first == null || second == null) {
                return;
            } else {
                second.next = first.next;
                first.next = second;
                second.prev = first;
                if (second.next != null) {
                    second.next.prev = second;
                }
            }
        }

        public static <Item> DoubleNode<Item> remove(DoubleNode<Item> node) {
            if (node != null) {
                DoubleNode<Item> prev = node.prev;
                DoubleNode<Item> next = node.next;
                if (prev != null) {
                    prev.next = next;
                }
                if (next != null) {
                    next.prev = prev;
                }
                node.next = null;
                node.prev = null;
            }
            return node;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        StdOut.println(list);

        StdOut.println("insert 2 at the beginning");
        DoublyLinkedList.DoubleNode<Integer> two = new DoublyLinkedList.DoubleNode<>();
        two.item = 2;
        DoublyLinkedList.insertAtBeginning(list, two);
        StdOut.println(list);

        StdOut.println("insert 1 at the beginning");
        DoublyLinkedList.DoubleNode<Integer> one = new DoublyLinkedList.DoubleNode<>();
        one.item = 1;
        DoublyLinkedList.insertAtBeginning(list, one);
        StdOut.println(list);

        StdOut.println("insert 3 at the end");
        DoublyLinkedList.DoubleNode<Integer> three = new DoublyLinkedList.DoubleNode<>();
        three.item = 3;
        DoublyLinkedList.insertAtEnd(list, three);
        StdOut.println(list);

        StdOut.println("insert 4 at the end");
        DoublyLinkedList.DoubleNode<Integer> four = new DoublyLinkedList.DoubleNode<>();
        four.item = 4;
        DoublyLinkedList.insertAtEnd(list, four);
        StdOut.println(list);

        StdOut.println("insert 0 at the beginning");
        DoublyLinkedList.DoubleNode<Integer> zero = new DoublyLinkedList.DoubleNode<>();
        zero.item = 0;
        DoublyLinkedList.insertAtBeginning(list, zero);
        StdOut.println(list);

        StdOut.println("remove from the beginning");
        StdOut.println(DoublyLinkedList.removeFromBeginning(list).item);
        StdOut.println(list);

        StdOut.println("remove from the end");
        StdOut.println(DoublyLinkedList.removeFromEnd(list).item);
        StdOut.println(list);

        StdOut.println("insert 0 after two");
        DoublyLinkedList.insertAfter(two, zero);
        StdOut.println(list);

        StdOut.println("insert 4 before zero");
        DoublyLinkedList.insertBefore(zero, four);
        StdOut.println(list);

        StdOut.println("remove 2");
        StdOut.println(DoublyLinkedList.remove(two).item);
        StdOut.println(list);

    }

}
