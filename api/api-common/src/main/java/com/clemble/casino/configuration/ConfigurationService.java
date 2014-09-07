package com.clemble.casino.configuration;

import java.util.List;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConfigurationService<T extends Configuration> {

        public List<T> getConfigurations();

}

