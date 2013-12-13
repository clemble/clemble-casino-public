package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

public class EventTypeSelector implements EventSelector {

    final private Class<?> eventType;

    public EventTypeSelector(Class<?> eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean filter(Event event) {
        return event != null && eventType.isAssignableFrom(event.getClass());
    }

}
