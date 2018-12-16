package me.xdrop.matchr.rymm.model;

import me.xdrop.matchr.model.Result;

public class RymmResult<T> extends Result<T> {
    public RymmResult(T referent, String string, int score, int originIndex) throws IllegalArgumentException {
        super(referent, string, score, originIndex);
    }

    public RymmResult(T referent, String string, int score) throws IllegalArgumentException {
        super(referent, string, score);
    }
}
