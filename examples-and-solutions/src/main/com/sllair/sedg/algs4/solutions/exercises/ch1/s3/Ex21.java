package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

public class Ex21 {

    public static boolean find(Ex20.LinkedList<String> list, String key) {
        for (String item : list) {
            if (item == key // covers case where item and key are both null
                    || (key != null && key.equals(item))) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        Ex20.LinkedList<String> list = new Ex20.LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }

        StdOut.println(list);

        for (int i = 0; i <= 10; i++) {
            StdOut.println(String.format("find %d", i));
            StdOut.println(find(list, String.valueOf(i)));
        }
    }

}
