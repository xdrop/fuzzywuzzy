package me.xdrop.matchr.functions;

/**
 * Used gather the score of a String against another String.
 */
public interface ScoringFunction {
    /**
     * The method to score with.
     * @param base The first string to compare.
     * @param target The second string to compare against.
     * @return The score of the {@code base} string.
     */
    int apply(String base, String target);
}
