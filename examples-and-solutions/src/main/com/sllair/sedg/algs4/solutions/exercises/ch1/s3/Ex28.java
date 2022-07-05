package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Ex28 {

    public static int max(Ex20.LinkedList.Node<Integer> curr) {
        if (curr == null) {
            return 0;
        } else {
            return Math.max(curr.item, max(curr.next));
        }
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
