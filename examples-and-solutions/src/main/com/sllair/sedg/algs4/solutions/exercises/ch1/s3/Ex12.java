package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Ex12 {

    /*
     *  Why limit ourselves to copying stacks of strings when we
     *  can use parameterize a generic Item?
     */
    public static <Item> Stack<Item> copy(Stack<Item> stack) {
        Stack<Item> tmp = new Stack<>();
        Stack<Item> copy = new Stack<>();

        // reverses order
        for (Item item : stack) {
            tmp.push(item);
        }

        // restore original order
        for (Item item : tmp) {
            copy.push(item);
        }

        return copy;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex12.class);

        Stack<String> original = new Stack<>();
        while (!in.isEmpty()) {
            original.push(in.readString());
        }

        Stack<String> copy = copy(original);

        StdOut.println("Original");
        StdOut.println(original);
        StdOut.println("Copy");
        StdOut.println(copy);
        StdOut.println("Is the copy the same object as the original?");
        StdOut.println(copy == original);
    }

}
