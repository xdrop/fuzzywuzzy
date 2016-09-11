package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.StringProcessor;

public class NoProcess implements StringProcessor {

    @Override
    public String process(String in) {
        return in;
    }

}
