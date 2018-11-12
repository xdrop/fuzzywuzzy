package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.functions.StringMapper;
import me.xdrop.fuzzywuzzy.model.ScoringMethod;

public interface Algorithm {
    ScoringMethod getDefaultScoringMethod();

    int score(String base, String target, ScoringMethod method);

    <T> int score(T base, T target, StringMapper<T> stringMapper, ScoringMethod method);
}
