package me.xdrop.fuzzywuzzy.functions;

public interface Function<T, R> {
    /**
     * Transforms the input item to a string.
     *
     * @param item The item to transform.
     * @return A string to use for comparing the item.
     */
    R apply(T item);
}
