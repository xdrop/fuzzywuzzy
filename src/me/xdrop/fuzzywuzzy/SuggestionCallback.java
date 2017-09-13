package me.xdrop.fuzzywuzzy;

import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.List;

public interface SuggestionCallback {

    void onResult(List<ExtractedResult> result);

}
