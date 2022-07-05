package com.sllair.sedg.algs4.utils;

public class CharacterUtils {

    public static boolean isLowerCaseLetter(char c) {
        return 'a' <= c && 'z' >= c;
    }

    public static boolean isUpperCaseLetter(char c) {
        return 'A' <= c && 'Z' >= c;
    }

    public static boolean isLetter(char c) {
        return isLowerCaseLetter(c) || isUpperCaseLetter(c);
    }

    public static char toUpperCase(char c) {
        if (isUpperCaseLetter(c)) {
            return c;
        } else if (isLowerCaseLetter(c)) {
            /*
             * Look at an ASCII table. 'A' = 65, 'a' = 97, and
             * ' ' = 32.  So 'a' - 'A' = 97 - 65 = 32 = ' '
             * Solve for 'A': 'A' = 'a' - ' '
             * The lower and upper case letters are both contiguous
             * blocks so you can substitute any uppercase and
             * lowercase pair into these formulas.
             */
            return (char) (c - ' ');
        } else {
            throw new IllegalArgumentException(
                    String.format("%c is not a letter", c)
            );
        }
    }

    public static char toLowerCase(char c) {
        if (isLowerCaseLetter(c)) {
            return c;
        } else if (isUpperCaseLetter(c)) {
            return (char) (c + ' ');
        } else {
            throw new IllegalArgumentException(
                    String.format("%c is not a letter", c)
            );
        }
    }

}
