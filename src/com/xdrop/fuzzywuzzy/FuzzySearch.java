package com.xdrop.fuzzywuzzy;

import com.xdrop.fuzzywuzzy.algorithms.TokenSet;
import com.xdrop.fuzzywuzzy.algorithms.TokenSort;
import com.xdrop.fuzzywuzzy.algorithms.WeightedRatio;
import com.xdrop.fuzzywuzzy.ratios.PartialRatio;
import com.xdrop.fuzzywuzzy.ratios.SimpleRatio;


/**
 * FuzzySearch facade class
 */
@SuppressWarnings("WeakerAccess")
public class FuzzySearch {


    /**
     * Calculates a Levenshtein simple ratio between the strings.
     * This is indicates a measure of similarity
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The simple ratio
     */
    public static int ratio(String s1, String s2) {

        return new SimpleRatio().apply(s1, s2);

    }

    /**
     * Inconsistent substrings lead to problems in matching. This ratio
     * uses a heuristic called "best partial" for when two strings
     * are of noticeably different lengths.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio
     */
    public static int partialRatio(String s1, String s2) {

        return new PartialRatio().apply(s1, s2);

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio of the strings
     */
    public static int tokenSortPartialRatio(String s1, String s2) {

        return new TokenSort().apply(s1, s2, new PartialRatio());

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The full ratio of the strings
     */
    public static int tokenSortRatio(String s1, String s2) {

        return new TokenSort().apply(s1, s2, new SimpleRatio());

    }


    /**
     * Splits the strings into tokens and computes intersections and remainders
     * between the tokens of the two strings. A comparison string is then
     * built up and is compared using the simple ratio algorithm.
     * Useful for strings where words appear redundantly.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The ratio of similarity
     */
    public static int tokenSetRatio(String s1, String s2) {

        return new TokenSet().apply(s1, s2, new SimpleRatio());

    }

    /**
     * Splits the strings into tokens and computes intersections and remainders
     * between the tokens of the two strings. A comparison string is then
     * built up and is compared using the simple ratio algorithm.
     * Useful for strings where words appear redundantly.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The ratio of similarity
     */
    public static int tokenSetPartialRatio(String s1, String s2) {

        return new TokenSet().apply(s1, s2, new PartialRatio());

    }

    /**
     * Calculates a weighted ratio between the different algorithms for best results
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The ratio of similarity
     */
    public static int weightedRatio(String s1, String s2) {

        return new WeightedRatio().apply(s1, s2);

    }


}
