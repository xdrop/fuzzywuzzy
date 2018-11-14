package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.model.ScoringMethod;

public abstract class AlgorithmBase implements Algorithm {
    protected final ScoringMethod defaultScoringMethod;

    public AlgorithmBase(ScoringMethod defaultScoringMethod) {
        super();
        this.defaultScoringMethod = defaultScoringMethod;
    }

    @Override
    public ScoringMethod getDefaultScoringMethod() {
        return defaultScoringMethod;
    }
}
