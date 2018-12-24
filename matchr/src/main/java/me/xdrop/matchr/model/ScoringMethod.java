package me.xdrop.matchr.model;

import me.xdrop.matchr.functions.ScoringFunction;

/**
 * Interface for scoring methods.
 */
public interface ScoringMethod extends ScoringFunction {
    /**
     * Calls the {@link ScoringFunction#apply(String, String)} method for the function.
     *
     * @param base The first string to compare.
     * @param target The second string to compare against.
     * @return The score of the {@code base} string.
     */
    Integer apply(String base, String target);
}
