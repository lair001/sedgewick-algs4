package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.utils.CharacterUtils;
import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class ExW12 {

    public static void printAnagrams(String[] a) {
        Arrays.sort(a, (s1, s2) -> score(s1) - score(s2));

        for (int i = 0; i < a.length - 1; i++) {
            int currScore = score(a[i]);
            int j = i;
            /*
             * The number of anagrams for a given set of letters is bounded
             * by a small constant, so the following loops don't contribute to the
             * time complexity.
             */
            while (j < a.length - 2 && score(a[j + 1]) == currScore) {
                ++j;
            }

            for (int x = i; x < j; x++) {
                for (int y = x + 1; y <= j; y++) {
                    StdOut.println(String.format("%s %s", a[x], a[y]));
                }
            }
            i = j;
        }
    }

    /*
     * Since the maximum word length is taken to be a small
     * constant (20), this method does not increase the
     * time complexity of the algorithm.
     */
    private static int score(String s) {
        int score = 0;
        for (int i = 0; i < s.length(); i++) {
            score += CharacterUtils.toLowerCase(s.charAt(i));
        }
        return score;
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, ExW12.class);
        printAnagrams(in.readAllStrings());
    }


}
