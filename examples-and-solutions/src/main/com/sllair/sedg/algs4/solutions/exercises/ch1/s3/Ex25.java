package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

public class Ex25 {

    public static <Item> void insertAfter(
            Ex20.LinkedList.Node<Item> first,
            Ex20.LinkedList.Node<Item> second) {
        if (first == null || second == null) {
            return;
        } else {
            Ex20.LinkedList.Node<Item> third = first.next;
            first.next = second;
            second.next = third;
        }
    }

    public static void main(String[] args) {

        Ex20.LinkedList<Integer> list = new Ex20.LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        StdOut.println(list);

        Ex20.LinkedList.Node<Integer> node;

        StdOut.println("insert value 10 after index 9");
        node = new Ex20.LinkedList.Node<>();
        node.item = 10;
        insertAfter(list.getNode(9), node);
        StdOut.println(list);

        StdOut.println("insert value 11 after index 0");
        node = new Ex20.LinkedList.Node<>();
        node.item = 11;
        insertAfter(list.getNode(0), node);
        StdOut.println(list);

        StdOut.println("insert value 12 after index 5");
        node = new Ex20.LinkedList.Node<>();
        node.item = 12;
        insertAfter(list.getNode(5), node);
        StdOut.println(list);


    }

}
