package com.xdrop.fuzzywuzzy;

import com.xdrop.fuzzywuzzy.ratios.PartialRatio;
import com.xdrop.fuzzywuzzy.ratios.SimpleRatio;

import java.util.*;

import static com.xdrop.fuzzywuzzy.PrimitiveUtils.max;
import static java.lang.Math.round;

@SuppressWarnings("WeakerAccess")
public class FuzzySearch {

    public static final double UNBASE_SCALE = .95;
    public static final double PARTIAL_SCALE = .90;
    public static final boolean TRY_PARTIALS = true;

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
     * Returns a partial ratio.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio
     */
    public static int partialRatio(String s1, String s2) {

        return new PartialRatio().apply(s1, s2);

    }

    private static String processAndSort(String in) {

        in = Utils.processString(in, false);
        String[] wordsArray = in.split("\\s+");

        List<String> words = Arrays.asList(wordsArray);
        String joined = Utils.sortAndJoin(words, " ");

        return joined.trim();

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1
     * @param s2
     * @param partial Whether to apply partial ratio or not
     * @return The ratio of the strings
     */
    private static int tokenSort(String s1, String s2, boolean partial) {

        String sorted1 = processAndSort(s1);
        String sorted2 = processAndSort(s2);

        return partial ? partialRatio(sorted1, sorted2) : ratio(sorted1, sorted2);

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1
     * @param s2
     * @return The partial ratio of the strings
     */
    public static int tokenSortPartial(String s1, String s2) {

        return tokenSort(s1, s2, true);

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1
     * @param s2
     * @return The full ratio of the strings
     */
    public static int tokenSortRatio(String s1, String s2) {

        return tokenSort(s1, s2, false);

    }

    private static int tokenSet(String s1, String s2, Ratio ratio) {

        s1 = Utils.processString(s1, false);
        s2 = Utils.processString(s2, false);

        Set<String> tokens1 = Utils.tokenizeSet(s1);
        Set<String> tokens2 = Utils.tokenizeSet(s2);

        Set<String> intersection = SetUtils.intersection(tokens1, tokens2);
        Set<String> diff1to2 = SetUtils.difference(tokens1, tokens2);
        Set<String> diff2to1 = SetUtils.difference(tokens2, tokens1);

        String sortedInter = Utils.sortAndJoin(intersection, " ").trim();
        String sorted1to2 = (sortedInter + " " + Utils.sortAndJoin(diff1to2, " ")).trim();
        String sorted2to1 = (sortedInter + " " + Utils.sortAndJoin(diff2to1, " ")).trim();

        List<Integer> results = new ArrayList<>();

        results.add(ratio.apply(sortedInter, sorted1to2));
        results.add(ratio.apply(sortedInter, sorted2to1));
        results.add(ratio.apply(sorted1to2, sorted2to1));

        return Collections.max(results);

    }

    public static int tokenSetRatio(String s1, String s2) {

        return tokenSet(s1, s2, new SimpleRatio());

    }

    public static int tokenSetPartial(String s1, String s2) {

        return tokenSet(s1, s2, new PartialRatio());

    }

    public static int weightedRatio(String s1, String s2) {

        s1 = Utils.processString(s1, false);
        s2 = Utils.processString(s2, false);

        int len1 = s1.length();
        int len2 = s2.length();

        boolean tryPartials = TRY_PARTIALS;
        double unbaseScale = UNBASE_SCALE;
        double partialScale = PARTIAL_SCALE;

        int base = ratio(s1, s2);
        double lenRatio = (double) (Math.max(len1, len2) / Math.min(len1, len2));

        // if strings are similar length don't use partials
        if (lenRatio < 1.5) tryPartials = false;

        // if one string is much shorter than the other
        if (lenRatio > 8) partialScale = .6;

        if (tryPartials) {

            double partial = partialRatio(s1, s2) * partialScale;
            double partialSor = tokenSortPartial(s1, s2) * unbaseScale * partialScale;
            double partialSet = tokenSetPartial(s1, s2) * unbaseScale * partialScale;

            return (int) round(max(partial, partialSor, partialSet));
            
        } else {

            double tokenSort = tokenSortRatio(s1, s2) * unbaseScale;
            double tokenSet = tokenSetRatio(s1, s2) * unbaseScale;

            return (int) round(max(tokenSort, tokenSet));

        }

    }

}
