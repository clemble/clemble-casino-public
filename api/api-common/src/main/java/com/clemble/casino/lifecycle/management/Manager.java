package com.clemble.casino.lifecycle.management;

import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;

/**
 * Created by mavarazy on 10/8/14.
 */
public interface Manager {

    public State getState();

    public Event process(Event action);

}
