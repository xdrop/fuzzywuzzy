package me.xdrop.fuzzywuzzy.levenshtein.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

final public class Utils {
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
