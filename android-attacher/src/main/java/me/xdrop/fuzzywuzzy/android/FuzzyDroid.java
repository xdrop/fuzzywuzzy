package me.xdrop.fuzzywuzzy.android;

import android.widget.TextView;
import java.util.Collection;
import me.xdrop.fuzzywuzzy.android.bindings.TextViewFuzzyBinding;
import me.xdrop.fuzzywuzzy.functions.ResultProvider;

public class FuzzyDroid {
    public static <T> FuzzyBinding<T> attachFuzzy(TextView view,
                                                  Collection<T> options,
                                                  ResultProvider<T> resultProvider) {
        return new TextViewFuzzyBinding<>(view, resultProvider, options);
    }
}
