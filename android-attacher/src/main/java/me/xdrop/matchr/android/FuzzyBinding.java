package me.xdrop.matchr.android;

import me.xdrop.matchr.android.event.ResultsChangeEvent;
import me.xdrop.matchr.android.listener.GenericListener;
import me.xdrop.matchr.android.listener.ListenerManager;

public interface FuzzyBinding<T> {
    ListenerManager<GenericListener<ResultsChangeEvent<T>>> addResultsChangeListener(
            GenericListener<ResultsChangeEvent<T>> listener);
}
