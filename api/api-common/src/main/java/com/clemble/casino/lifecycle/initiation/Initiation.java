package com.clemble.casino.lifecycle.initiation;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.ConfigurationAware;
import com.clemble.casino.lifecycle.management.Record;

/**
 * Created by mavarazy on 9/12/14.
 */
public interface Initiation<T extends Configuration> extends ConfigurationAware<T> {

    InitiationState getState();

    Record<T> toRecord();

}
