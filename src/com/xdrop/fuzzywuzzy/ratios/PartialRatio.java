package com.xdrop.fuzzywuzzy.ratios;

import com.xdrop.diffutils.DiffUtils;
import com.xdrop.diffutils.structs.MatchingBlock;
import com.xdrop.fuzzywuzzy.Ratio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartialRatio implements Ratio {

    @Override
    public int apply(String s1, String s2) {

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

        List<Double> scores = new ArrayList<>();

        for (MatchingBlock mb : matchingBlocks) {

            int dist = mb.dpos - mb.spos;

            int long_start = dist > 0 ? dist : 0;
            int long_end = long_start + shorter.length();


            String long_substr = longer.substring(long_start, long_end);

            double ratio = DiffUtils.getRatio(shorter, long_substr);

            if (ratio > .995) {
                return 100;
            } else {
                scores.add(ratio);
            }

        }

        return (int) Math.round(100 * Collections.max(scores));

    }


}
