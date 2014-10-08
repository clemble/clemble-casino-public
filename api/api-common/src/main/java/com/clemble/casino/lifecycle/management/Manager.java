package com.clemble.casino.lifecycle.management;

import com.clemble.casino.event.Event;

/**
 * Created by mavarazy on 10/8/14.
 */
public interface Manager<I extends Event, O extends Event> {

    public State getState();

    public O process(I action);

}
