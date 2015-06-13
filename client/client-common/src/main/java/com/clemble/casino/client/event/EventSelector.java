package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

public interface EventSelector {

    EventSelector TRUE = new EventSelector() {
        @Override
        public boolean filter(Event event) {
            return true;
        }
    };

    boolean filter(Event event);

}
