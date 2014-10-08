package com.clemble.casino.lifecycle.configuration;

import java.io.Serializable;

/**
 * Created by mavarazy on 8/26/14.
 */
public interface ConfigurationAware<T extends Configuration> extends Serializable {

    T getConfiguration();

}
