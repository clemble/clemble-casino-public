package com.clemble.casino.goal.configuration.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.configuration.ConfigurationService;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.configuration.GoalConfigurations;

import java.util.List;

/**
 * Created by mavarazy on 9/1/14.
 */
public interface GoalConfigurationService extends ConfigurationService<GoalConfiguration> {

    public List<GoalConfiguration> getConfigurations();

}
