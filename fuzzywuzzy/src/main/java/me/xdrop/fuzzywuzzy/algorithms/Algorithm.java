package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.model.ScoringMethod;

/**
 * Interface for algorithms.
 */
public interface Algorithm {
    /**
     * Returns the default scoring method for this algorithm.
     *
     * @return The default scoring method.
     */
    ScoringMethod getDefaultScoringMethod();
}
