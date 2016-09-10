package com.xdrop.fuzzywuzzy;

import org.apache.commons.lang.StringUtils;

import java.util.*;

class Utils {

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
    static String processString(String in, boolean forceAscii) {

        in = StringProcess.subNonAlphaNumeric(in, " ");
        in = in.toLowerCase();
        in = in.trim();

        return in;

    }

    static List<String> tokenize(String in){

        return Arrays.asList(in.split("\\s+"));

    }

    static Set<String> tokenizeSet(String in){

        return new HashSet<String>(tokenize(in));

    }

    static String sortAndJoin(List<String> col, String sep){

        Collections.sort(col);

        return StringUtils.join(col, sep);

    }

    static String sortAndJoin(Set<String> col, String sep){

        return sortAndJoin(new ArrayList<>(col), sep);

    }


}
