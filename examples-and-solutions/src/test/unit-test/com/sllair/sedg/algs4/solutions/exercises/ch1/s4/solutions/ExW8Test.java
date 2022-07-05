package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.ExW8;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ExW8Test {

    @Test
    public void testHasDuplicateForSingleDuplicate() {
        int[] a = new int[]{1, 3, 4, 2, 2};
        Assertions.assertTrue(ExW8.hasDuplicate(a));
    }

    @Test
    public void testHasDuplicateForTwoDuplicates() {
        int[] a = new int[]{4, 4, 4, 2, 2};
        Assertions.assertTrue(ExW8.hasDuplicate(a));
    }

    @Test
    public void testHasDuplicateForSetOfDistinctIntegers() {
        int[] a = new int[]{1, 3, 4, 2, 5};
        Assertions.assertFalse(ExW8.hasDuplicate(a));
    }


    @Test
    public void testHasDuplicateWithRandomTrials() {
        for (int i = 0; i < 10000; i++) {
            int n = StdRandom.uniform(1000) + 1;
            int[] array = new int[n];
            Map<Integer, Integer> counts = new HashMap<>();

            for (int j = 0; j < n; j++) {
                int randomNum = StdRandom.uniform(1, n + 1);
                array[j] = randomNum;
                counts.merge(randomNum, 1, Integer::sum);
            }
            Assertions.assertEquals(hasDuplicate(counts), ExW8.hasDuplicate(array), getFailureMessage(array));
        }
    }

    private boolean hasDuplicate(Map<Integer, Integer> counts) {
        for (Integer count : counts.values()) {
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    private static String getFailureMessage(int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) {
                sb.append(',');
            }
            sb.append(' ');
        }
        sb.append('}');
        return sb.toString();
    }

}
