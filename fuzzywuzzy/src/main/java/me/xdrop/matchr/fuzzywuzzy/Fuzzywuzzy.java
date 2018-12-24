package me.xdrop.matchr.fuzzywuzzy;

import me.xdrop.matchr.Matchr;
import me.xdrop.matchr.algorithms.AlgorithmBase;
import me.xdrop.matchr.algorithms.AlgorithmFactory;
import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.fuzzywuzzy.algorithms.RatioAlgorithm;
import me.xdrop.matchr.fuzzywuzzy.algorithms.TokenSet;
import me.xdrop.matchr.fuzzywuzzy.algorithms.TokenSort;
import me.xdrop.matchr.fuzzywuzzy.algorithms.WeightedRatio;
import me.xdrop.matchr.fuzzywuzzy.ratios.PartialRatio;
import me.xdrop.matchr.fuzzywuzzy.ratios.SimpleRatio;
import me.xdrop.matchr.model.ScoringMethod;

import java.util.ArrayList;

/**
 * Fuzzywuzzy algorithm implementation.
 */
public class Fuzzywuzzy extends AlgorithmBase {
    /**
     * Factory for the Fuzzywuzzy Algorithm
     */
    public final static AlgorithmFactory<Fuzzywuzzy> FACTORY = new Factory();

    /**
     * Private constructor; used by {@link Factory}.
     * Defines default scoring method as {@link Ratio#SIMPLE_RATIO}
     */
    private Fuzzywuzzy() {
        super(Ratio.WEIGHTED_RATIO);
    }

    /**
     * Predefined Fuzzywuzzy token based scoring methods.
     */
    public enum TokenRatio implements ScoringMethod {
        TOKEN_SORT_SIMPLE(new TokenSort(), Ratio.SIMPLE_RATIO),
        TOKEN_SET_SIMPLE(new TokenSet(), Ratio.SIMPLE_RATIO),
        TOKEN_SORT_PARTIAL(new TokenSort(), Ratio.PARTIAL_RATIO),
        TOKEN_SET_PARTIAL(new TokenSet(), Ratio.PARTIAL_RATIO);

        private final RatioAlgorithm r;
        private final ScoringFunction f;

        TokenRatio(RatioAlgorithm r, ScoringFunction f) {
            this.r = r;
            this.f = f;
        }

        @Override
        public int apply(String base, String target) {
            return r.apply(base, target, f);
        }
    }

    /**
     * Predefined Fuzzywuzzy scoring methods.
     */
    public enum Ratio implements ScoringMethod {
        SIMPLE_RATIO(new SimpleRatio()),
        PARTIAL_RATIO(new PartialRatio()),
        WEIGHTED_RATIO(new WeightedRatio());

        private final ScoringFunction scoringFunction;

        Ratio(ScoringFunction scoringFunction) {
            this.scoringFunction = scoringFunction;
        }

        @Override
        public int apply(String base, String target) {
            return scoringFunction.apply(base, target);
        }
    }

    /**
     * Factory implementation for Fuzzywuzzy algorithm
     */
    private static class Factory implements AlgorithmFactory<Fuzzywuzzy> {
        /**
         * Private constructor, used for instantiating the {@code factory} field.
         */
        private Factory() {
            super();
        }

        @Override
        public Fuzzywuzzy craft() {
            return new Fuzzywuzzy();
        }
    }
}
