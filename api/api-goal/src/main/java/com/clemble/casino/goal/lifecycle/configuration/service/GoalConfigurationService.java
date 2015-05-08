package com.clemble.casino.goal.lifecycle.configuration.service;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationChoices;
import com.clemble.casino.goal.lifecycle.configuration.IntervalGoalConfigurationBuilder;
import com.clemble.casino.lifecycle.configuration.service.ConfigurationService;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;

import java.util.List;

/**
 * Created by mavarazy on 9/1/14.
 */
public interface GoalConfigurationService extends ConfigurationService<GoalConfiguration> {

    public GoalConfigurationChoices getChoices();

    public List<GoalConfiguration> getConfigurations();

    public IntervalGoalConfigurationBuilder getIntervalBuilder();

}
