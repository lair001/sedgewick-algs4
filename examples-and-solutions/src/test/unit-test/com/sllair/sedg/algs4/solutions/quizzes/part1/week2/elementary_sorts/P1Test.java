package com.sllair.sedg.algs4.solutions.quizzes.part1.week2.elementary_sorts;

import com.sllair.sedg.algs4.utils.Point2DUtils;
import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class P1Test {
    @Test
    public void testNumOfPointsInBothWithRandomTrials() {
        for (int i = 0; i < 10000; ++i) {
            int n = StdRandom.uniform(1, 1001);

            Point2D[] a = new Point2D[n];
            Point2D[] b = new Point2D[n];

            int expectedCount = 0;
            HashSet<Point2D> seenA = new HashSet<>();
            HashSet<Point2D> seenB = new HashSet<>();
            int j = 0;
            while (seenA.size() < n) {
                Point2D randA = new Point2D(StdRandom.uniform(), StdRandom.uniform());
                if (!seenA.contains(randA)) {
                    a[j] = randA;
                    seenA.add(randA);
                    if (!seenB.contains(randA) && StdRandom.uniform() > StdRandom.uniform()) {
                        b[j] = new Point2D(a[j].x(), a[j].y());
                        seenB.add(randA);
                        ++expectedCount;
                    } else {
                        Point2D randB = new Point2D(StdRandom.uniform(), StdRandom.uniform());
                        while (seenB.contains(randB)) {
                            randB = new Point2D(StdRandom.uniform(), StdRandom.uniform());
                        }
                        b[j] = randB;
                        seenB.add(randB);
                        if (seenA.contains(randB)) {
                            ++expectedCount;
                        }
                    }
                    ++j;
                }
            }

            /*
             * If we don't do this, if a[i] is in b[] it will also be at index i.
             */
            Knuth.shuffle(a);
            Knuth.shuffle(b);

            int actualCount = P1.numOfPointsInBoth(a, b);
            Assertions.assertEquals(expectedCount, actualCount, getFailureMessage(expectedCount, actualCount, a, b));
        }
    }

    private static String getFailureMessage(int expectCount, int actualCount, Point2D[] a, Point2D[] b) {
        return String.format("expected count: %d%nactual count: %d%na: %s%nb: %s",
                expectCount, actualCount, Point2DUtils.toString(a), Point2DUtils.toString(b)
        );
    }

}
