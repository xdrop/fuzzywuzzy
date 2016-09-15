package me.xdrop.fuzzywuzzy.ratios;

import me.xdrop.diffutils.DiffUtils;
import me.xdrop.fuzzywuzzy.Ratio;

public class SimpleRatio implements Ratio {

    /**
     * Computes a simple Levenshtein distance ratio between the strings
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The resulting ratio of similarity
     */
    @Override
    public int apply(String s1, String s2) {

        return (int) Math.round(100 * DiffUtils.getRatio(s1, s2));

    }
}
