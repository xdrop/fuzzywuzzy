package me.xdrop.matchr.fuzzywuzzy.ratios;

import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.fuzzywuzzy.diffutils.DiffUtils;

public class SimpleRatio implements ScoringFunction {
    /**
     * Computes a simple Fuzzywuzzy distance ratio between the strings
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The resulting ratio of similarity
     */
    @Override
    public Integer apply(String s1, String s2) {
        return (int) Math.round(100 * DiffUtils.getRatio(s1, s2));
    }
}
