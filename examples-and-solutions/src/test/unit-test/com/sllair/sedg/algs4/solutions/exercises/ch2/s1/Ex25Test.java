package com.sllair.sedg.algs4.solutions.exercises.ch2.s1;

import com.sllair.sedg.algs4.testutils.unit.SortTestUtils;
import com.sllair.sedg.algs4.utils.IntegerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ex25Test {

    @Test
    public void testFasterInsertionSort() {
        Integer[] a = new Integer[]{3, 1, 2, 5, 101, 99, 6, 4, -1};
        Ex25.FasterInsertion.sort(a);
        Assertions.assertTrue(SortTestUtils.isSorted(a), IntegerUtils.toString(a));
    }

}
