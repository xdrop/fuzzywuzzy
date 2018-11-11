package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Ratio;

import java.util.Arrays;
import java.util.List;
import me.xdrop.fuzzywuzzy.ToStringFunction;

public class TokenSort extends RatioAlgorithm {
    public static final TokenSort instance = new TokenSort();

    private TokenSort() {
    }

    @Override
    public int apply(String s1, String s2, Ratio ratio, ToStringFunction<String> stringFunction) {

        String sorted1 = processAndSort(s1, stringFunction);
        String sorted2 = processAndSort(s2, stringFunction);

        return ratio.apply(sorted1, sorted2);

    }

    private static String processAndSort(String in, ToStringFunction<String> stringProcessor) {

        in = stringProcessor.apply(in);
        String[] wordsArray = in.split("\\s+");

        List<String> words = Arrays.asList(wordsArray);
        String joined = Utils.sortAndJoin(words, " ");

        return joined.trim();

    }

}
