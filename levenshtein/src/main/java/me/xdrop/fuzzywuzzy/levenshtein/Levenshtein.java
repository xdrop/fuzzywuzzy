package me.xdrop.fuzzywuzzy.levenshtein;

import me.xdrop.fuzzywuzzy.FuzzyWuzzy;
import me.xdrop.fuzzywuzzy.algorithms.AlgorithmBase;
import me.xdrop.fuzzywuzzy.algorithms.AlgorithmFactory;
import me.xdrop.fuzzywuzzy.functions.ScoringFunction;
import me.xdrop.fuzzywuzzy.levenshtein.algorithms.TokenSet;
import me.xdrop.fuzzywuzzy.levenshtein.algorithms.TokenSort;
import me.xdrop.fuzzywuzzy.levenshtein.algorithms.WeightedRatio;
import me.xdrop.fuzzywuzzy.levenshtein.ratios.PartialRatio;
import me.xdrop.fuzzywuzzy.levenshtein.ratios.SimpleRatio;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

/**
 * Levenshtein algorithm implementation.
 */
public class Levenshtein extends AlgorithmBase {
    /**
     * Factory for the Levenshtein Algorithm
     */
    public final static AlgorithmFactory<Levenshtein> factory = new Factory();

    /**
     * Private constructor; used by {@link Factory}.
     * Defines default scoring method as {@link Method#SIMPLE_RATIO}
     */
    private Levenshtein() {
        super(Method.SIMPLE_RATIO);
    }

    /**
     * Predefined Levenshtein scoring methods.
     */
    public enum Method implements ScoringMethod {
        SIMPLE_RATIO(new SimpleRatio()),
        PARTIAL_RATIO(new PartialRatio()),
        WEIGHTED_RATIO(new WeightedRatio()),
        TOKEN_SORT(new TokenSort()),
        TOKEN_SET(new TokenSet());

        private final ScoringFunction scoringFunction;

        <F extends ScoringFunction> Method(F scoringFunction) {
            this.scoringFunction = scoringFunction;
        }

        @Override
        public ScoringFunction getScoringFunction() {
            return scoringFunction;
        }
    }

    /**
     * Engine implementation for Levenshtein algorithm
     */
    private static class Engine extends FuzzyWuzzy<Levenshtein> {
        /**
         * Private contructor, used by {@link Factory}.
         *
         * @param algo The algorithm to be stored.
         */
        private Engine(Levenshtein algo) {
            super(algo);
        }
    }

    /**
     * Factory implementation for Levenshtein algorithm
     */
    private static class Factory implements AlgorithmFactory<Levenshtein> {
        /**
         * Private constructor, used for instantiating the {@code factory} field.
         */
        private Factory() {
            super();
        }

        @Override
        public FuzzyWuzzy<Levenshtein> craft() {
            return new Engine(new Levenshtein());
        }
    }
}
