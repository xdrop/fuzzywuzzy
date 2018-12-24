package me.xdrop.matchr.fuzzywuzzy.algorithms;

import java.util.Arrays;
import java.util.List;
import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.functions.StringMapper;
import me.xdrop.matchr.fuzzywuzzy.ratios.SimpleRatio;

public class TokenSort implements ScoringFunction {
    private StringMapper<String> stringProcessor;
    private ScoringFunction ratio;

    public TokenSort() {
        this.stringProcessor = new DefaultStringProcessor();
        this.ratio = new SimpleRatio();
    }

    private TokenSort(ScoringFunction ratio, StringMapper<String> stringProcessor) {
        this.stringProcessor = stringProcessor;
        this.ratio = ratio;
    }

    public static TokenSort with(ScoringFunction ratio) {
        return new TokenSort(ratio, new DefaultStringProcessor());
    }

    public static TokenSort with(StringMapper<String> stringProcessor) {
        return new TokenSort(new SimpleRatio(), stringProcessor);
    }

    public static TokenSort with(ScoringFunction ratio, StringMapper<String> stringProcessor) {
        return new TokenSort(ratio, stringProcessor);
    }

    public int apply(String s1, String s2) {
        String sorted1 = processAndSort(s1, stringProcessor);
        String sorted2 = processAndSort(s2, stringProcessor);

        return ratio.apply(sorted1, sorted2);
    }

    private static String processAndSort(String in, StringMapper<String> stringProcessor) {
        in = stringProcessor.apply(in);
        String[] wordsArray = in.split("\\s+");

        List<String> words = Arrays.asList(wordsArray);
        String joined = Utils.sortAndJoin(words, " ");

        return joined.trim();
    }
}
