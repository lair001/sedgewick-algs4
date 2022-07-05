package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.Ex12;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ex12Test {

    @Test
    public void testNumsInBoth() {
        int[] a = new int[]{1, 3, 9, 12, 44, 99, 321, 3113, 4331};
        int[] b = new int[]{1, 5, 7, 55, 99, 104, 321, 555, 3113};

        Assertions.assertEquals("1 99 321 3113 ", Ex12.numsInBoth(a, b).toString());
    }

}
