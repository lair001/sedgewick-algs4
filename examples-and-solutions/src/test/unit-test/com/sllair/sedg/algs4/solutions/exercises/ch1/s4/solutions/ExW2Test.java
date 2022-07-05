package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.ExW2;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ExW2Test {

    private int[] size10;

    @BeforeEach
    public void beforeEach() {
        this.size10 = new int[]{12, 23, 34, 45, 55, 68, 77, 88, 98, 108};
    }

    @Test
    public void testFloorAndCeilingWhenXIsNotInArray() {
        int[] result = ExW2.floorAndCeiling(50, this.size10);
        Assertions.assertEquals(45, result[0]);
        Assertions.assertEquals(55, result[1]);
    }

    @Test
    public void testFloorAndCeilingWhenXIsInMiddleOfArray() {
        int[] result = ExW2.floorAndCeiling(this.size10[5], this.size10);
        Assertions.assertEquals(this.size10[5], result[0]);
        Assertions.assertEquals(this.size10[5], result[1]);
    }

    @Test
    public void testFloorAndCeilingWhenXIsAtBeginningOfArray() {
        int[] result = ExW2.floorAndCeiling(this.size10[0], this.size10);
        Assertions.assertEquals(this.size10[0], result[0]);
        Assertions.assertEquals(this.size10[0], result[1]);
    }

    @Test
    public void testFloorAndCeilingWhenXIsAtEndOfArray() {
        int[] result = ExW2.floorAndCeiling(this.size10[this.size10.length - 1], this.size10);
        Assertions.assertEquals(this.size10[this.size10.length - 1], result[0]);
        Assertions.assertEquals(this.size10[this.size10.length - 1], result[1]);
    }

    @Test
    public void testFloorAndCeilingWhenXIsSmallerThanAllElementsOfArray() {
        int[] result = ExW2.floorAndCeiling(this.size10[0] - 1, this.size10);
        Assertions.assertEquals(Integer.MAX_VALUE, result[0]);
        Assertions.assertEquals(this.size10[0], result[1]);
    }

    @Test
    public void testFloorAndCeilingWhenXLargerThanAllElementsOfArray() {
        int[] result = ExW2.floorAndCeiling(this.size10[this.size10.length - 1] + 1, this.size10);
        Assertions.assertEquals(this.size10[this.size10.length - 1], result[0]);
        Assertions.assertEquals(Integer.MIN_VALUE, result[1]);
    }

    @Test
    public void testFloorAndCeilingWithRandomTrials() {
        for (int i = 0; i < 10000; i++) {
            int n = StdRandom.uniform(1000) + 1;
            int[] array = new int[n];

            for (int j = 0; j < n; j++) {
                array[j] = StdRandom.uniform(-1000001 * n, 1000000 * n);
            }
            Arrays.sort(array);
            int x = StdRandom.uniform(-1000001 * n, 1000000 * n);
            int[] result = ExW2.floorAndCeiling(x, array);

            if (result[0] == Integer.MAX_VALUE) {
                Assertions.assertTrue(x < array[0]);
            } else {
                Assertions.assertTrue(result[0] <= x, String.format("floor: %d x: %d", result[0], x));
            }

            if (result[1] == Integer.MIN_VALUE) {
                Assertions.assertTrue(x > array[array.length - 1]);
            } else {
                Assertions.assertTrue(result[1] >= x, String.format("ceiling: %d x: %d", result[0], x));
            }
        }
    }
}
