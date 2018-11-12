package me.xdrop.fuzzywuzzy.functions;

/**
 * Compares two items.
 *
 * @param <T> The type variable of the things to compare.
 * @param <R> The comparison output.
 */
public interface Comparison<T, R> {
    /**
     * Compares two instances of T.
     *
     * @param base The base line instance.
     * @param target The target.
     * @return The result of the comparison.
     */
    R apply(T base, T target);
}
