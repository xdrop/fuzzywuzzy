package me.xdrop.fuzzywuzzy.functions;

import java.util.Collection;
import java.util.List;
import me.xdrop.fuzzywuzzy.FuzzyWuzzy;
import me.xdrop.fuzzywuzzy.android.FuzzyDroid;
import me.xdrop.fuzzywuzzy.model.Result;

/**
 * An interface for using the {@link FuzzyDroid} functionality.
 * A variety of predefined ResultProviders can be found in {@link ResultProviders}.
 *
 * @param <T> Type of the returning results.
 * @see ResultProviders
 */
public interface ResultProvider<T> {
    /**
     * Interface method for {@linkplain FuzzyWuzzy FuzzyWuzzy-Search} methods.
     * This method should call a method of any FuzzyWuzzy implementation.
     *
     * @param target  The target string.
     * @param options The possible options.
     * @return A list of results.
     * @see FuzzyWuzzy
     */
    List<Result<T>> fetch(String target, Collection<T> options);
}
