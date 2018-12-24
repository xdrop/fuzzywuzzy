package me.xdrop.matchr.fuzzywuzzy.algorithms;


import com.sun.javafx.binding.StringFormatter;
import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.functions.StringMapper;
import me.xdrop.matchr.fuzzywuzzy.ratios.SimpleRatio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class TokenSet implements ScoringFunction {
    private StringMapper<String> stringProcessor;
    private ScoringFunction ratio;

    public TokenSet() {
        this.stringProcessor = new DefaultStringProcessor();
        this.ratio = new SimpleRatio();
    }

    private TokenSet(ScoringFunction ratio, StringMapper<String> stringProcessor) {
        this.stringProcessor = stringProcessor;
        this.ratio = ratio;
    }

    public static TokenSet with(ScoringFunction ratio) {
        return new TokenSet(ratio, new DefaultStringProcessor());
    }

    public static TokenSet with(StringMapper<String> stringProcessor) {
        return new TokenSet(new SimpleRatio(), stringProcessor);
    }

    public static TokenSet with(ScoringFunction ratio, StringMapper<String> stringProcessor) {
        return new TokenSet(ratio, stringProcessor);
    }

    public int apply(String s1, String s2) {
        s1 = stringProcessor.apply(s1);
        s2 = stringProcessor.apply(s2);

        Set<String> tokens1 = Utils.tokenizeSet(s1);
        Set<String> tokens2 = Utils.tokenizeSet(s2);

        Set<String> intersection = SetUtils.intersection(tokens1, tokens2);
        Set<String> diff1to2 = SetUtils.difference(tokens1, tokens2);
        Set<String> diff2to1 = SetUtils.difference(tokens2, tokens1);

        String sortedInter = Utils.sortAndJoin(intersection, " ").trim();
        String sorted1to2 = (sortedInter + " " + Utils.sortAndJoin(diff1to2, " ")).trim();
        String sorted2to1 = (sortedInter + " " + Utils.sortAndJoin(diff2to1, " ")).trim();

        List<Integer> results = new ArrayList<>();

        results.add(ratio.apply(sortedInter, sorted1to2));
        results.add(ratio.apply(sortedInter, sorted2to1));
        results.add(ratio.apply(sorted1to2, sorted2to1));

        return Collections.max(results);
    }
}
