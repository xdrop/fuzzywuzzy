package me.xdrop.matchr.functions;

import java.util.Collection;
import java.util.List;
import me.xdrop.matchr.Matchr;
import me.xdrop.matchr.android.FuzzyDroid;
import me.xdrop.matchr.model.Result;

/**
 * An interface for using the {@link FuzzyDroid} functionality.
 * A variety of predefined ResultProviders can be found in {@link ResultProviders}.
 *
 * @param <T> Type of the returning results.
 * @see ResultProviders
 */
public interface ResultProvider<T> {
    /**
     * Interface method for {@linkplain Matchr Matchr-Search} methods.
     * This method should call a method of any Matchr implementation.
     *
     * @param target  The target string.
     * @param options The possible options.
     * @return A list of results.
     * @see Matchr
     */
    List<Result<T>> fetch(String target, Collection<T> options);
}
