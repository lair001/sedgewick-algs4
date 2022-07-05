package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

public class ExW9 {
    public static int findDuplicate(int[] nums) {
        /*
         * My tests aren't extensive since this problem is on LeetCode:
         * https://leetcode.com/problems/find-the-duplicate-number/
         * You can find my successful submission here:
         * https://leetcode.com/submissions/detail/747152775/
         *
         * You interpret the array as a linked list. A duplicate starts a cycle.
         * Therefore, use Floyd's algorithm for detecting the start of a cycle:
         * https://en.wikipedia.org/wiki/Cycle_detection#Floyd's_tortoise_and_hare
         *
         * Since there are N + 1 elements and only N possible values for those
         * elements, we are guaranteed to have a duplicate.
         *
         * NeetCode made a nice video about this problem:
         * https://www.youtube.com/watch?v=wjYnzkAhcNk
         */

        int slow = nums[0];
        int fast = nums[slow];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
