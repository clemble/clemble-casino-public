package com.clemble.casino.configuration;

import com.clemble.casino.ClembleService;

import java.util.List;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConfigurationService<T extends Configuration> extends ClembleService {

        public List<T> getConfigurations();

}

