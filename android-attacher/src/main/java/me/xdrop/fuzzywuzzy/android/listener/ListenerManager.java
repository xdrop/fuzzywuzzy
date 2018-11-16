package me.xdrop.fuzzywuzzy.android.listener;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public interface ListenerManager<L extends GenericListener<?>> {
    boolean detachNow();

    ScheduledFuture<?> detachIn(long time, TimeUnit unit);

    L getListener();
}
