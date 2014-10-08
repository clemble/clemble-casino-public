package com.clemble.casino.lifecycle.construction;

import com.clemble.casino.lifecycle.configuration.Configuration;
import com.clemble.casino.lifecycle.configuration.ConfigurationAware;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConstructionRequest<T extends Configuration> extends ConfigurationAware<T> {

    public Construction<T> toConstruction(String player, String key);

}
