package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

public interface IUnionFind {

    public int count();

    public int find(int p);

    public boolean connected(int p, int q);

    public void union(int p, int q);

}
