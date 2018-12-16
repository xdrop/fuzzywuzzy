package me.xdrop.matchr.android.listener;

import me.xdrop.matchr.android.event.Event;

public interface GenericListener<E extends Event> {
    void onEvent(E event);
}
