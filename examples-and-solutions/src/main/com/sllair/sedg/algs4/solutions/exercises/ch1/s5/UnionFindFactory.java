package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

import com.sllair.sedg.algs4.examples.ch1.s5.QuickFindUF;
import com.sllair.sedg.algs4.examples.ch1.s5.QuickUnionUF;
import com.sllair.sedg.algs4.examples.ch1.s5.WeightedQuickUnionUF;

public class UnionFindFactory {

    public static IUnionFind get(int n, UnionFindType type) {
        switch (type) {
            case QUICK_FIND:
                return new QuickFindUF(n);
            case QUICK_UNION:
                return new QuickUnionUF(n);
            case WEIGHTED_QUICK_UNION:
                return new WeightedQuickUnionUF(n);
            default:
                throw new IllegalArgumentException(
                        String.format("Illegal Union Find Type: %s", type)
                );
        }
    }
}
