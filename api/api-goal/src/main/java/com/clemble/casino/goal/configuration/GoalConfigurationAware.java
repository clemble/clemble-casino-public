package com.clemble.casino.goal.configuration;

import com.clemble.casino.lifecycle.configuration.ConfigurationAware;

/**
 * Created by mavarazy on 8/26/14.
 */
public interface GoalConfigurationAware extends ConfigurationAware<GoalConfiguration> {

    public GoalConfiguration getConfiguration();

}
