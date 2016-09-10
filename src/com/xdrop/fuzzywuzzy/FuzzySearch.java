package com.xdrop.fuzzywuzzy;

import com.xdrop.diffutils.DiffUtils;
import com.xdrop.diffutils.structs.MatchingBlock;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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

        return (int) Math.round(100 * DiffUtils.getRatio(s1, s2));

    }

    /**
     * Returns a partial ratio.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio
     */
    public static int partialRatio(String s1, String s2) {

        String shorter;
        String longer;

        if (s1.length() < s2.length()){

            shorter = s1;
            longer = s2;

        } else {

            shorter = s2;
            longer = s1;

        }

        MatchingBlock[] matchingBlocks = DiffUtils.getMatchingBlocks(s1, s2);

        List<Double> scores = new ArrayList<Double>();

        for (MatchingBlock mb : matchingBlocks) {

            int dist = mb.dpos - mb.spos;

            int long_start = dist > 0 ? dist : 0;
            int long_end = long_start + shorter.length();

            String long_substr = longer.substring(long_start, long_end);

            double ratio = ratio(shorter, long_substr);

            if (ratio > .995) {
                return 100;
            } else {
                scores.add(ratio);
            }

        }

        return (int) Math.round(100 * Collections.max(scores));

    }

    private String processAndSort(String in) {

        in = Utils.processString(in, false);
        String[] wordsArray = in.split("\\s+");

        List<String> words = Arrays.asList(wordsArray);
        Collections.sort(words);

        String joined = StringUtils.join(words, " ");

        return joined.trim();

    }

    private int tokenSort(String s1, String s2, boolean partial) {

        String sorted1 = processAndSort(s1);
        String sorted2 = processAndSort(s2);

        return partial ? partialRatio(sorted1, sorted2) : ratio(sorted1, sorted2);

    }



}
