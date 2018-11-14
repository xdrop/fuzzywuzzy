package me.xdrop.fuzzywuzzy.rymm.algorithms;

import me.xdrop.fuzzywuzzy.rymm.matrix.CostMatrix;
import me.xdrop.fuzzywuzzy.rymm.matrix.Substitution;

import static me.xdrop.fuzzywuzzy.Util.*;

public class EditDistance {
    private final static CostMatrix subst = new Substitution();

    public int get(String base, String target) {
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
                            d[i - 1][j] + 1, // deletion
                            d[i][j - 1] + 1, // insertion
                            d[i - 1][j - 1] + subst.cost(expect, actual)
                    });
                }
            }
        }

        return (int) Math.round(100 * (m + n - d[m - 1][n - 1]) / (double) m + n);
    }
}
