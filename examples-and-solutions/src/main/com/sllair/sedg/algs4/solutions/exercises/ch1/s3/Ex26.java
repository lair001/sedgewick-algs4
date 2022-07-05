package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import edu.princeton.cs.algs4.StdOut;

public class Ex26 {

    public static void remove(Ex20.LinkedList<String> list, String key) {
        while (!list.isEmpty() && equals(key, list.get(0))) {
            list.delete(0);
        }

        Ex20.LinkedList.Node<String> curr = list.getNode(0);

        while (curr != null) {
            while (curr.next != null && equals(key, curr.next.item)) {
                Ex24.removeAfter(curr);
            }
            curr = curr.next;
        }
    }

    private static boolean equals(String key, String item) {
        if (key == item) { // covers case where key and item are null
            return true;
        } else if (key == null) {
            return false;
        } else {
            return key.equals(item);
        }
    }

    public static void main(String[] args) {
        Ex20.LinkedList<String> list = new Ex20.LinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
            list.add("0");
            list.add("0");
            list.add("0");
        }

        StdOut.println(list);
        remove(list, "0");
        StdOut.println(list);
    }

}
