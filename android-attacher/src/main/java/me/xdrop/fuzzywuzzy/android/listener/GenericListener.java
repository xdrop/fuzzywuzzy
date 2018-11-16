package me.xdrop.fuzzywuzzy.android.listener;

import me.xdrop.fuzzywuzzy.android.event.Event;

public interface GenericListener<E extends Event> {
    void onEvent(E event);
}
