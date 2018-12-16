package me.xdrop.matchr.rymm.algorithms;

import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.rymm.matrix.CostMatrix;
import me.xdrop.matchr.rymm.matrix.Substitution;

import static java.lang.System.out;
import static me.xdrop.matchr.Util.*;

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
        int round = (int) Math.round(100 * ((totalLen - dist) / (double) totalLen));
        out.printf("score %3d from [%3d,%3d,%3d] [%12s -> %12s]\n", round, base.length(), target.length(), totalLen, base, target);
        return round;
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
                    int deletion = d[i - 1][j] + (intelligent ? scale(1.6) : scale(1.6));
                    int insertion = d[i][j - 1] + (intelligent ? scale(0.7) : scale(1.2));
                    int substitution = d[i - 1][j - 1] + (intelligent ? scale(subst.cost(expect, actual)) : scale(1));
                    int x = intMin(new int[]{deletion, insertion, substitution});
                    d[i][j] = x;

                    if (x == deletion) out.printf(      "delet -> %c $ %c = %4d [%12s -> %12s]\n", expect, actual, x, base, target);
                    if (x == insertion) out.printf(     "inser -> %c $ %c = %4d [%12s -> %12s]\n", expect, actual, x, base, target);
                    if (x == substitution) out.printf(  "subst -> %c $ %c = %4d [%12s -> %12s]\n", expect, actual, x, base, target);
                }
            }
        }

        out.printf("dist %3d from %3d [%s -> %12s]\n", d[m-1][n-1]/100, d[m-1][n-1], base, target);

        return d[m - 1][n - 1] / 100;
    }

    private int scale(double amount) {
        return (int) Math.round(amount * 100); // multiply by set scale
    }
}
