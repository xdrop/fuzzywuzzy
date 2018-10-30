package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.StringProcessor;

/**
 * @deprecated Use {@code ToStringFunction#DEFAULT} instead.
 */
@Deprecated
public class NoProcess extends StringProcessor {

    @Override
    @Deprecated
    public String process(String in) {
        return in;
    }

}
