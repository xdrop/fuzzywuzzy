package me.xdrop.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import me.xdrop.fuzzywuzzy.algorithms.Algorithm;
import me.xdrop.fuzzywuzzy.algorithms.AlgorithmFactory;
import me.xdrop.fuzzywuzzy.functions.ScoringFunction;
import me.xdrop.fuzzywuzzy.functions.StringMapper;
import me.xdrop.fuzzywuzzy.model.Result;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

/**
 * Facade class for using FuzzyWuzzy.
 * Use {@link #algorithm(AlgorithmFactory)} to create a usable instance.
 *
 * @param <A> The generic-type of the current algorithm.
 */
public abstract class FuzzyWuzzy<A extends Algorithm> {
    protected final A algorithm;

    /**
     * Protected constructor to be used by implementing types.
     *
     * @param algorithm The algorithm for the instance.
     */
    protected FuzzyWuzzy(A algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Gets the algorithm of the instance.
     *
     * @return The algorithm.
     */
    public A getAlgorithm() {
        return algorithm;
    }

    /**
     * Extracts the best result from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} to determin the scores of the elements.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     */
    public <T> Result<T> extractBest(T target, Collection<T> options, StringMapper<T> stringMapper) {
        return extractBest(target, options, stringMapper, algorithm.getDefaultScoringMethod());
    }

    /**
     * Extracts the best result from the provided list, using the provided scoringMethod to determine the scores
     * of the elements.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param scoringMethod The method to use for scoring between two strings.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     */
    public <T> Result<T> extractBest(T target,
                                     Collection<T> options,
                                     StringMapper<T> stringMapper,
                                     ScoringMethod scoringMethod) {
        // add a custom comparator because by default, results compare in "reversed" order; meaning
        // Collections.max would actually return the lowest score result
        return Collections.max(extractAllSorted(target, options, stringMapper, scoringMethod, 0),
                new Comparator<Result<T>>() {
            @Override
            public int compare(Result<T> o1, Result<T> o2) {
                return Integer.compare(o2.getScore(), o1.getScore());
            }
        });
    }

    /**
     * Extracts a limited amount of the best results from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} defined by the algorithm to determine
     * the scores of the results.
     * Uses a default threshold of 0, thus; every item gets included.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param limit The maximum amount of items to include in the output.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     * @throws IllegalArgumentException If the provided limit is smaller than 1.
     * @see #extractLimited(Object, Collection, StringMapper, ScoringMethod, int, int)
     */
    public <T> List<Result<T>> extractLimited(T target,
                                              Collection<T> options,
                                              StringMapper<T> stringMapper,
                                              int limit) throws IllegalArgumentException {
        return extractLimited(target, options, stringMapper, algorithm.getDefaultScoringMethod(), 0, limit);
    }

    /**
     * Extracts a limited amount of the best results from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} defined by the algorithm to determine
     * the scores of the results.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param threshold The minimum score of an element to be included in the output list.
     * @param limit The maximum amount of items to include in the output.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     * @throws IllegalArgumentException If the provided limit is smaller than 1.
     * @see #extractLimited(Object, Collection, StringMapper, ScoringMethod, int, int)
     */
    public <T> List<Result<T>> extractLimited(T target,
                                              Collection<T> options,
                                              StringMapper<T> stringMapper,
                                              int threshold,
                                              int limit) throws IllegalArgumentException {
        return extractLimited(target, options, stringMapper, algorithm.getDefaultScoringMethod(), threshold, limit);
    }

    /**
     * Extracts a limited amount of the best results from the provided list, using the provided scoringMethod to
     * determine their scores.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param scoringMethod The method to use for scoring between two strings.
     * @param threshold The minimum score of an element to be included in the output list.
     * @param limit The maximum amount of items to include in the output.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     * @throws IllegalArgumentException If the provided limit is smaller than 1.
     */
    public <T> List<Result<T>> extractLimited(T target,
                                              Collection<T> options,
                                              StringMapper<T> stringMapper,
                                              ScoringMethod scoringMethod,
                                              int threshold,
                                              int limit) throws IllegalArgumentException {
        if (limit < 1) throw new IllegalArgumentException("Parameter 'limit' must not be smaller than '1'!");
        List<Result<T>> preResults = extractAll(target, options, stringMapper, scoringMethod, threshold);
        return Util.extractLimitedKHeap(preResults, limit);
    }


    /**
     * Extracts all results from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} defined by the algorithm,
     * then sorts the results.
     * Uses a default threshold of 0, thus; every item gets included.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     * @see #extractAllSorted(Object, Collection, StringMapper, ScoringMethod, int)
     */
    public <T> List<Result<T>> extractAllSorted(T target,
                                                Collection<T> options,
                                                StringMapper<T> stringMapper) {
        return extractAllSorted(target, options, stringMapper, algorithm.getDefaultScoringMethod(), 0);
    }


    /**
     * Extracts all results from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} defined by the algorithm,
     * then sorts the results.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param threshold The minimum score of an element to be included in the output list.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     * @see #extractAllSorted(Object, Collection, StringMapper, ScoringMethod, int)
     */
    public <T> List<Result<T>> extractAllSorted(T target,
                                                Collection<T> options,
                                                StringMapper<T> stringMapper,
                                                int threshold) {
        return extractAllSorted(target, options, stringMapper, algorithm.getDefaultScoringMethod(), threshold);
    }

    /**
     * Extracts all results from the provided list, using the provided scoringMethod to determine their scores,
     * and sorts the results.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param scoringMethod The method to use for scoring between two strings.
     * @param threshold The minimum score of an element to be included in the output list.
     * @param <T> Type-variable of the elements.
     * @return A sorted list of all Results whose score is higher than or equal the given threshold.
     */
    public <T> List<Result<T>> extractAllSorted(T target,
                                                Collection<T> options,
                                                StringMapper<T> stringMapper,
                                                ScoringMethod scoringMethod,
                                                int threshold) {
        List<Result<T>> results = extractAll(target, options, stringMapper, scoringMethod, threshold);
        Collections.sort(results);
        return results;
    }

    /**
     * Extracts all results from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} defined by the algorithm.
     * Uses a default threshold of 0, thus; every item gets included.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param <T> Type-variable of the elements.
     * @return An unsorted list of all Results whose score is higher than or equal the given threshold.
     * @see #extractAll(Object, Collection, StringMapper, ScoringMethod, int)
     */
    public <T> List<Result<T>> extractAll(T target, Collection<T> options, StringMapper<T> stringMapper) {
        return extractAll(target, options, stringMapper, algorithm.getDefaultScoringMethod(), 0);
    }

    /**
     * Extracts all results from the provided list, using the
     * {@linkplain Algorithm#getDefaultScoringMethod() default scoring method} defined by the algorithm.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param threshold The minimum score of an element to be included in the output list.
     * @param <T> Type-variable of the elements.
     * @return An unsorted list of all Results whose score is higher than or equal the given threshold.
     * @see #extractAll(Object, Collection, StringMapper, ScoringMethod, int)
     */
    public <T> List<Result<T>> extractAll(T target,
                                          Collection<T> options,
                                          StringMapper<T> stringMapper,
                                          int threshold) {
        return extractAll(target, options, stringMapper, algorithm.getDefaultScoringMethod(), threshold);
    }

    /**
     * Extracts all results from the provided list, using the provided scoringMethod to determine their scores.
     *
     * @param target The target (key) element. This will be the criteria.
     * @param options A list of elements to look for the target in.
     * @param stringMapper A mapper to convert the elements into string representations that can be compared.
     * @param scoringMethod The method to use for scoring between two strings.
     * @param threshold The minimum score of an element to be included in the output list.
     * @param <T> Type-variable of the elements.
     * @return An unsorted list of all Results whose score is higher than or equal the given threshold.
     */
    public <T> List<Result<T>> extractAll(T target,
                                          Collection<T> options,
                                          StringMapper<T> stringMapper,
                                          ScoringMethod scoringMethod,
                                          int threshold) {
        final List<Result<T>> yields = new ArrayList<>();
        final ScoringFunction scoringFunction = scoringMethod.getScoringFunction();
        final String targetString = stringMapper.apply(target);
        int originIndex = 0;

        for (T t : options) {
            final String s = stringMapper.apply(t);
            final int score = scoringFunction.apply(s, targetString);

            if (score >= threshold) yields.add(new Result<>(t, s, score, originIndex));
            originIndex++;
        }

        return yields;
    }

    /**
     * Creates a new FuzzyWuzzy instance using the provided factory.
     *
     * @param factory The factory to use to create a new FuzzyWuzzy implementation.
     * @param <A> Type-variable of the algorithm.
     * @return A FuzzyWuzzy interface to use the algorithm.
     */
    public static <A extends Algorithm> FuzzyWuzzy<A> algorithm(AlgorithmFactory<A> factory) {
        return factory.craft();
    }
}