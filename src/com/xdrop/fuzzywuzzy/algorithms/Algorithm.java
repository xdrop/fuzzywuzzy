package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.Ratio;

public interface Algorithm {

    int apply(String s1, String s2);

    int apply(String s1, String s2, Ratio ratio);

}
