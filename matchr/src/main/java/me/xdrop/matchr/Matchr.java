package me.xdrop.matchr;

import me.xdrop.matchr.algorithms.Algorithm;
import me.xdrop.matchr.algorithms.AlgorithmFactory;

/**
 * Facade class for using Matchr.
 * Use {@link #algorithm(AlgorithmFactory)} to create a usable instance.
 *
 * @param <A> The generic-type of the current algorithm.
 */
public abstract class Matchr<A extends Algorithm> {
    protected final A algorithm;

    /**
     * Protected constructor to be used by implementing types.
     *
     * @param algorithm The algorithm for the instance.
     */
    protected Matchr(A algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Gets the algorithm of the instance.
     *
     * @return The algorithm.
     */
    public A getAlgorithm() {
        return algorithm;
    }


    /**
     * Creates a new Matchr instance using the provided factory.
     *
     * @param factory The factory to use to create a new Matchr implementation.
     * @param <A>     Type-variable of the algorithm.
     * @return A Matchr interface to use the algorithm.
     */
    public static <A extends Algorithm> Extractor<A> algorithm(AlgorithmFactory<A> factory) {
        return new Extractor<>(factory.craft());
    }
}