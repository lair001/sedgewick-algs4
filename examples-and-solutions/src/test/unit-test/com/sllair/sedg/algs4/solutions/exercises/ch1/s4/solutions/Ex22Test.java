package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.Ex22;
import com.sllair.sedg.algs4.testutils.unit.SearchTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Ex22Test {

    private int[] size7;
    private int[] size1;
    private int[] size2;

    @BeforeEach
    public void beforeEach() {
        this.size7 = new int[]{5, 8, 12, 17, 19, 23, 88};
        this.size1 = new int[]{1};
        this.size2 = new int[]{70, 99};
    }

    private void executeFibonacciSearchTests(Ex22.IFibonacciSearch fibonacciSearch) {

        // testFibonacciSearchWhenAllElementsInArrayAreLessThanKey
        Assertions.assertEquals(-1, fibonacciSearch.rank(Integer.MAX_VALUE, this.size7));

        // testFibonacciSearchWhenAllElementsInArrayAreGreaterThanKey
        Assertions.assertEquals(-1, fibonacciSearch.rank(Integer.MIN_VALUE, this.size7));

        // testFibonacciSearchWhenKeyEqualsFirstElement
        Assertions.assertEquals(0, fibonacciSearch.rank(this.size7[0], this.size7));

        // testFibonacciSearchWhenKeyEqualsLastElement
        Assertions.assertEquals(size7.length - 1, fibonacciSearch.rank(this.size7[size7.length - 1], this.size7));

        // testFibonacciSearchWhenKeyEqualsMiddleElement
        Assertions.assertEquals(size7.length / 2, fibonacciSearch.rank(this.size7[size7.length / 2], this.size7));

        // testFibonacciSearchWhenKeyIsInSize1Array
        Assertions.assertEquals(0, fibonacciSearch.rank(this.size1[0], this.size1));

        // testFibonacciSearchWhenKeyIsLessThanOnlyValueInSize1Array
        Assertions.assertEquals(-1, fibonacciSearch.rank(this.size1[0] - 1, this.size1));

        // testFibonacciSearchWhenKeyIsGreaterThanOnlyValueInSize1Array
        Assertions.assertEquals(-1, fibonacciSearch.rank(this.size1[0] + 1, this.size1));

        // testFibonacciSearchWhenKeyIsFirstElementOfSize2Array
        Assertions.assertEquals(0, fibonacciSearch.rank(this.size2[0], this.size2));

        // testFibonacciSearchWhenKeyIsSecondElementOfSize2Array
        Assertions.assertEquals(1, fibonacciSearch.rank(this.size2[1], this.size2));

        // testFibonacciSearchWhenKeyIsLessThanBothElementsOfSize2Array() {
        Assertions.assertEquals(-1, fibonacciSearch.rank(this.size2[0] - 1, this.size2));

        // testFibonacciSearchWhenKeyIsGreaterThanBothElementsOfSize2Array() {
        Assertions.assertEquals(-1, fibonacciSearch.rank(this.size2[1] + 1, this.size2));

        // testFibonacciSearchWhenKeyIsBetweenTheElementsOfSize2Array
        Assertions.assertEquals(-1, fibonacciSearch.rank((this.size2[0] + this.size2[1]) / 2, this.size2));

        // Do some random trials
        SearchTestUtils.executeRandomTrials(fibonacciSearch);
    }

    @Test
    public void testFibonacciSearch() {
        executeFibonacciSearchTests(new Ex22.FibonacciSearch());
    }

    @Test
    public void testKnuthFibonacciSearch() {
        executeFibonacciSearchTests(new Ex22.KnuthFibonacciSearch());
    }

    @Test
    public void testFergusonFibonacciSearch() {
        executeFibonacciSearchTests(new Ex22.FergusonFibonacciSearch());
    }

    @Test
    public void testNotesForMScFibonacciSearch() {
        executeFibonacciSearchTests(new Ex22.NotesForMScFibonacciSearch());
    }
}

