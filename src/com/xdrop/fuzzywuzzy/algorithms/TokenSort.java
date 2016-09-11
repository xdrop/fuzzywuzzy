package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.Ratio;
import com.xdrop.fuzzywuzzy.ratios.SimpleRatio;

public class TokenSort implements Algorithm {

    @Override
    public int apply(String s1, String s2) {

        return apply(s1, s2, new SimpleRatio());

    }

    @Override
    public int apply(String s1, String s2, Ratio ratio) {

        String sorted1 = StringProcess.processAndSort(s1);
        String sorted2 = StringProcess.processAndSort(s2);

        return ratio.apply(sorted1, sorted2);
    }

}
