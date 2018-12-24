package me.xdrop.matchr.fuzzywuzzy.ratios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.fuzzywuzzy.levenshtein.Levenshtein;
import me.xdrop.matchr.fuzzywuzzy.levenshtein.structs.MatchingBlock;

/**
 * Partial ratio of similarity
 */
public class PartialRatio implements ScoringFunction {
    /**
     * Computes a partial ratio between the strings
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio
     */
    @Override
    public Integer apply(String s1, String s2) {
        String shorter;
        String longer;

        if (s1.length() < s2.length()){
            shorter = s1;
            longer = s2;
        } else {
            shorter = s2;
            longer = s1;
        }

        MatchingBlock[] matchingBlocks = Levenshtein.getMatchingBlocks(shorter, longer);
        List<Double> scores = new ArrayList<>();

        for (MatchingBlock mb : matchingBlocks) {
            int dist = mb.dpos - mb.spos;

            int long_start = dist > 0 ? dist : 0;
            int long_end = long_start + shorter.length();

            if(long_end > longer.length()) long_end = longer.length();

            String long_substr = longer.substring(long_start, long_end);

            double ratio = Levenshtein.getRatio(shorter, long_substr);

            if (ratio > .995) {
                return 100;
            } else {
                scores.add(ratio);
            }
        }

        return (int) Math.round(100 * Collections.max(scores));
    }
}
