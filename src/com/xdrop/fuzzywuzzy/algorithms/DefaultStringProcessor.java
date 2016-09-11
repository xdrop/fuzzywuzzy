package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.StringProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultStringProcessor implements StringProcessor {

    private final static String pattern = "[\\p{IsAlphabetic}|\\p{Digit}]";
    private final static Pattern r = Pattern.compile(pattern);


    /**
     * Substitute non alphanumeric characters.
     *
     * @param in The input string
     * @param sub The string to substitute with
     * @return The replaced string
     */
    public static String subNonAlphaNumeric(String in, String sub) {

        Matcher m = r.matcher(in);

        if(m.matches()){
            return m.replaceAll(sub);
        } else {
            return in;
        }

    }

    @Override
    public String process(String in) {

        in = subNonAlphaNumeric(in, " ");
        in = in.toLowerCase();
        in = in.trim();

        return in;

    }

}
