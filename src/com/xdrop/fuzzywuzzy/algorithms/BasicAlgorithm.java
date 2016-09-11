package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.StringProcessor;

public interface BasicAlgorithm {

    int apply(String s1, String s2);

    int apply(String s1, String s2, StringProcessor stringProcessor);

}
