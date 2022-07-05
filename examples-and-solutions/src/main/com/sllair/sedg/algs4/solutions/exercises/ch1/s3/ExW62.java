package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/*
 * This problem gives no clear direction on what to do if
 * the price never exceeds prices[i] by prices[n-1].  In a
 * popular inverse of this problem, see
 * https://www.geeksforgeeks.org/the-stock-span-problem/
 * , the price just out of bounds at prices[-1] is assumed to
 * be lower than all prices in bounds. Therefore, I will assume
 * that the price just out of bounds at prices[n] is higher than
 * all prices in bounds.
 */
public class ExW62 {

    public static int[] computeDays(int[] prices) {
        int[] days = new int[prices.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < prices.length; i++) {
            if (!stack.isEmpty()) {
                while (prices[i] > prices[stack.peek()]) {
                    int past = stack.pop();
                    days[past] = i - past;
                }
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int past = stack.pop();
            days[past] = prices.length - past;
        }

        return days;
    }

    private static <Item> void println(int[] items) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int item : items) {
            sb.append(item);
            sb.append(' ');
        }
        sb.append(']');

        StdOut.println(sb);
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW62.class);

        int n = in.readInt();

        int[] prices = new int[n];

        for (int i = 0; i < n; i++) {
            prices[i] = in.readInt();
        }

        StdOut.println("Prices");
        println(prices);
        StdOut.println("Days");
        println(computeDays(prices));
    }

}
