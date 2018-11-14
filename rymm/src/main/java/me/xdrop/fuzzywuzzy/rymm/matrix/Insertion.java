package me.xdrop.fuzzywuzzy.rymm.matrix;

public class Insertion extends CostMatrix {
    public static final int[][] MATRIX;

    static {
        MATRIX = new int[26][]
    }

    @Override
    public int cost(char expected, char actual) {
        return 0;
    }
}
