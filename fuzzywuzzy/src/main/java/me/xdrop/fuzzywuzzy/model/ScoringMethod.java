package me.xdrop.fuzzywuzzy.model;

import me.xdrop.fuzzywuzzy.functions.ScoringFunction;

/**
 * Interface for scoring methods.
 */
public interface ScoringMethod {
    /**
     * Gets the scoring function for this method.
     *
     * @return The scoring function.
     */
    ScoringFunction getScoringFunction();
}
