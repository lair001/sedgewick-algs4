package com.sllair.sedg.algs4.solutions.exercises.ch1.s4.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s4.ExW17;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExW17Test {

    @Test
    public void testIsInWithRandomTrials() {
        for (int i = 0; i < 10000; i++) {
            int n = StdRandom.uniform(1, 51);
            int[][] array = new int[n][];

            for (int row = 0; row < n; row++) {
                array[row] = new int[n];
                for (int col = 0; col < n; col++) {
                    int randomNum = StdRandom.uniform(1, 11);
                    if (row > 0 && randomNum <= array[row - 1][col]) {
                        randomNum += array[row - 1][col] - randomNum + StdRandom.uniform(1, 11);
                    }
                    if (col > 0 && randomNum <= array[row][col - 1]) {
                        randomNum += array[row][col - 1] - randomNum + StdRandom.uniform(1, 11);
                    }
                    array[row][col] = randomNum;
                }

            }
            int numInArray = array[StdRandom.uniform(n)][StdRandom.uniform(n)];
            Assertions.assertTrue(ExW17.isIn(numInArray, array), getFailureMessage(numInArray, array));
            int smallNumOutOfArray = array[0][0] - StdRandom.uniform(1, 11);
            Assertions.assertFalse(ExW17.isIn(smallNumOutOfArray, array), getFailureMessage(numInArray, array));
            int largeNumOutOfArray = array[n - 1][n - 1] + StdRandom.uniform(1, 11);
            Assertions.assertFalse(ExW17.isIn(largeNumOutOfArray, array), getFailureMessage(numInArray, array));
        }
    }

    private static String getFailureMessage(int x, int[][] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("x: ");
        sb.append(x);
        for (int row = 0; row < a.length; row++) {
            sb.append("\n{ ");
            for (int col = 0; col < a.length; col++) {
                sb.append(a[row][col]);
                if (col < a.length - 1) {
                    sb.append(',');
                }
                sb.append(' ');
            }
            sb.append('}');
        }
        return sb.toString();
    }

}
