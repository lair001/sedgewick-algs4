package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.ExW9;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExW9Test {

    @Test
    public void testFindDuplicateWithFirstLeetCodeTestCase() {
        int[] a = new int[]{1, 3, 4, 2, 2};
        Assertions.assertEquals(2, ExW9.findDuplicate(a));
    }

}
