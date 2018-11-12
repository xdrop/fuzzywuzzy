package me.xdrop.fuzzywuzzy.algorithms;

import me.xdrop.fuzzywuzzy.FuzzyWuzzy;

public interface AlgorithmFactory<A extends Algorithm> {
    FuzzyWuzzy<A> craft();
}
