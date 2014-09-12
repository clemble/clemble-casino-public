package com.clemble.casino.goal.construction.service;

import com.clemble.casino.construction.service.ConstructionService;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.construction.GoalConstructionRequest;

/**
 * Created by mavarazy on 9/10/14.
 */
public interface GoalConstructionService<T extends GoalConstructionRequest> extends ConstructionService<GoalConfiguration, T> {

}
