package com.clemble.casino.lifecycle.configuration.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.lifecycle.configuration.Configuration;

import java.util.List;

/**
 * Created by mavarazy on 9/7/14.
 */
public interface ConfigurationService<T extends Configuration> extends ClembleService {

        public List<T> getConfigurations();

}

