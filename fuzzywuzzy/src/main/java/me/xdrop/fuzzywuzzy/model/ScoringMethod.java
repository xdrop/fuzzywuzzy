package me.xdrop.fuzzywuzzy.model;

import me.xdrop.fuzzywuzzy.functions.ScoringFunction;

public interface ScoringMethod {
    ScoringFunction getScoringFunction();
}
