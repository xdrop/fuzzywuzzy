package me.xdrop.fuzzywuzzy;

/**
 * Transforms the input string
 */
public interface StringProcessor {

    /**
     * Transforms the input string
     *
     * @param in Input string
     * @return The processed string
     */
    String process(String in);

}
