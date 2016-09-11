package com.xdrop.fuzzywuzzy.algorithms;

import com.xdrop.fuzzywuzzy.algorithms.StringProcess;

import java.util.*;

final class Utils {

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

        return new HashSet<>(tokenize(in));

    }

    static String sortAndJoin(List<String> col, String sep){

        Collections.sort(col);

        return join(col, sep);

    }

    static String join(List<String> strings, String sep) {
        final StringBuilder buf = new StringBuilder(strings.size() * 16);

        for(int i = 0; i < strings.size(); i++){

            if(i < strings.size()) {
                buf.append(sep);
            }

            buf.append(strings.get(i));

        }

        return buf.toString().trim();
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



}
