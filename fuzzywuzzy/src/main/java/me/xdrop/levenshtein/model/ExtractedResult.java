package me.xdrop.levenshtein.model;

public class ExtractedResult<T> implements Comparable<ExtractedResult<T>> {

    private T referent;
    private String string;
    private int score;
    private int index;

    public ExtractedResult(T referent, String string, int score, int index) {
        this.referent = referent;
        this.string = string;
        this.score = score;
        this.index = index;
    }

    @Override
    public int compareTo(ExtractedResult o) {
        return Integer.compare(this.getScore(), o.getScore());
    }

    public T getReferent() {
        return referent;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public int getScore() {
        return score;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "(string: " + string + ", score: " + score + ", index: " + index+ ")";
    }
}