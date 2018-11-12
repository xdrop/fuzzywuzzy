package me.xdrop.fuzzywuzzy.levenshtein.ratios;

import me.xdrop.fuzzywuzzy.functions.ScoringFunction;
import me.xdrop.fuzzywuzzy.levenshtein.diffutils.DiffUtils;

public class SimpleRatio implements ScoringFunction {
    /**
     * Computes a simple Levenshtein distance ratio between the strings
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
