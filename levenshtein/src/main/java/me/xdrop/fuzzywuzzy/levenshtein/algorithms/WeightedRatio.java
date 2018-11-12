package me.xdrop.fuzzywuzzy.levenshtein.algorithms;

import me.xdrop.fuzzywuzzy.functions.StringMapper;

import static me.xdrop.fuzzywuzzy.levenshtein.Levenshtein.Method.*;

@SuppressWarnings("WeakerAccess")
public class WeightedRatio extends BasicAlgorithm {
    public static final double UNBASE_SCALE = .95;
    public static final double PARTIAL_SCALE = .90;
    public static final boolean TRY_PARTIALS = true;

    @Override
    public int apply(String s1, String s2, StringMapper<String> stringProcessor) {
        s1 = stringProcessor.apply(s1);
        s2 = stringProcessor.apply(s2);

        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 == 0 || len2 == 0) {
            return 0;
        }

        boolean tryPartials = TRY_PARTIALS;
        double unbaseScale = UNBASE_SCALE;
        double partialScale = PARTIAL_SCALE;

        int base = SIMPLE_RATIO.apply(s1, s2);
        double lenRatio = ((double) Math.max(len1, len2)) / Math.min(len1, len2);

        // if strings are similar length don't use partials
        if (lenRatio < 1.5) tryPartials = false;

        // if one string is much shorter than the other
        if (lenRatio > 8) partialScale = .6;

        if (tryPartials) {
            double partial = PARTIAL_RATIO.apply(s1, s2) * partialScale;
            double partialSor = TOKEN_SORT_PARTIAL.apply(s1, s2) * unbaseScale * partialScale;
            double partialSet = TOKEN_SET_PARTIAL.apply(s1, s2) * unbaseScale * partialScale;

            return (int) Math.round(PrimitiveUtils.max(base, partial, partialSor, partialSet));
        } else {
            double tokenSort = TOKEN_SORT_SIMPLE.apply(s1, s2) * unbaseScale;
            double tokenSet = TOKEN_SET_SIMPLE.apply(s1, s2) * unbaseScale;

            return (int) Math.round(PrimitiveUtils.max(base, tokenSort, tokenSet));
        }
    }
}
