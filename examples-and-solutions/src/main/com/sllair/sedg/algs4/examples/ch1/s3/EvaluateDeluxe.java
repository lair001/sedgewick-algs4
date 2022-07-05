package com.sllair.sedg.algs4.examples.ch1.s3; /******************************************************************************
 *  Compilation:  javac EvaluateDeluxe.java
 *  Execution:    java EvaluateDeluxe
 *  Dependencies: Stack.java
 *
 *  Evaluates arithmetic expressions using Dijkstra's two-stack algorithm.
 *  Handles the following binary operators: +, -, *, / and parentheses.
 *
 *  % echo "3 + 5 * 6 - 7 * ( 8 + 5 )" | java EvaluateDeluxe
 *  -58.0
 *
 *
 *  Limitiations
 *  --------------
 *    -  can easily add additional operators and precedence orders, but they
 *       must be left associative (exponentiation is right associative)
 *    -  assumes whitespace between operators (including parentheses)
 *
 *  Remarks
 *  --------------
 *    -  can eliminate second phase if we enclose input expression
 *       in parentheses (and, then, could also remove the test
 *       for whether the operator stack is empty in the inner while loop)
 *    -  see http://introcs.cs.princeton.edu/java/11precedence/ for
 *       operator precedence in Java
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.TreeMap;

public class EvaluateDeluxe {

    // result of applying binary operator op to two operands val1 and val2
    public static double eval(String op, double val1, double val2) {
        if (op.equals("+")) return val1 + val2;
        if (op.equals("-")) return val1 - val2;
        if (op.equals("/")) return val1 / val2;
        if (op.equals("*")) return val1 * val2;
        throw new RuntimeException("Invalid operator");
    }

    public static void main(String[] args) {

        // precedence order of operators
        TreeMap<String, Integer> precedence = new TreeMap<String, Integer>();
        precedence.put("(", 0);   // for convenience with algorithm
        precedence.put(")", 0);
        precedence.put("+", 1);   // + and - have lower precedence than * and /
        precedence.put("-", 1);
        precedence.put("*", 2);
        precedence.put("/", 2);

        Stack<String> ops = new Stack<String>();
        Stack<Double> vals = new Stack<Double>();

        while (!StdIn.isEmpty()) {

            // read in next token (operator or value)
            String s = StdIn.readString();

            // token is a value
            if (!precedence.containsKey(s)) {
                vals.push(Double.parseDouble(s));
                continue;
            }

            // token is an operator
            while (true) {

                // the last condition ensures that the operator with higher precedence is evaluated first
                if (ops.isEmpty() || s.equals("(") || (precedence.get(s) > precedence.get(ops.peek()))) {
                    ops.push(s);
                    break;
                }

                // evaluate expression
                String op = ops.pop();

                // but ignore left parentheses
                if (op.equals("(")) {
                    assert s.equals(")");
                    break;
                }

                // evaluate operator and two operands and push result onto value stack
                else {
                    double val2 = vals.pop();
                    double val1 = vals.pop();
                    vals.push(eval(op, val1, val2));
                }
            }
        }

        // finished parsing string - evaluate operator and operands remaining on two stacks
        while (!ops.isEmpty()) {
            String op = ops.pop();
            double val2 = vals.pop();
            double val1 = vals.pop();
            vals.push(eval(op, val1, val2));
        }

        // last value on stack is value of expression
        StdOut.println(vals.pop());
        assert vals.isEmpty();
        assert ops.isEmpty();
    }
}
