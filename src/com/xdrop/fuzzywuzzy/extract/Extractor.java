package com.xdrop.fuzzywuzzy.extract;

import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import com.xdrop.fuzzywuzzy.Ratio;
import com.xdrop.fuzzywuzzy.StringProcessor;
import com.xdrop.fuzzywuzzy.algorithms.BasicAlgorithm;
import com.xdrop.fuzzywuzzy.algorithms.RatioAlgorithm;

import java.util.Collection;

public class Extractor {

    private StringProcessor processor;
    private int cutoff;

    public Extractor(StringProcessor processor) {
        this.processor = processor;
        this.cutoff = 0;
    }

    public Extractor(int cutoff) {
        this.cutoff = cutoff;
        this.processor = null;
    }

    public Extractor(StringProcessor processor, int cutoff) {
        this.processor = processor;
        this.cutoff = cutoff;
    }

    public Extractor() {
        this.processor = null;
        this.cutoff = 0;
    }

    public void extractWithoutOrder(String query, Collection<String> choices, Ratio func) {

    }

    public void extractWithoutOrder(String query, Collection<String> choices, BasicAlgorithm func) {

    }

    public void extractWithoutOrder(String query, Collection<String> choices, RatioAlgorithm func) {

    }

    public void extractOne(String query, Collection<String> choice, Ratio func) {

    }

    public void extractOne(String query, Collection<String> choice, BasicAlgorithm func) {

    }

    public void extractOne(String query, Collection<String> choice, RatioAlgorithm func) {

    }

}
