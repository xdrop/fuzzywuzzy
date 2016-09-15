package me.xdrop.fuzzywuzzy.model;

public class ExtractedResult implements Comparable<ExtractedResult> {

    private String string;
    private int score;

    public ExtractedResult(String string, int score) {
        this.string = string;
        this.score = score;
    }

    @Override
    public int compareTo(ExtractedResult o) {
        return Integer.compare(this.getScore(), o.getScore());
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

    @Override
    public String toString() {
        return "(string: " + string + ", score:" + score + ")";
    }
}