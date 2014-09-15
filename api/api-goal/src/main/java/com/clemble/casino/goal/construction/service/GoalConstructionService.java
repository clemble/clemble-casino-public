package com.clemble.casino.goal.construction.service;

import com.clemble.casino.construction.Construction;
import com.clemble.casino.construction.service.ConstructionService;
import com.clemble.casino.goal.configuration.GoalConfiguration;
import com.clemble.casino.goal.construction.GoalConstruction;
import com.clemble.casino.goal.construction.GoalConstructionRequest;

import java.util.Collection;

/**
 * Created by mavarazy on 9/10/14.
 */
public interface GoalConstructionService extends ConstructionService<GoalConfiguration, GoalConstructionRequest> {

    @Override
    public GoalConstruction construct(GoalConstructionRequest request);

    @Override
    public Collection<GoalConstruction> getPending(String player);

}
