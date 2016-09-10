package com.xdrop.fuzzywuzzy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcess {

    private final static String pattern = "(?ui)\\W";
    private final static Pattern r = Pattern.compile(pattern);

    public static String subNonAlphaNumeric(String in, String sub) {

        Matcher m = r.matcher(in);

        if(m.matches()){
            return m.replaceAll(sub);
        } else {
            return "";
        }

    }

}
