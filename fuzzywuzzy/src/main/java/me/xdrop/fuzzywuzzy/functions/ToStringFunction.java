package me.xdrop.fuzzywuzzy.functions;

/**
 * Transforms an item of type T to a String.
 *
 * @param <T> The type of the item to transform.
 */
public interface ToStringFunction<T> extends Function<T, String> {
    /**
     * Transforms the input item to a string.
     *
     * @param item The item to transform.
     * @return A string to use for comparing the item.
     */
    String apply(T item);

    /**
     * A default ToStringFunction that returns the input string.
     */
    ToStringFunction<String> IDENTITY = new ToStringFunction<String>() {
        @Override
        public String apply(String item) {
            return item;
        }
    };
}
