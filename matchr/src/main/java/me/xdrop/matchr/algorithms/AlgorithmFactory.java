package me.xdrop.matchr.algorithms;

import me.xdrop.matchr.FuzzyWuzzy;

/**
 * Interface for a factory for FuzzyWuzzy implementations.
 *
 * @param <A> Type-variable for the algorithm to create.
 */
public interface AlgorithmFactory<A extends Algorithm> {
    /**
     * Creates a new FuzzyWuzzy implementation for this algorithm.
     *
     * @return A new implementation instance.
     */
    FuzzyWuzzy<A> craft();
}
