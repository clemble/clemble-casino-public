package com.clemble.casino.client.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.OutcomeAware;

/**
 * Created by mavarazy on 16/10/14.
 */
public class OutcomeTypeSelector<T extends Outcome> implements EventSelector {

    final private Class<T> eventType;

    public OutcomeTypeSelector(Class<T> eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean filter(Event event) {
        return event != null && event instanceof OutcomeAware && ((OutcomeAware) event).getOutcome().getClass() == eventType;
    }

}
