package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class Ex4 {

    public static class Parentheses {
        private static final Map<Character, Character> PAIRS;

        static {
            Map<Character, Character> tmp = new HashMap<>();
            tmp.put('}', '{');
            tmp.put(')', '(');
            tmp.put(']', '[');
            PAIRS = Collections.unmodifiableMap(tmp);
        }

        private static final Set<Character> OPENERS = Collections.unmodifiableSet(
                new HashSet<>(PAIRS.values())
        );

        private static final Set<Character> CLOSERS = Collections.unmodifiableSet(
                PAIRS.keySet()
        );

        public static boolean balanced(String s) {
            Stack<Character> stack = new Stack<>();

            for (int i = 0; i < s.length(); i++) {
                Character curr = s.charAt(i);
                if (OPENERS.contains(curr)) {
                    stack.push(curr);
                } else if (CLOSERS.contains(curr)) {
                    if (stack.isEmpty() || !PAIRS.get(curr).equals(stack.pop())) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex4.class);
        StdOut.println(Parentheses.balanced(in.readAll()));
    }


}
