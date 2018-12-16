package me.xdrop.matchr.functions;

/**
 * Transforms T into R.
 *
 * @param <T> Type-variable for the input.
 * @param <R> Type-variable for the output.
 */
public interface Mapper<T, R> {
    /**
     * Transforms the input item to a string.
     *
     * @param t The item to transform.
     * @return A string to use for comparing the item.
     */
    R apply(T t);
}
