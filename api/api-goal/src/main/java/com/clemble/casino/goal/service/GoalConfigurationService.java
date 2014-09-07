package com.clemble.casino.goal.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.configuration.ConfigurationService;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.configuration.GoalConfigurations;

import java.util.List;

/**
 * Created by mavarazy on 9/1/14.
 */
public interface GoalConfigurationService extends ConfigurationService<GoalConfiguration>, ClembleService{

    public List<GoalConfiguration> getConfigurations();

}
