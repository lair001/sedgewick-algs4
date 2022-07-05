package com.sllair.sedg.algs4.solutions.quizzes.part1.week2.elementary_sorts;

import com.sllair.sedg.algs4.utils.IntegerUtils;
import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class P2Test {
    @Test
    public void testIsPermutationWithRandomTrials() {
        for (int i = 0; i < 10000; ++i) {
            int n = StdRandom.uniform(1, 1001);

            Integer[] a = new Integer[n];
            Integer[] b = new Integer[n];
            Integer[] c = new Integer[n];

            HashSet<Integer> seen = new HashSet<>();
            int j = 0;
            while (seen.size() < n) {
                Integer rand = StdRandom.uniform(-Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
                if (!seen.contains(rand)) {
                    a[j] = rand;
                    b[j] = rand;
                    c[j] = rand;
                    seen.add(rand);
                    ++j;
                }
            }

            /*
             * If we don't do this, if a[i] is in b[] it will also be at index i.
             */
            Knuth.shuffle(b);

            boolean actual = P2.isPermutation(a, b);
            Assertions.assertTrue(actual, getFailureMessage(true, actual, a, b));

            int numberOfElementsToChange = StdRandom.uniform(1, n + 1);
            for (int h = 0; h < numberOfElementsToChange; h++) {
                int k = StdRandom.uniform(h, n);
                ;
                int rand = StdRandom.uniform(-Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
                while (seen.contains(rand)) {
                    rand = StdRandom.uniform(-Integer.MAX_VALUE / 2, Integer.MAX_VALUE / 2);
                }
                c[k] = c[h];
                c[h] = rand;
                seen.add(rand);
            }
            Knuth.shuffle(c);

            actual = P2.isPermutation(a, c);
            Assertions.assertFalse(actual, getFailureMessage(false, actual, a, b));
        }
    }

    private static String getFailureMessage(boolean expected, boolean actual, Integer[] a, Integer[] b) {
        return String.format("expected: %b%nactual: %b%na: %s%nb: %s",
                expected, actual, IntegerUtils.toString(a), IntegerUtils.toString(b)
        );
    }
}
