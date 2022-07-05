package com.sllair.sedg.algs4.solutions.exercises.ch1.s4;

import com.sllair.sedg.algs4.interfaces.IIntSearch;
import com.sllair.sedg.algs4.utils.InFactory;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Ex22 {

    public interface IFibonacciSearch extends IIntSearch {
    }

    public static class FibonacciSearch implements IFibonacciSearch {
        public int rank(int key, int[] a) {
            if (a.length < 1) {
                return -1;
            }

            int fibKm2 = 0;
            int fibKm1 = 1;
            int fibK = 1;

            while (fibK <= a.length) {
                int tmp = fibK;
                fibK = fibK + fibKm1;
                fibKm2 = fibKm1;
                fibKm1 = tmp;
            }

            while (true) {

                fibKm2 = Math.min(fibKm2, a.length - 1);

                int curr = a[fibKm2];

                if (curr == key) {
                    return fibKm2;
                } else if (curr < key) {
                    if (fibK <= 1) {
                        return -1;
                    }
                    int tmp = fibK;
                    fibK = fibKm1;
                    fibKm1 = tmp - fibKm1;
                    fibKm2 = fibK - fibKm1 + fibKm2;
                    //     i = fibKm2;
                } else {
                    if (fibK <= 2) {
                        return fibKm2 > 0 && key == a[fibKm2 - 1] ? fibKm2 - 1 : -1;
                    }
                    fibK = fibK - fibKm1;
                    fibKm1 = fibKm1 - fibK;
                    fibKm2 = fibKm2 - fibKm1;
                }
            }
        }
    }

    public static class KnuthFibonacciSearch implements IFibonacciSearch {
        public int rank(int key, int[] a) {
            if (a.length < 1) {
                return -1;
            }

            /*
             * Now that I've made my own version of this search,
             * I'll look up Knuth's version and try to implement it.
             */

            /*
             * Compared to my code,
             * i -> fibK
             * p -> fibKm1
             * q -> fibKm2
             */

            int i = 1;
            int p = 1;
            int q = 0;

            // Find smallest Fibonacci number greater than N
            while (i <= a.length) {
                int tmp = i;
                i = i + p;
                q = p;
                p = tmp;
            }

            while (true) {
                /*
                 * The next 2 lines aren't in Knuth's
                 * algorithm, but he does assume that FibKp1
                 * happens to equal N + 1.  Perhaps some adjustment
                 * is needed when that assumption doesn't hold.
                 *
                 * Also, his algorithm suggests that you should step
                 * the fib numbers back by 1 after you've found the
                 * smallest fib number greater than N. However, I found
                 * that doing that risks not finding the key when it's in
                 * the array.
                 */
                i = Math.min(i, a.length - 1);
                i = Math.max(i, 0);

                if (key < a[i]) {
                    if (q == 0) {
                        return -1;
                    }
                    // Go back 1 step in fib sequence
                    i = i - q;
                    int tmp = p;
                    p = q;
                    q = tmp - q;
                } else if (key > a[i]) {
                    if (p == 1) {
                        return -1;
                    }
                    /*
                     * Setting p and q back 1 step
                     * What are we doing with i?
                     */
                    i = i + q;
                    int tmp = p;
                    p = p - q;
                    q = q + q - tmp;
                } else {
                    return i;
                }
            }
        }
    }

    public static class FergusonFibonacciSearch implements IFibonacciSearch {
        public int rank(int key, int[] a) {
            /*
             * Let's see how I do implementing the original
             * 1960 version by David Furguson.
             */

            if (a.length < 1) {
                return -1;
            }

            int fibKm2 = 0;
            int fibKm1 = 1;
            int fibK = 1;

            while (fibK < a.length) {
                int tmp = fibK;
                fibK = fibK + fibKm1;
                fibKm2 = fibKm1;
                fibKm1 = tmp;
            }

            while (true) {
                fibKm1 = Math.min(fibKm1, a.length - 1);

                if (key == a[fibKm1]) {
                    return fibKm1;
                } else if (key < a[fibKm1]) {
                    if (fibKm2 <= 0) {
                        return fibKm1 > 0 && key == a[fibKm1 - 1] ? fibKm1 - 1 : -1;
                    }
                    fibK = fibK - fibKm2;
                    fibKm2 = fibK - fibKm2;
                    fibKm1 = fibKm1 - fibKm2;
                } else {
                    if (fibK <= 1) {
                        return -1;
                    }
                    int tmp = fibK - fibKm2;
                    fibK = fibKm2;
                    fibKm2 = fibKm2 + fibKm2 - tmp;
                    fibKm1 = tmp - fibK + fibKm1;
                }
            }
        }
    }

    public static class NotesForMScFibonacciSearch implements IFibonacciSearch {
        public int rank(int key, int[] a) {
            /*
             * Variant found here:
             * https://notesformsc.org/fibonacci-search-algorithm/
             * I find this the most intuitive as it looks like a
             * binary search except you're using decreasing
             * Fibonacci numbers as your "midpoints" instead of
             * powers of 1/2
             */

            int fibKm2 = 0;
            int fibKm1 = 1;
            /*
             * fibK but we don't need fibK after finding the intial
             * fib values so we use it a tmp variable when stepping
             * down fib values.
             */
            int tmp = 1;

            while (fibKm1 < a.length) {
                int tmp2 = tmp;
                tmp = tmp + fibKm1;
                fibKm2 = fibKm1;
                fibKm1 = tmp2;
            }

            int lo = 0;
            int hi = a.length - 1;

            while (lo <= hi) {
                int index = fibKm2 + lo;

                if (key == a[index]) {
                    return index;
                } else if (key < a[index]) {
                    hi = index - 1;
                } else {
                    lo = index + 1;
                }

                while (fibKm2 > hi - lo) {
                    tmp = fibKm2;
                    fibKm2 = fibKm1 - fibKm2;
                    fibKm1 = tmp;
                }

            }
            return -1;
        }
    }

    private static IFibonacciSearch getFibonacciSearch(String args[]) {
        if (args.length > 0 && "k".equals(args[0])) {
            return new KnuthFibonacciSearch();
        } else if (args.length > 0 && "f".equals(args[0])) {
            return new FergusonFibonacciSearch();
        } else {
            return new FibonacciSearch();
        }
    }

    public static void main(String[] args) {
        In in = InFactory.get(args, 1, Ex22.class);

        int n = in.readInt();

        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = in.readInt();
        }

        IFibonacciSearch fibonacciSearch = getFibonacciSearch(args);

        while (!in.isEmpty()) {
            int key = in.readInt();
            StdOut.println(String.format("%11d %d", key, fibonacciSearch.rank(key, a)));
        }
    }

}
