package com.sllair.sedg.algs4.solutions.exercises.ch1.s5;

public enum UnionFindType {

    /*
     * Min and max N are calibrated based on running 200 trials
     * on my computer in a reasonable amount of time. You may
     * adjust based on your desire for more reliable stats,
     * tolerance for less reliable stats and/or the
     * computational power of your own computer.
     */
    QUICK_FIND("Quick Find", 8, 32768),
    QUICK_UNION("Quick Union", 8, 32768),
    WEIGHTED_QUICK_UNION("Weighted Quick Union", 8, 1048576);

    private final String label;
    private final int minN;
    private final int maxN;

    UnionFindType(String label, int minN, int maxN) {
        this.label = label;
        this.minN = minN;
        this.maxN = maxN;
    }

    public String getLabel() {
        return this.label;
    }

    public int getMinN() {
        return this.minN;
    }

    public int getMaxN() {
        return this.maxN;
    }

}
