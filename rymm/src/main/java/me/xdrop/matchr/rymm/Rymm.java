package me.xdrop.matchr.rymm;

import me.xdrop.matchr.Matchr;
import me.xdrop.matchr.algorithms.AlgorithmBase;
import me.xdrop.matchr.algorithms.AlgorithmFactory;
import me.xdrop.matchr.functions.ScoringFunction;
import me.xdrop.matchr.model.ScoringMethod;
import me.xdrop.matchr.rymm.algorithms.EditDistance;

public class Rymm extends AlgorithmBase {
    public final static AlgorithmFactory<Rymm> FACTORY = new Factory();

    private Rymm() {
        super(Method.PARTIAL_MATCH);
    }

    public enum Method implements ScoringMethod {
        PARTIAL_MATCH(new EditDistance(true)),
        COMPLETE_MATCH(new EditDistance(false));

        private final ScoringFunction scoringFunction;

        Method(ScoringFunction scoringFunction) {
            this.scoringFunction = scoringFunction;
        }

        @Override
        public Integer apply(String base, String target) {
            return scoringFunction.apply(base, target);
        }
    }

    private static class Engine extends Matchr<Rymm> {
        protected Engine(Rymm algorithm) {
            super(algorithm);
        }
    }

    private static class Factory implements AlgorithmFactory<Rymm> {
        @Override
        public Engine craft() {
            return new Engine(new Rymm());
        }
    }
}
