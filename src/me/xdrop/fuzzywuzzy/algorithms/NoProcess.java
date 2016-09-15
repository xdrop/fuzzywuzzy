package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.StringProcessor;

public class NoProcess implements StringProcessor {

    @Override
    public String process(String in) {
        return in;
    }

}
