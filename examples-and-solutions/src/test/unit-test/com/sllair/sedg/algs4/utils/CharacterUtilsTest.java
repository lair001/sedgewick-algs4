package com.sllair.sedg.algs4.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CharacterUtilsTest {

    public final static String NOT_A_LTR_ERR_MSG_FMT = "%c is not a letter";

    @Test
    public void testIsLowerCaseLetter() {
        for (char c = 'a'; c <= 'z'; c++) {
            Assertions.assertTrue(CharacterUtils.isLowerCaseLetter(c));
        }

        for (char c = Character.MIN_VALUE; c < 'a'; c++) {
            Assertions.assertFalse(CharacterUtils.isLowerCaseLetter(c));
        }

        for (char c = '{'; c <= '\u007F'; c++) {
            Assertions.assertFalse(CharacterUtils.isLowerCaseLetter(c));
        }
    }

    @Test
    public void testIsUpperCaseLetter() {
        for (char c = 'A'; c <= 'Z'; c++) {
            Assertions.assertTrue(CharacterUtils.isUpperCaseLetter(c));
        }

        for (char c = Character.MIN_VALUE; c < 'A'; c++) {
            Assertions.assertFalse(CharacterUtils.isUpperCaseLetter(c));
        }

        for (char c = '['; c <= '\u007F'; c++) {
            Assertions.assertFalse(CharacterUtils.isUpperCaseLetter(c));
        }
    }

    @Test
    public void testIsLetter() {
        for (char c = 'A'; c <= 'Z'; c++) {
            Assertions.assertTrue(CharacterUtils.isLetter(c));
        }

        for (char c = 'a'; c <= 'z'; c++) {
            Assertions.assertTrue(CharacterUtils.isLetter(c));
        }

        for (char c = Character.MIN_VALUE; c < 'A'; c++) {
            Assertions.assertFalse(CharacterUtils.isLetter(c));
        }

        for (char c = '['; c <= '`'; c++) {
            Assertions.assertFalse(CharacterUtils.isLetter(c));
        }

        for (char c = '{'; c <= '\u007F'; c++) {
            Assertions.assertFalse(CharacterUtils.isLetter(c));
        }
    }

    @Test
    public void testToUpperCase() {
        for (char c = 'A'; c <= 'Z'; c++) {
            Assertions.assertEquals(c, CharacterUtils.toUpperCase(c));
        }

        for (char c = 'a'; c <= 'z'; c++) {
            Assertions.assertEquals(Character.toUpperCase(c), CharacterUtils.toUpperCase(c));
        }

        for (char c = Character.MIN_VALUE; c < 'A'; c++) {
            final char finalC = c; // ugh!
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> CharacterUtils.toUpperCase(finalC),
                    String.format(NOT_A_LTR_ERR_MSG_FMT, finalC)
            );
        }

        for (char c = '['; c <= '`'; c++) {
            final char finalC = c; // ugh!
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> CharacterUtils.toUpperCase(finalC),
                    String.format(NOT_A_LTR_ERR_MSG_FMT, finalC)
            );
        }

        for (char c = '{'; c <= '\u007F'; c++) {
            final char finalC = c; // ugh!
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> CharacterUtils.toUpperCase(finalC),
                    String.format(NOT_A_LTR_ERR_MSG_FMT, finalC)
            );
        }
    }

    @Test
    public void testToLowerCase() {
        for (char c = 'A'; c <= 'Z'; c++) {
            Assertions.assertEquals(Character.toLowerCase(c), CharacterUtils.toLowerCase(c));
        }

        for (char c = 'a'; c <= 'z'; c++) {
            Assertions.assertEquals(c, CharacterUtils.toLowerCase(c));
        }

        for (char c = Character.MIN_VALUE; c < 'A'; c++) {
            final char finalC = c; // ugh!
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> CharacterUtils.toLowerCase(finalC),
                    String.format(NOT_A_LTR_ERR_MSG_FMT, finalC)
            );
        }

        for (char c = '['; c <= '`'; c++) {
            final char finalC = c; // ugh!
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> CharacterUtils.toLowerCase(finalC),
                    String.format(NOT_A_LTR_ERR_MSG_FMT, finalC)
            );
        }

        for (char c = '{'; c <= '\u007F'; c++) {
            final char finalC = c; // ugh!
            Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> CharacterUtils.toLowerCase(finalC),
                    String.format(NOT_A_LTR_ERR_MSG_FMT, finalC)
            );
        }
    }

}
