package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.Ratio;
import com.xdrop.fuzzywuzzy.StringProcessor;

import java.util.Arrays;
import java.util.List;

public class TokenSort extends RatioAlgorithm {

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
