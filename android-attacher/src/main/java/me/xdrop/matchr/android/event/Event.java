package me.xdrop.matchr.android.event;

public abstract class Event {
    private final String cause;

    public Event(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }
}
