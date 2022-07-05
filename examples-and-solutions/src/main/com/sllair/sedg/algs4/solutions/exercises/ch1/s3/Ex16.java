package com.sllair.sedg.algs4.solutions.exercises.ch1.s3;

import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/*
 * Taking the date format to be a string of form
 * mm/dd/yyyy
 */
public class Ex16 {

    public static Date[] readDates(In in) {
        Queue<Date> queue = new Queue<>();

        while (!in.isEmpty()) {
            queue.enqueue(new Date(in.readString()));
        }

        int numDates = queue.size();
        Date[] result = new Date[numDates];

        for (int i = 0; i < numDates; i++) {
            result[i] = queue.dequeue();
        }

        return result;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, Ex16.class);
        for (Date date : readDates(in)) {
            StdOut.println(date);
        }
    }

}
