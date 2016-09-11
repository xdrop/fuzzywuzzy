package com.xdrop.fuzzywuzzy;

import com.xdrop.fuzzywuzzy.algorithms.Utils;
import com.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Extractor {

    private int cutoff;

    public Extractor() {
        this.cutoff = 0;
    }

    public Extractor(int cutoff) {
        this.cutoff = cutoff;
    }

    public Extractor with(int cutoff) {
        this.setCutoff(cutoff);
        return this;
    }


    public List<ExtractedResult> extractWithoutOrder(String query, Collection<String> choices, Applicable func) {

        List<ExtractedResult> yields = new ArrayList<>();

        for (String s : choices) {

            int score = func.apply(query, s);

            if (score >= cutoff) {
                yields.add(new ExtractedResult(s, score));
            }

        }

        return yields;

    }

    /**
     * Find the single best match above a score in a list of choices.
     *
     * @param query A string to match against
     * @param choice A list of choices
     * @param func Scoring function
     * @return An object containing the best match and it's score
     */
    public ExtractedResult extractOne(String query, Collection<String> choice, Applicable func) {

        List<ExtractedResult> extracted = extractWithoutOrder(query, choice, func);

        return Collections.max(extracted);

    }

    public List<ExtractedResult> extractBests(String query, Collection<String> choice, Applicable func){

        List<ExtractedResult> best = extractWithoutOrder(query, choice, func);
        Collections.sort(best, Collections.<ExtractedResult>reverseOrder());

        return best;
    }

    public List<ExtractedResult> extractBests(String query, Collection<String> choice, Applicable func, int limit){

        List<ExtractedResult> best = extractWithoutOrder(query, choice, func);

        List<ExtractedResult> results = Utils.findTopKHeap(best, limit);
        Collections.reverse(results);

        return results;
    }

    public int getCutoff() {
        return cutoff;
    }

    public void setCutoff(int cutoff) {
        this.cutoff = cutoff;
    }
}
