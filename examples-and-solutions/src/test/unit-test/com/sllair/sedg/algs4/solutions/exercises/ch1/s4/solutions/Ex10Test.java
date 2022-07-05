package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.Ex10;
import com.sllair.sedg.algs4.testutils.unit.SearchTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ex10Test {
    @Test
    public void testBinaryMinIndexSearch() {
        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
                23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
        Assertions.assertEquals(31, new Ex10.BinarySearchMinIndex().rank(a[31], a));
    }

    @Test
    public void testBinarySearchWithDuplicatesInMiddle() {
        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 18, 18, 18, 18, 18, 18, 18, 18,
                18, 18, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
        Assertions.assertEquals(15, new Ex10.BinarySearchMinIndex().rank(18, a));
    }

    @Test
    public void testBinaryMinIndexSearchWithDupicatesAtBeginning() {
        int[] a = new int[]{5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
                23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43};
        Assertions.assertEquals(0, new Ex10.BinarySearchMinIndex().rank(5, a));
    }

    @Test
    public void testBinaryMinIndexSearchWithDuplicatesAtEnd() {
        int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22,
                23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 43, 43, 43, 43, 43, 43, 43, 43, 43};
        Assertions.assertEquals(35, new Ex10.BinarySearchMinIndex().rank(43, a));
    }

    @Test
    public void testBinaryMinIndexSearchWithRandomTrials() {
        SearchTestUtils.executeRandomTrials(new Ex10.BinarySearchMinIndex());
    }
}
