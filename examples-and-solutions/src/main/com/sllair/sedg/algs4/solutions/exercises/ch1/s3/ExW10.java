package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.CharacterUtils;
import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ExW10 {


    public static boolean isPalindrome(String s) {
        Queue<Character> queue = new Queue<>();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (CharacterUtils.isLetter(curr)) {
                curr = CharacterUtils.toUpperCase(curr);
                queue.enqueue(curr);
                stack.push(curr);
            }
        }

        for (int i = 0; i < queue.size(); i++) {
            if (!queue.dequeue().equals(stack.pop())) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW10.class);
        StdOut.println(isPalindrome(in.readAll()));
    }

}
