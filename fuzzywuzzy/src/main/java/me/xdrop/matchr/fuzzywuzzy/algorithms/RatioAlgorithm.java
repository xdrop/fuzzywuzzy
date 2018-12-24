package me.xdrop.matchr.fuzzywuzzy.algorithms;

import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.functions.StringMapper;
import me.xdrop.matchr.fuzzywuzzy.ratios.SimpleRatio;

public abstract class RatioAlgorithm extends BasicAlgorithm {
    private ScoringFunction ratio;

    public RatioAlgorithm() {
        super();
        this.ratio = new SimpleRatio();
    }

    public RatioAlgorithm(StringMapper<String> stringFunction) {
        super(stringFunction);
    }

    public RatioAlgorithm(ScoringFunction ratio) {
        super();
        this.ratio = ratio;
    }


    public RatioAlgorithm(StringMapper<String> stringFunction, ScoringFunction ratio) {
        super(stringFunction);
        this.ratio = ratio;
    }

    public abstract int apply(String s1, String s2, ScoringFunction ratio, StringMapper<String> stringFunction);

    public RatioAlgorithm with(ScoringFunction ratio) {
        setRatio(ratio);
        return this;
    }

    public int apply(String s1, String s2, ScoringFunction ratio) {
        return apply(s1, s2, ratio, getStringFunction());
    }

    @Override
    public int apply(String s1, String s2, StringMapper<String> stringFunction) {
        return apply(s1, s2, getRatio(), stringFunction);
    }

    public void setRatio(ScoringFunction ratio) {
        this.ratio = ratio;
    }

    public ScoringFunction getRatio() {
        return ratio;
    }
}
