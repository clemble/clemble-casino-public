package com.clemble.casino.lifecycle.management;

import com.clemble.casino.event.Event;


/**
 * Created by mavarazy on 10/8/14.
 */
public interface State<R extends Event, C extends StateContext> {

    R process(Event action);

    C getContext();

}
