package me.xdrop.fuzzywuzzy.rymm.matrix;

public abstract class CostMatrix {
    public abstract int cost(final char expected, final char actual);
}
