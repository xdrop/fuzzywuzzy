package com.xdrop.fuzzywuzzy.ratios;

import com.xdrop.diffutils.DiffUtils;
import com.xdrop.fuzzywuzzy.Ratio;

public class SimpleRatio implements Ratio {
    @Override
    public int apply(String s1, String s2) {

        return (int) Math.round(100 * DiffUtils.getRatio(s1, s2));

    }
}
