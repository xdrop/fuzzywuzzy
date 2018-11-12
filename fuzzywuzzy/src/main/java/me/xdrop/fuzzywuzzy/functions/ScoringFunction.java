package me.xdrop.fuzzywuzzy.functions;

/**
 * Used gather the score of a String against another String.
 */
public interface ScoringFunction extends Comparison<String, Integer> {
    /**
     * The method to score with.
     * @param base The first string to compare.
     * @param target The second string to compare against.
     * @return The score of the {@code base} string.
     */
    @Override
    Integer apply(String base, String target);
}
