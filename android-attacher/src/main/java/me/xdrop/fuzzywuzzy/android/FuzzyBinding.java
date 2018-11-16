package me.xdrop.fuzzywuzzy.android;

import me.xdrop.fuzzywuzzy.android.event.ResultsChangeEvent;
import me.xdrop.fuzzywuzzy.android.listener.GenericListener;
import me.xdrop.fuzzywuzzy.android.listener.ListenerManager;

public interface FuzzyBinding<T> {
    ListenerManager<GenericListener<ResultsChangeEvent<T>>> addResultsChangeListener(
            GenericListener<ResultsChangeEvent<T>> listener);
}
