package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.Applicable;
import me.xdrop.fuzzywuzzy.StringProcessor;

public abstract class BasicAlgorithm implements Applicable {

    private StringProcessor stringProcessor;

    public BasicAlgorithm() {
        this.stringProcessor = new DefaultStringProcessor();
    }

    public BasicAlgorithm(StringProcessor stringProcessor) {
        this.stringProcessor = stringProcessor;
    }

    public abstract int apply(String s1, String s2, StringProcessor stringProcessor);

    public int apply(String s1, String s2){

        return apply(s1, s2, this.stringProcessor);

    }

    public BasicAlgorithm with(StringProcessor stringProcessor){
        setStringProcessor(stringProcessor);
        return this;
    }

    public BasicAlgorithm noProcessor(){
        this.stringProcessor = new NoProcess();
        return this;
    }

    void setStringProcessor(StringProcessor stringProcessor){
        this.stringProcessor = stringProcessor;
    }

    public StringProcessor getStringProcessor() {
        return stringProcessor;
    }
}
