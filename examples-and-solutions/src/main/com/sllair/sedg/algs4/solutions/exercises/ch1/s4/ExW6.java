package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

public class ExW6 {

    public static String getMajority(String[] a) {
        if (a.length < 1) {
            return null;
        } else if (a.length == 1) {
            return a[0];
        }

        int majorityCandidate = getMajorityCandidate(a);

        return isMajority(majorityCandidate, a) ? a[majorityCandidate] : null;
    }

    private static int getMajorityCandidate(String[] a) {
        int majorityCandidate = 0;
        int count = 1;
        for (int i = 1; i < a.length; i++) {
            if (a[i].equals(a[majorityCandidate])) {
                ++count;
            } else if (count > 0) {
                --count;
            } else {
                majorityCandidate = i;
            }
        }
        return majorityCandidate;
    }

    private static boolean isMajority(int majorityCandidate, String[] a) {
        int count = 1;
        for (int i = 0; i < a.length; i++) {
            if (i != majorityCandidate && a[i].equals(a[majorityCandidate])) {
                ++count;
            }
        }

        return count > a.length / 2;
    }

}
