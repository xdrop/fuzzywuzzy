package com.xdrop.fuzzywuzzy.algorithms;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StringProcess {

    private final static String pattern = "[\\p{IsAlphabetic}|\\p{Digit}]";
    private final static Pattern r = Pattern.compile(pattern);


    /**
     * Substitute non alphanumeric characters.
     *
     * @param in The input string
     * @param sub The string to substitute with
     * @return The replaced string
     */
    static String subNonAlphaNumeric(String in, String sub) {

        Matcher m = r.matcher(in);

        if(m.matches()){
            return m.replaceAll(sub);
        } else {
            return in;
        }

    }

    static String processAndSort(String in) {

        in = Utils.processString(in, false);
        String[] wordsArray = in.split("\\s+");

        List<String> words = Arrays.asList(wordsArray);
        String joined = Utils.sortAndJoin(words, " ");

        return joined.trim();

    }

}
