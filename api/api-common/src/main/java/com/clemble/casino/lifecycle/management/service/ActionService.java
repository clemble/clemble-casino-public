package com.clemble.casino.lifecycle.management.service;

import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.management.State;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by mavarazy on 10/8/14.
 */
public interface ActionService {

    public Event process(String sessionKey, Event action);

    public State getState(String sessionKey);

}
