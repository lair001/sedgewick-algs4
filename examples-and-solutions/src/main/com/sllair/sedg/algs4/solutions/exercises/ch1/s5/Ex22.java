package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.utils.InFactory;
import com.sllair.sedg.algs4.utils.StringUtils;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * To verify that quick find and quick union have quadratic time
 * complexities and weighted quick union has linear time
 * complexity for the dynamic connectivity problem, run this
 * problem and inspect its output. Note that for large N, the
 * runtime increases by a factor of around 4 when N is doubled
 * for quick find and quick union. This ratio is around 2 for
 * weighted quick union.
 */
public class Ex22 {

    private static final String DIVIDER = " -------------------------";

    public static int count(int n, UnionFindType type) {
        IUnionFind uf = UnionFindFactory.get(n, type);

        int numPairs = 0;

        while (uf.count() > 1) {
            numPairs++;

            int p = StdRandom.uniform(n);
            int q = StdRandom.uniform(n);

            if (!uf.connected(p, q)) {
                uf.union(p, q);
            }
        }

        return numPairs;
    }

    public static void main(String[] args) {
        int T; // num of trials
        if (args.length > 0 && StringUtils.isInteger(args[0])) {
            T = Integer.parseInt(args[0]);
        } else {
            In in = InFactory.get(args, Ex22.class);
            T = in.readInt();
        }

        StdOut.println();

        for (UnionFindType type : UnionFindType.values()) {
            StdOut.println(String.format(" %s", type.getLabel()));
            StdOut.println(DIVIDER);

            int n = type.getMinN();
            long prevRuntime = 0L;

            while (n <= type.getMaxN()) {
                int genPairTotal = 0;
                long startTime = System.currentTimeMillis();

                for (int i = 0; i < T; i++) {
                    genPairTotal += count(n, type);
                }

                long currRuntime = System.currentTimeMillis() - startTime;

                StdOut.println(String.format("%8d %10.2f %6.3f",
                        n,
                        genPairTotal / (double) T,
                        prevRuntime == 0L ? 0.0 : currRuntime / (double) prevRuntime
                ));

                prevRuntime = currRuntime;
                n += n;
            }

            StdOut.println();
        }

    }

}
