package com.clemble.casino.goal.configuration.service;

import com.clemble.casino.configuration.service.ConfigurationService;
import com.clemble.casino.goal.configuration.GoalConfiguration;

import java.util.List;

/**
 * Created by mavarazy on 9/1/14.
 */
public interface GoalConfigurationService extends ConfigurationService<GoalConfiguration> {

    public List<GoalConfiguration> getConfigurations();

}
