package me.xdrop.fuzzywuzzy;

/**
 * Interface for the different ratios
 */
public interface Ratio extends Applicable {

    /**
     * Applies the ratio between the two strings
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return Integer representing ratio of similarity
     */
    int apply(String s1, String s2);

}
