package me.xdrop.matchr.fuzzywuzzy.algorithms;

import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.functions.StringMapper;

/**
 * An extended ratio is configurable with a string processor
 */
public abstract class ExtendedRatio implements ScoringFunction {

    private StringMapper<String> stringProcessor;

    public ExtendedRatio() {
        this.stringProcessor = new DefaultStringProcessor();
    }

    public ExtendedRatio(StringMapper<String> stringProcessor) {
        this.stringProcessor = stringProcessor;
    }

    public abstract int apply(String s1, String s2, StringMapper<String> stringProcessor);

    public int apply(String s1, String s2){
        return apply(s1, s2, this.stringProcessor);
    }

    public ExtendedRatio withProcessor(StringMapper<String> stringProcessor){
        setStringProcessor(stringProcessor);
        return this;
    }

    public ExtendedRatio noProcessor(){
        this.stringProcessor = StringMapper.IDENTITY;
        return this;
    }

    void setStringProcessor(StringMapper<String> stringProcessor){
        this.stringProcessor = stringProcessor;
    }

    public StringMapper<String> getStringProcessor() {
        return stringProcessor;
    }
}
