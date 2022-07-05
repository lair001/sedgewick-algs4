package com.sllair.sedg.algs4.solutions.exercises.ch1.s3.solutions;

import com.sllair.sedg.algs4.solutions.exercises.ch1.s3.ExW10;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExW10Test {

    @Test
    public void isPalindrome() {
        Assertions.assertTrue(ExW10.isPalindrome("I am Mai!"));
        Assertions.assertTrue(ExW10.isPalindrome("A man, a plan, a canal - Panama!"));
        Assertions.assertTrue(ExW10.isPalindrome("tattarrattat"));
        Assertions.assertFalse(ExW10.isPalindrome("The rain in Spain falls mainly the plain!"));
    }

}
