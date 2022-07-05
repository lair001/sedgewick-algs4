package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

public class Ex24 {

    public static <Item> void removeAfter(Ex20.LinkedList.Node<Item> node) {
        if (node == null || node.next == null) {
            return;
        } else {
            node.next = node.next.next;
        }
    }

    public static void main(String[] args) {

        Ex20.LinkedList<Integer> list = new Ex20.LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        StdOut.println(list);

        StdOut.println("remove after index 9");
        removeAfter(list.getNode(9));
        StdOut.println(list);
        StdOut.println("remove after index 0");
        removeAfter(list.getNode(0));
        StdOut.println(list);
        StdOut.println("remove after index 0");
        removeAfter(list.getNode(0));
        StdOut.println(list);
        StdOut.println("remove after index 4");
        removeAfter(list.getNode(4));
        StdOut.println(list);

    }

}
