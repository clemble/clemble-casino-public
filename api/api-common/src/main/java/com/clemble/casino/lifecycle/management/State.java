package com.clemble.casino.lifecycle.management;

import com.clemble.casino.event.Event;
import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.ConfigurationAware;


/**
 * Created by mavarazy on 10/8/14.
 */
public interface State<R extends Event, C extends StateContext> {

    R start();

    R process(Event action);

    C getContext();

    Configuration getConfiguration();

    String toKey(); // TODO this was added to generalize processing for notification, find a better way

}
