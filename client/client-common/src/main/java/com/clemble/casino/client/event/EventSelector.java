package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

public interface EventSelector {

    public static EventSelector TRUE = new EventSelector() {
        @Override
        public boolean filter(Event event) {
            return true;
        }
    };

    public boolean filter(Event event);

}
