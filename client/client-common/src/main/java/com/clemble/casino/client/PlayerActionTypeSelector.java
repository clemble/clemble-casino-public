package com.clemble.casino.client;

import com.clemble.casino.client.event.EventSelector;
import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;

/**
 * Created by mavarazy on 13/10/14.
 */
public class PlayerActionTypeSelector implements EventSelector{

    final private Class<?> eventType;

    public PlayerActionTypeSelector(Class<?> eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean filter(Event event) {
        return event != null && event instanceof PlayerAction && ((PlayerAction) event).getAction() != null && eventType.isAssignableFrom(((PlayerAction) event).getAction().getClass());
    }

}
