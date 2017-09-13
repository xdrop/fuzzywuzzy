package me.xdrop.fuzzywuzzy;

import me.xdrop.fuzzywuzzy.algorithms.WeightedRatio;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.ArrayList;
import java.util.List;

/**
 * An autocomplete class that allows easy searching on fixed list of elements. This is typically
 * used to search as the user types
 */
public class FuzzyAutocomplete {

    private final List<String> dataMatchCollection;
    private final int entriesShown;
    private final Applicable method;
    private final Extractor extractor;

    /**
     *
     * @param targetCollection The target collection to be searched
     * @param entriesShown The number of entries to be retrieved (the rest are ignored)
     * @param method The ratio to be used
     * @param cutoff Any entries below this are rejected
     */
    public FuzzyAutocomplete(List<String> targetCollection, int entriesShown, Applicable method, int cutoff) {
        this.dataMatchCollection = targetCollection;
        this.entriesShown = entriesShown;
        this.method = method;
        if (cutoff != 0){
            this.extractor = new Extractor(cutoff);
        } else {
            this.extractor = new Extractor();
        }
    }

    /**
     *
     * @param targetCollection The target collection to be searched
     * @param entriesShown The number of entries to be retrieved (the rest are ignored)
     * @param method The ratio to be used
     */
    public FuzzyAutocomplete(List<String> targetCollection, int entriesShown, Applicable method) {
        this(targetCollection, entriesShown, method, 0);
    }

    /**
     * Creates an autocomplete instance with the default setup, which is using {@link WeightedRatio} with no
     * cutoff and returning *five* entries.
     *
     */
    public FuzzyAutocomplete() {
        this(new ArrayList<String>(), 5, new WeightedRatio(), 0);
    }

    /**
     * Fires an asynchronous callback when the fuzzy search is complete
     *
     * @param query The current input string to be searched
     * @param callback The callback to be called once the search has been complete
     */
    public void onUpdate(String query, SuggestionCallback callback) {

        List<ExtractedResult> extractedResults = extractor.extractTop(query, dataMatchCollection, method, entriesShown);
        callback.onResult(extractedResults);
    }

    /**
     * Performs a synchronous search and returns a list of {@link ExtractedResult}
     *
     * @param query The current input string to be searched
     * @return The list of scores and strings (see {@link ExtractedResult})
     */
    public List<ExtractedResult> retrieve(String query) {
        List<ExtractedResult> extractedResults = extractor.extractTop(query, dataMatchCollection, method, entriesShown);
        return extractedResults;
    }

}
