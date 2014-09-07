package com.clemble.casino.configuration;

import java.io.Serializable;

/**
 * Created by mavarazy on 8/26/14.
 */
public interface ConfigurationAware<T extends Configuration> extends Serializable {

    T getConfiguration();

}
