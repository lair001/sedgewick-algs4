package com.sllair.sedg.algs4.solutions.exercises.ch2.s2;

import com.sllair.sedg.algs4.testutils.unit.SortTestUtils;
import com.sllair.sedg.algs4.utils.IntegerUtils;
import edu.princeton.cs.algs4.Knuth;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ex11Test {

    @Test
    public void testEnhancedMergeSort() {
        Integer[] a = new Integer[100];
        for (int i = 0; i < 100; ++i) {
            a[i] = i;
        }
        Knuth.shuffle(a);
        Ex11.EnhancedMerge.sort(a);
        Assertions.assertTrue(SortTestUtils.isSorted(a), IntegerUtils.toString(a));
    }

}
