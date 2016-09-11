package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.Ratio;
import com.xdrop.fuzzywuzzy.StringProcessor;
import com.xdrop.fuzzywuzzy.ratios.SimpleRatio;

import java.util.Arrays;
import java.util.List;

public class TokenSort implements RatioAlgorithm {

    @Override
    public int apply(String s1, String s2) {

        return apply(s1, s2, new SimpleRatio());

    }

    @Override
    public int apply(String s1, String s2, StringProcessor stringProcessor) {

        return apply(s1, s2, new SimpleRatio(), stringProcessor);

    }

    @Override
    public int apply(String s1, String s2, Ratio ratio) {

        return apply(s1, s2, ratio, new DefaultStringProcessor());

    }

    @Override
    public int apply(String s1, String s2, Ratio ratio, StringProcessor stringProcessor) {

        String sorted1 = processAndSort(s1, stringProcessor);
        String sorted2 = processAndSort(s2, stringProcessor);

        return ratio.apply(sorted1, sorted2);

    }

    private static String processAndSort(String in, StringProcessor stringProcessor) {

        in = stringProcessor.process(in);
        String[] wordsArray = in.split("\\s+");

        List<String> words = Arrays.asList(wordsArray);
        String joined = Utils.sortAndJoin(words, " ");

        return joined.trim();

    }

}
