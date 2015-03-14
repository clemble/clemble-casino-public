package com.clemble.casino.lifecycle.management.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.management.event.action.Action;

/**
 * Created by mavarazy on 10/8/14.
 */
public interface ActionService extends ClembleService {

    public Event process(String sessionKey, Action action);

    public State getState(String sessionKey);

}
