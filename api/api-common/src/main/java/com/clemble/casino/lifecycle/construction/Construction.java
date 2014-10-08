package com.clemble.casino.lifecycle.construction;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.ConfigurationAware;
import com.clemble.casino.lifecycle.initiation.Initiation;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface Construction<T extends Configuration> extends ConfigurationAware<T> {

    ConstructionState getState();

    Initiation<T> toInitiation();

}
