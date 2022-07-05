package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.Ex8;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class Ex8Test {

    @Test
    public void testGetNumOfEqualPairs() {
        Map<Integer, Integer> counts = new HashMap<>();

        int[] a = new int[1000];

        for (int i = 0; i < a.length; i++) {
            int rand = StdRandom.uniform(a.length);
            a[i] = rand;
            counts.merge(rand, 1, Integer::sum);
        }

        int expectedPairs = 0;
        for (int count : counts.values()) {
            expectedPairs += count - 1;
        }

        Assertions.assertEquals(expectedPairs, Ex8.getNumOfEqualPairs(a));
    }

}
