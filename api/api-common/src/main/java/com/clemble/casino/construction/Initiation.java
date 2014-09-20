package com.clemble.casino.construction;

import com.clemble.casino.configuration.Configuration;
import com.clemble.casino.configuration.ConfigurationAware;
import com.clemble.casino.management.Record;

/**
 * Created by mavarazy on 9/12/14.
 */
public interface Initiation<T extends Configuration> extends ConfigurationAware<T> {

    InitiationState getState();

    Record<T> toRecord();

}
