package me.xdrop.fuzzywuzzy;

import me.xdrop.fuzzywuzzy.algorithms.WeightedRatio;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.ArrayList;
import java.util.List;

public class ContractCheck {

    /**
     * Test that the public interface contract is met, to ensure no backwards
     * compatibility breaking changes. The test only ensures type safety
     * at compilation time, the rest of the tests are needed for
     * functionality testing.
     */
    public void testContract() {
        int ratio = FuzzySearch.ratio("mysmilarstring", "myawfullysimilarstirng");
        int i = FuzzySearch.partialRatio("mysmilarstring", "myawfullysimilarstirng");

        ArrayList<String> sample = new ArrayList<>();

        int order_words_out_of = FuzzySearch.tokenSortPartialRatio("order words out of", "  words out of order");
        int order_words_out_of1 = FuzzySearch.tokenSortRatio("order words out of", "  words out of order");

        int i1 = FuzzySearch.tokenSetRatio("fuzzy was a bear", "fuzzy fuzzy fuzzy bear");
        int i2 = FuzzySearch.tokenSetPartialRatio("fuzzy was a bear", "fuzzy fuzzy fuzzy bear");
        int i3 = FuzzySearch.weightedRatio("The quick brown fox jimps ofver the small lazy dog", "the quick brown fox jumps over the small lazy dog");

        WeightedRatio weighted = new WeightedRatio();
        weighted.with(new StringProcessor() {
            @Override
            public String process(String in) {
                return in;
            }
        });

        ExtractedResult one = FuzzySearch.extractOne("cowboys", sample);

        List<ExtractedResult> e1 = FuzzySearch.extractTop("goolge", sample, weighted, 3);
        List<ExtractedResult> e2 = FuzzySearch.extractAll("goolge", sample);
        List<ExtractedResult> e3 = FuzzySearch.extractAll("goolge", sample, 40);
        List<ExtractedResult> e4 = FuzzySearch.extractSorted("goolge", sample);
        List<ExtractedResult> e5 = FuzzySearch.extractSorted("goolge", sample, 3);
    }
}
