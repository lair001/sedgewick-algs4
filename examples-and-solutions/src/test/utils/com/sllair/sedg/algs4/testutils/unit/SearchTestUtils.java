package com.sllair.sedg.algs4.testutils.unit;

import com.sllair.sedg.algs4.interfaces.IIntSearch;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SearchTestUtils {

    public static void executeRandomTrials(IIntSearch searchEngine) {
        for (int i = 0; i < 10000; i++) {
            int n = StdRandom.uniform(1000) + 1;
            int[] array = new int[n];
            Set<Integer> seen = new HashSet<>();
            /*
             * The fibonacci search algorithms work just fine with
             * duplicates, but I want to test whether they are returning
             * the correct indices.
             */
            while (seen.size() < n) {
                int randomNum = StdRandom.uniform(-1000001 * n, 1000000 * n);
                if (!seen.contains(randomNum)) {
                    array[seen.size()] = randomNum;
                    seen.add(randomNum);
                }
            }
            Arrays.sort(array);
            int randomIndex = StdRandom.uniform(n);
            Assertions.assertEquals(randomIndex, searchEngine.rank(array[randomIndex], array), getFailureMessage(randomIndex, array));
            Assertions.assertEquals(-1, searchEngine.rank(array[0] - 1, array));
            Assertions.assertEquals(-1, searchEngine.rank(array[n - 1] + 1, array));
        }
    }

    private static String getFailureMessage(int index, int[] a) {
        StringBuilder sb = new StringBuilder();
        sb.append("index: ");
        sb.append(index);
        sb.append(" length: ");
        sb.append(a.length);
        sb.append(" key: ");
        sb.append(a[index]);
        sb.append("\n{ ");
        for (int i = 0; i < a.length; i++) {
            sb.append(a[i]);
            if (i < a.length - 1) {
                sb.append(',');
            }
            sb.append(' ');
        }
        sb.append('}');
        return sb.toString();
    }


}
