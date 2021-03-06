package com.clemble.casino.goal.lifecycle.construction.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.GoalService;
import com.clemble.casino.goal.lifecycle.construction.GoalConstruction;
import com.clemble.casino.goal.lifecycle.construction.GoalConstructionRequest;

import java.util.Collection;

/**
 * Created by mavarazy on 9/10/14.
 */
public interface GoalConstructionService extends GoalService {

    GoalConstruction construct(GoalConstructionRequest request);

    Collection<GoalConstruction> getPending(String player);

}
