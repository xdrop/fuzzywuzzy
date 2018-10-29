package me.xdrop.fuzzywuzzy;

import me.xdrop.fuzzywuzzy.algorithms.Utils;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Extractor {

    private int cutoff;

    public Extractor() {
        this.cutoff = 0;
    }

    public Extractor(int cutoff) {
        this.cutoff = cutoff;
    }

    public Extractor with(int cutoff) {
        this.setCutoff(cutoff);
        return this;
    }

    /**
     * Returns the list of choices with their associated scores of similarity in a list
     * of {@link ExtractedResult}
     *
     * @param query The query string
     * @param choices The list of choices
     * @param func The function to apply
     * @return The list of results
     */
    public List<ExtractedResult<String>> extractWithoutOrder(String query, Collection<String> choices,
                                                             Applicable func) {
        return extractWithoutOrder(query, choices, ToStringFunction.DEFAULT, func);
    }

    /**
     * Returns the list of choices with their associated scores of similarity in a list
     * of {@link ExtractedResult}
     *
     * @param query The query string
     * @param choices The list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func The function to apply
     * @return The list of results
     */
    public <T> List<ExtractedResult<T>> extractWithoutOrder(String query, Collection<T> choices,
                                                            ToStringFunction<T> toStringFunction, Applicable func) {

        List<ExtractedResult<T>> yields = new ArrayList<>();
        int index = 0;

        for (T t : choices) {

            String s = toStringFunction.apply(t);
            int score = func.apply(query, s);

            if (score >= cutoff) {
                yields.add(new ExtractedResult<>(t, s, score, index));
            }
            index++;
        }

        return yields;

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query  A string to match against
     * @param choices A list of choices
     * @param func   Scoring function
     * @return An object containing the best match and it's score
     */
    public ExtractedResult<String> extractOne(String query, Collection<String> choices, Applicable func) {
        return extractOne(query, choices, ToStringFunction.DEFAULT, func);
    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query  A string to match against
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func   Scoring function
     * @return An object containing the best match and it's score
     */
    public <T> ExtractedResult<T> extractOne(String query, Collection<T> choices, ToStringFunction<T> toStringFunction,
                                         Applicable func) {

        List<ExtractedResult<T>> extracted = extractWithoutOrder(query, choices, toStringFunction, func);

        return Collections.max(extracted);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult}  which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @return A list of the results
     */
    public List<ExtractedResult<String>> extractTop(String query, Collection<String> choices, Applicable func) {
        return extractTop(query, choices, ToStringFunction.DEFAULT, func);
    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult}  which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @return A list of the results
     */
    public <T> List<ExtractedResult<T>> extractTop(String query, Collection<T> choices,
                                               ToStringFunction<T> toStringFunction, Applicable func) {

        List<ExtractedResult<T>> best = extractWithoutOrder(query, choices, toStringFunction, func);
        Collections.sort(best, Collections.<ExtractedResult<T>>reverseOrder());

        return best;
    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param limit   Limits the number of results and speeds up
     *                the search (k-top heap sort) is used
     * @return A list of the results
     */
    public List<ExtractedResult<String>> extractTop(String query, Collection<String> choices, Applicable func, int limit) {
        return extractTop(query, choices, ToStringFunction.DEFAULT, func, limit);
    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param limit   Limits the number of results and speeds up
     *                the search (k-top heap sort) is used
     * @return A list of the results
     */
    public <T> List<ExtractedResult<T>> extractTop(String query, Collection<T> choices,
                                               ToStringFunction<T> toStringFunction, Applicable func, int limit) {

        List<ExtractedResult<T>> best = extractWithoutOrder(query, choices, toStringFunction, func);

        List<ExtractedResult<T>> results = Utils.findTopKHeap(best, limit);
        Collections.reverse(results);

        return results;
    }

    public int getCutoff() {
        return cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }
}
