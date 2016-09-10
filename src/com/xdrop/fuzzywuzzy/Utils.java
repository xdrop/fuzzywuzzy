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

    static <T extends Comparable<? super T>> T max(T ... elems) {

        if (elems.length == 0) return null;

        T best = elems[0];

        for(T t : elems){
            if (t.compareTo(best) > 0) {
                best = t;
            }
        }

        return best;

    }

    static double max(double ... elems) {

        if (elems.length == 0) return 0;

        double best = elems[0];

        for(double t : elems){
            if (t > best) {
                best = t;
            }
        }

        return best;

    }


}
