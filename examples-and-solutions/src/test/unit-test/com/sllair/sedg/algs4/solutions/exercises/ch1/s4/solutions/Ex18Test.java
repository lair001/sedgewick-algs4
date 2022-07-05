package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.Ex18;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Ex18Test {

    @Test
    public void testMinimumJustLeftOfTheMidpoint() {
        int[] a = new int[]{20, 19, 14, 3, 18, 21, 22, 24, 27};
        Assertions.assertEquals(3, Ex18.findALocalMinimum(a));
    }

    @Test
    public void testMinimumJustRightOfTheMidpoint() {
        int[] a = new int[]{20, 19, 14, 12, 11, 3, 22, 24, 27};
        Assertions.assertEquals(5, Ex18.findALocalMinimum(a));
    }

}
