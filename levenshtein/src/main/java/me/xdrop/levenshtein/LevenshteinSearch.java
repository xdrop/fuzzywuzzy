package me.xdrop.levenshtein;

import me.xdrop.levenshtein.algorithms.TokenSet;
import me.xdrop.levenshtein.algorithms.TokenSort;
import me.xdrop.levenshtein.algorithms.WeightedRatio;
import me.xdrop.levenshtein.model.ExtractedResult;
import me.xdrop.levenshtein.ratios.PartialRatio;
import me.xdrop.levenshtein.ratios.SimpleRatio;

import java.util.Collection;
import java.util.List;


/**
 * LevenshteinSearch facade class
 */
public class LevenshteinSearch {


    /**
     * Calculates a Levenshtein simple ratio between the strings.
     * This is indicates a measure of similarity
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The simple ratio
     */
    public static int ratio(String s1, String s2) {

        return new SimpleRatio().apply(s1, s2);

    }

    /**
     * Calculates a Levenshtein simple ratio between the strings.
     * This is indicates a measure of similarity
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The simple ratio
     */
    public static int ratio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new SimpleRatio().apply(s1, s2, stringFunction);

    }

    /**
     * Inconsistent substrings lead to problems in matching. This ratio
     * uses a heuristic called "best partial" for when two strings
     * are of noticeably different lengths.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio
     */
    public static int partialRatio(String s1, String s2) {

        return new PartialRatio().apply(s1, s2);

    }

    /**
     * Inconsistent substrings lead to problems in matching. This ratio
     * uses a heuristic called "best partial" for when two strings
     * are of noticeably different lengths.
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The partial ratio
     */
    public static int partialRatio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new PartialRatio().apply(s1, s2, stringFunction);

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The partial ratio of the strings
     */
    public static int tokenSortPartialRatio(String s1, String s2) {

        return new TokenSort().apply(s1, s2, new PartialRatio());

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The partial ratio of the strings
     */
    public static int tokenSortPartialRatio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new TokenSort().apply(s1, s2, new PartialRatio(), stringFunction);

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The full ratio of the strings
     */
    public static int tokenSortRatio(String s1, String s2) {

        return new TokenSort().apply(s1, s2, new SimpleRatio());

    }

    /**
     * Find all alphanumeric tokens in the string and sort
     * those tokens and then take ratio of resulting
     * joined strings.
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The full ratio of the strings
     */
    public static int tokenSortRatio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new TokenSort().apply(s1, s2, new SimpleRatio(), stringFunction);

    }


    /**
     * Splits the strings into tokens and computes intersections and remainders
     * between the tokens of the two strings. A comparison string is then
     * built up and is compared using the simple ratio algorithm.
     * Useful for strings where words appear redundantly.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The ratio of similarity
     */
    public static int tokenSetRatio(String s1, String s2) {

        return new TokenSet().apply(s1, s2, new SimpleRatio());

    }

    /**
     * Splits the strings into tokens and computes intersections and remainders
     * between the tokens of the two strings. A comparison string is then
     * built up and is compared using the simple ratio algorithm.
     * Useful for strings where words appear redundantly.
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The ratio of similarity
     */
    public static int tokenSetRatio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new TokenSet().apply(s1, s2, new SimpleRatio(), stringFunction);

    }

    /**
     * Splits the strings into tokens and computes intersections and remainders
     * between the tokens of the two strings. A comparison string is then
     * built up and is compared using the simple ratio algorithm.
     * Useful for strings where words appear redundantly.
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The ratio of similarity
     */
    public static int tokenSetPartialRatio(String s1, String s2) {

        return new TokenSet().apply(s1, s2, new PartialRatio());

    }

    /**
     * Splits the strings into tokens and computes intersections and remainders
     * between the tokens of the two strings. A comparison string is then
     * built up and is compared using the simple ratio algorithm.
     * Useful for strings where words appear redundantly.
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The ratio of similarity
     */
    public static int tokenSetPartialRatio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new TokenSet().apply(s1, s2, new PartialRatio(), stringFunction);

    }

    /**
     * Calculates a weighted ratio between the different algorithms for best results
     *
     * @param s1 Input string
     * @param s2 Input string
     * @return The ratio of similarity
     */
    public static int weightedRatio(String s1, String s2) {

        return new WeightedRatio().apply(s1, s2);

    }

    /**
     * Calculates a weighted ratio between the different algorithms for best results
     *
     * @param s1              Input string
     * @param s2              Input string
     * @param stringFunction Functor which transforms strings before
     *                        calculating the ratio
     * @return The ratio of similarity
     */
    public static int weightedRatio(String s1, String s2, ToStringFunction<String> stringFunction) {

        return new WeightedRatio().apply(s1, s2, stringFunction);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult}  which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param limit   Limits the number of results and speeds up
     *                the search (k-top heap sort) is used
     * @param cutoff  Rejects any entries with score below this
     * @param func    The scoring function
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractTop(String query, Collection<String> choices,
                                                           Applicable func, int limit, int cutoff) {

        Extractor extractor = new Extractor(cutoff);
        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, func, limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param limit   Limits the number of results and speeds up
     *                the search (k-top heap sort) is used
     * @param cutoff  Rejects any entries with score below this
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractTop(String query, Collection<String> choices,
                                                           int limit, int cutoff) {

        Extractor extractor = new Extractor(cutoff);
        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, new WeightedRatio(), limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @param limit   The number of results to return
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractTop(String query, Collection<String> choices,
                                                           Applicable func, int limit) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, func, limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param limit   The number of results to return
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractTop(String query, Collection<String> choices,
                                                           int limit) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, new WeightedRatio(), limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractSorted(String query, Collection<String> choices, Applicable func) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, func);

    }


    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @param cutoff  Keep only scores above cutoff
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractSorted(String query, Collection<String> choices, Applicable func,
                                                              int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, func);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractSorted(String query, Collection<String> choices) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, new WeightedRatio());

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param cutoff  Keep only scores above cutoff
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractSorted(String query, Collection<String> choices,
                                                              int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractTop(query, choices, ToStringFunction.IDENTITY, new WeightedRatio());

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractAll(String query, Collection<String> choices, Applicable func) {

        Extractor extractor = new Extractor();

        return extractor.extractWithoutOrder(query, choices, ToStringFunction.IDENTITY, func);

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param func    The scoring function
     * @param cutoff  Keep only scores above cutoff
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractAll(String query, Collection<String> choices, Applicable func,
                                                           int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractWithoutOrder(query, choices, ToStringFunction.IDENTITY, func);

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractAll(String query, Collection<String> choices) {

        Extractor extractor = new Extractor();

        return extractor.extractWithoutOrder(query, choices, ToStringFunction.IDENTITY, new WeightedRatio());

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param cutoff  Keep only scores above cutoff
     * @return A list of the results
     */
    public static List<ExtractedResult<String>> extractAll(String query, Collection<String> choices, int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractWithoutOrder(query, choices, ToStringFunction.IDENTITY, new WeightedRatio());

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query   A string to match against
     * @param choices A list of choices
     * @param func    Scoring function
     * @return An object containing the best match and it's score
     */
    public static ExtractedResult<String> extractOne(String query, Collection<String> choices, Applicable func) {

        Extractor extractor = new Extractor();

        return extractor.extractOne(query, choices, ToStringFunction.IDENTITY, func);

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query   A string to match against
     * @param choices A list of choices
     * @return An object containing the best match and it's score
     */
    public static ExtractedResult<String> extractOne(String query, Collection<String> choices) {

        Extractor extractor = new Extractor();

        return extractor.extractOne(query, choices, ToStringFunction.IDENTITY, new WeightedRatio());

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult}  which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @param limit   Limits the number of results and speeds up
     *                the search (k-top heap sort) is used
     * @param cutoff  Rejects any entries with score below this
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractTop(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, Applicable func,
                                                          int limit, int cutoff) {

        Extractor extractor = new Extractor(cutoff);
        return extractor.extractTop(query, choices, toStringFunction, func, limit);

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
     * @param cutoff  Rejects any entries with score below this
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractTop(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, int limit, int cutoff) {

        Extractor extractor = new Extractor(cutoff);
        return extractor.extractTop(query, choices, toStringFunction, new WeightedRatio(), limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @param limit   The number of results to return
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractTop(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, Applicable func,
                                                          int limit) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, toStringFunction, func, limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain the
     * top @param limit most similar choices
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param limit   The number of results to return
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractTop(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, int limit) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, toStringFunction, new WeightedRatio(), limit);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractSorted(String query, Collection<T> choices,
                                                             ToStringFunction<T> toStringFunction, Applicable func) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, toStringFunction, func);

    }


    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @param cutoff  Keep only scores above cutoff
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractSorted(String query, Collection<T> choices,
                                                             ToStringFunction<T> toStringFunction, Applicable func,
                                                             int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractTop(query, choices, toStringFunction, func);

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractSorted(String query, Collection<T> choices,
                                                             ToStringFunction<T> toStringFunction) {

        Extractor extractor = new Extractor();

        return extractor.extractTop(query, choices, toStringFunction, new WeightedRatio());

    }

    /**
     * Creates a <b>sorted</b> list of {@link ExtractedResult} which contain all the choices
     * with their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param cutoff  Keep only scores above cutoff
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractSorted(String query, Collection<T> choices,
                                                             ToStringFunction<T> toStringFunction, int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractTop(query, choices, toStringFunction, new WeightedRatio());

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractAll(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, Applicable func) {

        Extractor extractor = new Extractor();

        return extractor.extractWithoutOrder(query, choices, toStringFunction, func);

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    The scoring function
     * @param cutoff  Keep only scores above cutoff
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractAll(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, Applicable func,
                                                          int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractWithoutOrder(query, choices, toStringFunction, func);

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractAll(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction) {

        Extractor extractor = new Extractor();

        return extractor.extractWithoutOrder(query, choices, toStringFunction, new WeightedRatio());

    }

    /**
     * Creates a list of {@link ExtractedResult} which contain all the choices with
     * their corresponding score where higher is more similar
     *
     * @param query   The query string
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param cutoff  Keep only scores above cutoff
     * @param <T> Type-variable for the referents.
     * @return A list of the results
     */
    public static <T> List<ExtractedResult<T>> extractAll(String query, Collection<T> choices,
                                                          ToStringFunction<T> toStringFunction, int cutoff) {

        Extractor extractor = new Extractor(cutoff);

        return extractor.extractWithoutOrder(query, choices, toStringFunction, new WeightedRatio());

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query   A string to match against
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param func    Scoring function
     * @param <T> Type-variable for the referents.
     * @return An object containing the best match and it's score
     */
    public static <T> ExtractedResult<T> extractOne(String query, Collection<T> choices,
                                                    ToStringFunction<T> toStringFunction, Applicable func) {

        Extractor extractor = new Extractor();

        return extractor.extractOne(query, choices, toStringFunction, func);

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query   A string to match against
     * @param choices A list of choices
     * @param toStringFunction The ToStringFunction to be applied to all choices.
     * @param <T> Type-variable for the referents.
     * @return An object containing the best match and it's score
     */
    public static <T> ExtractedResult<T> extractOne(String query, Collection<T> choices,
                                                    ToStringFunction<T> toStringFunction) {

        Extractor extractor = new Extractor();

        return extractor.extractOne(query, choices, toStringFunction, new WeightedRatio());

    }


}
