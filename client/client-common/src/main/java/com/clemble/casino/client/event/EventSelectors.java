package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;

public class EventSelectors implements EventSelector {

    final private EventSelector selector;

    private EventSelectors(EventSelector selector) {
        this.selector = selector;
    }

    public static EventSelectors where(EventSelector selector) {
        return new EventSelectors(selector);
    }

    public static EventSelectors not(final EventSelector selector) {
        return new EventSelectors(new EventSelector() {
            @Override
            public boolean filter(Event event) {
                return !selector.filter(event);
            }
        });
    }

    public EventSelectors and(final EventSelector eventSelector) {
        return new EventSelectors(new EventSelector() {
            @Override
            public boolean filter(Event event) {
                return selector.filter(event) && eventSelector.filter(event);
            }
        });
    }

    public EventSelectors or(final EventSelector eventSelector) {
        return new EventSelectors(new EventSelector() {
            @Override
            public boolean filter(Event event) {
                return selector.filter(event) || eventSelector.filter(event);
            }
        });
    }

    @Override
    public boolean filter(Event event) {
        return selector != null ? selector.filter(event) : true;
    }

}
