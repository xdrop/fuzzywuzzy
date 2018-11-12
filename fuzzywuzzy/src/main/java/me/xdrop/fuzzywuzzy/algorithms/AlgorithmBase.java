package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.functions.StringMapper;
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

    @Override
    public int score(String base, String target, ScoringMethod method) {
        return method.getScoringFunction().apply(base, target);
    }

    @Override
    public <T> int score(T base, T target, StringMapper<T> stringMapper, ScoringMethod method) {
        return score(stringMapper.apply(base), stringMapper.apply(target), method);
    }
}
