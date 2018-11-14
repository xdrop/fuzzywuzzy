package me.xdrop.fuzzywuzzy.rymm;

import me.xdrop.fuzzywuzzy.algorithms.AlgorithmBase;
import me.xdrop.fuzzywuzzy.functions.ScoringFunction;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

public class Rymm extends AlgorithmBase {
    public Rymm() {
        super(Method.INTELLIGENT);
    }

    public enum Method implements ScoringMethod {
        INTELLIGENT(),
        NORMAL();

        @Override
        public Integer apply(String base, String target) {
            return null;
        }
    }
}
