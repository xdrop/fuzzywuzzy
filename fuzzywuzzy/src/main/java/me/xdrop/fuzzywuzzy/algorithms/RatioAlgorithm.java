package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Ratio;
import me.xdrop.fuzzywuzzy.ToStringFunction;
import me.xdrop.fuzzywuzzy.ratios.SimpleRatio;

public abstract class RatioAlgorithm extends BasicAlgorithm {

    private Ratio ratio;

    public RatioAlgorithm() {
        super();
        this.ratio = SimpleRatio.instance;
    }

    public RatioAlgorithm(ToStringFunction<String> stringFunction) {
        super(stringFunction);
    }

    public RatioAlgorithm(Ratio ratio) {
        super();
        this.ratio = ratio;
    }


    public RatioAlgorithm(ToStringFunction<String> stringFunction, Ratio ratio) {
        super(stringFunction);
        this.ratio = ratio;
    }

    public abstract int apply(String s1, String s2, Ratio ratio, ToStringFunction<String> stringFunction);

    public RatioAlgorithm with(Ratio ratio) {
        setRatio(ratio);
        return this;
    }

    public int apply(String s1, String s2, Ratio ratio) {
        return apply(s1, s2, ratio, getStringFunction());
    }

    @Override
    public int apply(String s1, String s2, ToStringFunction<String> stringFunction) {
        return apply(s1, s2, getRatio(), stringFunction);
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public Ratio getRatio() {
        return ratio;
    }
}
