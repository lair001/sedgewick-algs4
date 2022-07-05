package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
 * The problem doesn't require supporting unary operators, but
 * such support is pretty easy to add.
 */
public class Ex9 {

    private static final String CLOSER = ")";
    private static final String OPENER = "(";
    private static final Set<String> UNARY_OPS;

    static {
        Set<String> tmp = new HashSet<>();
        tmp.add("sqrt");
        tmp.add("cbrt"); // cubic root
        tmp.add("cos");
        tmp.add("arccos");
        tmp.add("sin");
        tmp.add("arcsin");
        tmp.add("tan");
        tmp.add("arctan");
        tmp.add("lg"); // base 2
        tmp.add("ln"); // base e
        tmp.add("log"); // base 10

        UNARY_OPS = Collections.unmodifiableSet(tmp);
    }

    private static final Set<String> BINARY_OPS;

    static {
        Set<String> tmp = new HashSet<>();
        tmp.add("*");
        tmp.add("/");
        tmp.add("+");
        tmp.add("-");

        BINARY_OPS = Collections.unmodifiableSet(tmp);
    }

    private static final Set<String> OPS;

    static {
        Set<String> tmp = new HashSet<>();
        tmp.addAll(UNARY_OPS);
        tmp.addAll(BINARY_OPS);

        OPS = Collections.unmodifiableSet(tmp);
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex9.class);

        Stack<String> operators = new Stack<>();
        Stack<String> operands = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while (!in.isEmpty()) {
            String curr = in.readString();

            if (CLOSER.equals(curr)) {
                sb.setLength(0);
                String operator = operators.pop();

                if (UNARY_OPS.contains(operator)) {
                    String operand = operands.pop();
                    sb.append(operator);
                    sb.append(' ');
                    sb.append(OPENER);
                    sb.append(' ');
                    sb.append(operand);
                    sb.append(' ');
                    sb.append(CLOSER);
                } else if (BINARY_OPS.contains(operator)) {
                    if (!operators.isEmpty() && UNARY_OPS.contains(operators.peek())) {
                        sb.append(operators.pop());
                        sb.append(' ');
                    }

                    String secondOperand = operands.pop();
                    String firstOperand = operands.pop();

                    sb.append(OPENER);
                    sb.append(' ');
                    sb.append(firstOperand);
                    sb.append(' ');
                    sb.append(operator);
                    sb.append(' ');
                    sb.append(secondOperand);
                    sb.append(' ');
                    sb.append(CLOSER);
                }

                operands.push(sb.toString());
            } else if (OPS.contains(curr)) {
                operators.push(curr);
            } else {
                operands.push(curr);
            }
        }

        StdOut.println(operands.pop());
    }

}
