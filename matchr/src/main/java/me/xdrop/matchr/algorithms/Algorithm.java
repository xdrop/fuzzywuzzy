package me.xdrop.matchr.algorithms;

import me.xdrop.matchr.model.ScoringMethod;

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
