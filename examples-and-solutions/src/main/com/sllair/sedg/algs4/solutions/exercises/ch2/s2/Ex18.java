package com.sllair.sedg.algs4.solutions.exercises.ch2.s2;

import com.sllair.sedg.algs4.utils.DoubleUtils;
import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex18 {

    public static class Node<Item> {

        Node(Item item) {
            this.item = item;
        }

        public Item item;
        public Node<Item> next;
    }

    private static class ShuffleResult<Item> {
        Node<Item> head;
        int numOfSwaps;
    }

    public static <Item> Node<Item> buildLinkedList(Item[] items) {
        Node<Item> head = new Node<>(items[0]);
        Node<Item> prev = head;

        for (int i = 1; i < items.length; ++i) {
            prev.next = new Node<>(items[i]);
            prev = prev.next;
        }
        return head;
    }

    public static <Item> int getSize(Node<Item> head) {
        if (head == null) {
            return 0;
        }
        int sz = 1;
        while (head.next != null) {
            ++sz;
            head = head.next;
        }
        return sz;
    }

    public static <Item> ShuffleResult<Item> shuffle(Node<Item> head) {
        int n = getSize(head);
        Node<Item> sentinel = new Node<>(null);
        sentinel.next = head;
        ShuffleResult<Item> result = new ShuffleResult<>();
        for (int len = 1; len < n; len *= 2) {

            Node<Item> loPrev = sentinel;
            Node<Item> mid = sentinel;
            Node<Item> hi = sentinel;

            while (loPrev.next != null) {
                for (int j = 0; j < (len + len); ++j) {
                    if (hi.next != null) {
                        hi = hi.next;
                        if (j < len) {
                            mid = mid.next;
                        }
                    } else {
                        break;
                    }
                }
                loPrev = shuffle(loPrev, mid, hi, result);
                mid = loPrev;
                hi = loPrev;
            }
        }
        result.head = sentinel.next;
        return result;
    }

    private static <Item> Node<Item> shuffle(
            Node<Item> loPrev, Node<Item> mid, Node<Item> hi, ShuffleResult<Item> result) {
        if (mid == hi) {
            return hi;
        }

        Node<Item> prev = loPrev;
        Node<Item> left = loPrev.next;
        Node<Item> right = mid.next;

        while (true) {
            ++result.numOfSwaps;
            if (StdRandom.uniform() < 0.5) {
                prev.next = left;
                prev = prev.next;
                if (left == mid) {
                    prev.next = right;
                    return hi;
                } else {
                    left = left.next;
                }
            } else {
                prev.next = right;
                prev = prev.next;
                if (right == hi) {
                    mid.next = hi.next;
                    prev.next = left;
                    return mid;
                } else {
                    right = right.next;
                }
            }
        }
    }

    public static void performanceTest() {
        int N = 128;

        while (N < Integer.MAX_VALUE / 2) {
            ShuffleResult<Double> result = shuffle(buildLinkedList(DoubleUtils.getRandomArray(N)));
            StdOut.println(String.format("%d %f", N, result.numOfSwaps / (N * Math.log(N))));
            N += N;
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && "pt".equals(args[0])) {
            performanceTest();
        } else {
            In in = InFactory.get(args, Ex18.class);
            String[] a = in.readAllStrings();
            Node<String> head = buildLinkedList(a);
            Node<String> curr = head;
            while (curr != null) {
                StdOut.println(curr.item);
                curr = curr.next;
            }
            ShuffleResult<String> result = shuffle(head);
            StdOut.println();
            curr = result.head;
            while (curr != null) {
                StdOut.println(curr.item);
                curr = curr.next;
            }
            StdOut.println(result.numOfSwaps / (a.length * Math.log(a.length)));
        }
    }
}
