package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.ExW6;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ExW6Test {

    @Test
    public void testGetMajorityWhenThereIsAMajority() {
        String[] a = new String[]{"foo", "bar", "bar", "foo", "baz", "foo", "foo"};
        Assertions.assertEquals("foo", ExW6.getMajority(a));
    }

    @Test
    public void testGetMajorityWhenThereIsNoMajority() {
        String[] a = new String[]{"foo", "bar", "bar", "foo", "baz", "foo", "foo", "bar"};
        Assertions.assertNull(ExW6.getMajority(a));
    }

    @Test
    public void testGetMajorityWithRandomTrials() {
        /*
         * Must be careful here. Need to devise the trials in a
         * way where duplicates are inevitable for non-tiny n
         * and majorities plausible.
         */
        String[] pool = new String[]{"foo", "bar", "baz"};
        for (int i = 0; i < 10000; i++) {
            int n = StdRandom.uniform(1, 11);
            String[] array = new String[n];
            Map<String, Integer> counts = new HashMap<>();
            for (int j = 0; j < n; j++) {
                String randomStr = pool[StdRandom.uniform(pool.length)];
                array[j] = randomStr;
                counts.merge(randomStr, 1, Integer::sum);
            }
            Assertions.assertEquals(getMajority(counts, array), ExW6.getMajority(array));
        }
    }

    private String getMajority(Map<String, Integer> counts, String[] a) {
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > a.length / 2) {
                return entry.getKey();
            }
        }
        return null;
    }

}
