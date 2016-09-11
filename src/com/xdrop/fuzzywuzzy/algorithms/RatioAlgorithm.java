package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.Ratio;
import com.xdrop.fuzzywuzzy.StringProcessor;
import com.xdrop.fuzzywuzzy.ratios.SimpleRatio;

public abstract class RatioAlgorithm extends BasicAlgorithm {

    private Ratio ratio;

    public RatioAlgorithm() {
        super();
        this.ratio = new SimpleRatio();
    }

    public RatioAlgorithm(StringProcessor stringProcessor) {
        super(stringProcessor);
    }

    public RatioAlgorithm(Ratio ratio) {
        super();
        this.ratio = ratio;
    }


    public RatioAlgorithm(StringProcessor stringProcessor, Ratio ratio) {
        super(stringProcessor);
        this.ratio = ratio;
    }

    public abstract int apply(String s1, String s2, Ratio ratio, StringProcessor stringProcessor);

    public RatioAlgorithm with(Ratio ratio) {
        setRatio(ratio);
        return this;
    }

    public int apply(String s1, String s2, Ratio ratio) {
        return apply(s1, s2, ratio, getStringProcessor());
    }

    @Override
    public int apply(String s1, String s2, StringProcessor stringProcessor) {
        return apply(s1, s2, getRatio(), stringProcessor);
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public Ratio getRatio() {
        return ratio;
    }
}
