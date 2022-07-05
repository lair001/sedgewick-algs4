package com.sllair.sedg.algs4.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    @Test
    public void testIsInteger() {
        StringBuilder sb = new StringBuilder();
        for (char c = '0'; c <= '9'; c++) {
            Assertions.assertTrue(StringUtils.isInteger(String.valueOf(c)));
            sb.append('0');
            sb.append(c);
            Assertions.assertTrue(StringUtils.isInteger(sb.toString()));
            sb.setLength(0);
            sb.append('-');
            sb.append(c);
            Assertions.assertTrue(StringUtils.isInteger(sb.toString()));
            sb.setLength(0);
        }

        for (char c = Character.MIN_VALUE; c < '0'; c++) {
            Assertions.assertFalse(StringUtils.isInteger(String.valueOf(c)));
        }

        for (char c = ':'; c < '\u007F'; c++) {
            Assertions.assertFalse(StringUtils.isInteger(String.valueOf(c)));
        }

        Assertions.assertTrue(StringUtils.isInteger(String.valueOf(Integer.MIN_VALUE)));
        Assertions.assertTrue(StringUtils.isInteger(String.valueOf(Integer.MAX_VALUE)));
    }

}
