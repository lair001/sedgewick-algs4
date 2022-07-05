package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex27 {

    public static int max(Ex20.LinkedList.Node<Integer> head) {
        int result = 0;

        Ex20.LinkedList.Node<Integer> curr = head;

        while (curr != null) {
            result = Math.max(result, curr.item);
            curr = curr.next;
        }

        return result;
    }

    public static void main(String[] args) {

        Ex20.LinkedList<Integer> list = new Ex20.LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(StdRandom.uniform(128));
        }

        StdOut.println(list);
        StdOut.println(max(list.getNode(0)));
    }
}
