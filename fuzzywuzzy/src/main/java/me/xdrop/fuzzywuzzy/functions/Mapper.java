package me.xdrop.fuzzywuzzy.functions;

public interface Mapper<T, R> {
    /**
     * Transforms the input item to a string.
     *
     * @param t The item to transform.
     * @return A string to use for comparing the item.
     */
    R apply(T t);
}
