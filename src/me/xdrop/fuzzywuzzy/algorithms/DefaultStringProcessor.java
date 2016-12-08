package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.StringProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultStringProcessor implements StringProcessor {

    private final static String pattern = "[^\\p{Alnum}]";
    private final static Pattern r = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);


    /**
     * Substitute non alphanumeric characters.
     *
     * @param in The input string
     * @param sub The string to substitute with
     * @return The replaced string
     */
    public static String subNonAlphaNumeric(String in, String sub) {

        Matcher m = r.matcher(in);

        if(m.find()){
            return m.replaceAll(" ");
        } else {
            return in;
        }

    }

    /**
     * Performs the default string processing on the input string
     *
     * @param in Input string
     * @return The processed string
     */
    @Override
    public String process(String in) {

        in = subNonAlphaNumeric(in, " ");
        in = in.toLowerCase();
        in = in.trim();

        return in;

    }

    private static Pattern compilePattern(){

        Pattern p;

        try{
            p = Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS);
        } catch (IllegalArgumentException e) {
            // catch Android specific issue
            p =Pattern.compile(pattern);
        }

        return p;

    }

}
