package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

public class ExW8 {
    public static boolean hasDuplicate(int[] a) {

        /*
         * Essentially, we're treating each array value as a link in a linked list making
         * a minus 1 adjustment to account for the fact that the indices range from
         * [0, N-1] while the values range [1, N]. When we visit a element on the array,
         * we check what its linked to.  If the value of the linked element is negative,
         * we know that something else is also linked to that element indicating a
         * duplicate. Otherwise, we set the value of the linked element to its corresponding
         * negative value to mark the fact that something is linked to it while still
         * being able to resolve its link if we visit it later.
         *
         * So simple yet it took me a few hours to figure it out.
         */
        boolean result = false;
        for (int j = 0; j < a.length; j++) {
            int indexForValue = Math.abs(a[j]) - 1;
            if (a[indexForValue] < 0) {
                result = true;
                break;
            }
            a[indexForValue] = -a[indexForValue];
        }

        // Bonus points for restoring the array to its original condition
        for (int j = 0; j < a.length; j++) {
            if (a[j] < 0) {
                a[j] = -a[j];
            }
        }

        return result;
    }

}
