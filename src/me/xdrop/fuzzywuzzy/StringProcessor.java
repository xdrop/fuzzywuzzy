package me.xdrop.fuzzywuzzy;

/**
 * Transforms the input string
 *
 * @deprecated Use {@code ToStringFunction<String>} instead.
 */
@Deprecated
public abstract class StringProcessor implements ToStringFunction<String> {
// now abstract class because JDK1.7 does not allow default methods in interfaces
    /**
     * Transforms the input string
     *
     * @deprecated Use {@code ToStringFunction#apply(String)} instead.
     * @param in Input string
     * @return The processed string
     */
    @Deprecated
    public abstract String process(String in);

    @Override
    public String apply(String item) {
        return process(item);
    }
}
