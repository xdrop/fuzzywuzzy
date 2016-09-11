package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.Ratio;
import com.xdrop.fuzzywuzzy.StringProcessor;

public interface RatioAlgorithm extends BasicAlgorithm {

    int apply(String s1, String s2, Ratio ratio);

    int apply(String s1, String s2, Ratio ratio, StringProcessor stringProcessor);

}
