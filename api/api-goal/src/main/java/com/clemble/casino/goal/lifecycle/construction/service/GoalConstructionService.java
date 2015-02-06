package com.clemble.casino.goal.lifecycle.construction.service;

import com.clemble.casino.goal.lifecycle.construction.IntervalGoalConstructionRequest;
import com.clemble.casino.lifecycle.construction.service.ConstructionService;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.construction.GoalConstruction;
import com.clemble.casino.goal.lifecycle.construction.GoalConstructionRequest;

import java.util.Collection;

/**
 * Created by mavarazy on 9/10/14.
 */
public interface GoalConstructionService extends ConstructionService<GoalConfiguration, GoalConstructionRequest> {

    @Override
    public GoalConstruction construct(GoalConstructionRequest request);

    public GoalConstruction construct(IntervalGoalConstructionRequest intervalRequest);

    @Override
    public Collection<GoalConstruction> getPending(String player);

}
