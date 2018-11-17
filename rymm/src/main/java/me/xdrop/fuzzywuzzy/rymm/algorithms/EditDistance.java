package me.xdrop.fuzzywuzzy.rymm.algorithms;

import me.xdrop.fuzzywuzzy.functions.ScoringFunction;
import me.xdrop.fuzzywuzzy.rymm.matrix.CostMatrix;
import me.xdrop.fuzzywuzzy.rymm.matrix.Substitution;

import static me.xdrop.fuzzywuzzy.Util.*;

public class EditDistance implements ScoringFunction {
    private final static CostMatrix subst = new Substitution();
    private final boolean intelligent;

    public EditDistance(boolean intelligent) {
        this.intelligent = intelligent;
    }

    @Override
    public Integer apply(String base, String target) {
        int totalLen = base.length() + target.length();
        int dist = dist(base, target);
        return (int) Math.round(100 * ((totalLen - dist) / (double) totalLen));
    }

    private int dist(String base, String target) {
        final char[] s = base.toCharArray();
        final char[] t = target.toCharArray();
        final int m = s.length;
        final int n = t.length;
        final int[][] d = new int[m][n];

        for (int i : intRange(0, m - 1)) d[i][0] = i;
        for (int j : intRange(0, n - 1)) d[0][j] = j;

        for (int j : intRange(1, n)) {
            for (int i : intRange(1, m)) {
                final char expect = s[i];
                final char actual = t[j];
                if (expect == actual)
                    d[i][j] = d[i - 1][j - 1];
                else {
                    d[i][j] = intMin(new int[]{
                            d[i - 1][j] + (intelligent ? scale(1.6) : scale(1.6)), // TODO deletion
                            d[i][j - 1] + (intelligent ? scale(0.7) : scale(1.2)), // TODO insertion
                            d[i - 1][j - 1] + (intelligent ? scale(subst.cost(expect, actual)) : scale(1)) // substitution
                    });
                }
            }
        }

        return d[m - 1][n - 1] / 100;
    }

    private int scale(double amount) {
        return (int) Math.round(amount * 100); // multiply by set scale
    }
}
