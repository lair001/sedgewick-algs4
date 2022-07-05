package com.sllair.sedg.algs4.examples.ch1.s3;

import com.sllair.sedgewick.introcs.Histogram;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class MM1Queue {

    public static void main(String[] args) {
        double lambda = Double.parseDouble(args[0]);  // arrival rate
        double mu = Double.parseDouble(args[1]);  // service rate

        Queue<Double> q = new Queue<Double>();            // arrival times of customers
        double nextArrival = StdRandom.exp(lambda);     // time of next arrival
        double nextDeparture = Double.POSITIVE_INFINITY;  // time of next departure

        // histogram object
        Histogram hist = new Histogram(60);

        // simulate an M/M/1 queue
        while (true) {

            // it's an arrival
            if (nextArrival <= nextDeparture) {
                if (q.isEmpty()) nextDeparture = nextArrival + StdRandom.exp(mu);
                q.enqueue(nextArrival);
                nextArrival += StdRandom.exp(lambda);
            }

            // it's a departure
            else {
                double wait = nextDeparture - q.dequeue();
                StdOut.printf("Wait = %6.2f, queue size = %d\n", wait, q.size());
                hist.addDataPoint(Math.min(60, (int) (Math.round(wait))));
                hist.draw();
                if (q.isEmpty()) nextDeparture = Double.POSITIVE_INFINITY;
                else nextDeparture += StdRandom.exp(mu);

            }
        }

    }

}

