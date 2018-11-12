package me.xdrop.fuzzywuzzy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import me.xdrop.fuzzywuzzy.algorithms.Algorithm;
import me.xdrop.fuzzywuzzy.algorithms.AlgorithmBase;
import me.xdrop.fuzzywuzzy.algorithms.AlgorithmFactory;
import me.xdrop.fuzzywuzzy.functions.ScoringFunction;
import me.xdrop.fuzzywuzzy.functions.StringMapper;
import me.xdrop.fuzzywuzzy.model.Result;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

public class FuzzyWuzzy<A extends Algorithm> {
    protected final AlgorithmBase algorithm;

    protected FuzzyWuzzy(AlgorithmBase algorithm) {
        this.algorithm = algorithm;
    }

    public <T> Result<T> extractBest(T target, Collection<T> options, StringMapper<T> stringMapper) {
        return extractBest(target, options, stringMapper, algorithm.getDefaultScoringMethod());
    }

    public <T> Result<T> extractBest(T target,
                                     Collection<T> options,
                                     StringMapper<T> stringMapper,
                                     ScoringMethod scoringMethod) {
        return Collections.max(extractAll(target, options, stringMapper, scoringMethod, 0));
    }

    public <T> List<Result<T>> extractAll(T target, Collection<T> options, StringMapper<T> stringMapper) {
        return extractAll(target, options, stringMapper, algorithm.getDefaultScoringMethod(), 0);
    }

    public <T> List<Result<T>> extractAll(T target, Collection<T> options, StringMapper<T> stringMapper, int threshold) {
        return extractAll(target, options, stringMapper, algorithm.getDefaultScoringMethod(), threshold);
    }

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

    public static <A extends Algorithm> FuzzyWuzzy<A> algorithm(AlgorithmFactory<A> of) {
        return of.craft();
    }
}