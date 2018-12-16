package me.xdrop.matchr.android;

import android.widget.TextView;
import java.util.Collection;
import me.xdrop.matchr.android.bindings.TextViewFuzzyBinding;
import me.xdrop.matchr.functions.ResultProvider;

public class FuzzyDroid {
    public static <T> FuzzyBinding<T> attachFuzzy(TextView view,
                                                  Collection<T> options,
                                                  ResultProvider<T> resultProvider) {
        return new TextViewFuzzyBinding<>(view, resultProvider, options);
    }
}
