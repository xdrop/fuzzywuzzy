package com.xdrop.fuzzywuzzy;

public class Utils {

    /**
     * Process a string by
     *      -- Removing all but letters and numbers
     *      -- Trim whitespace
     *      -- Force to lower case
     *      -- Force to ascii (if true)
     *
     * @param forceAscii Force to ascii (if true)
     * @return The processed string
     */
    public static String processString(String in, boolean forceAscii) {

        in = StringProcess.subNonAlphaNumeric(in, " ");
        in = in.toLowerCase();
        in = in.trim();

        return in;

    }

}
