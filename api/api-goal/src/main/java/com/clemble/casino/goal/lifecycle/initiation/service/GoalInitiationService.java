package com.clemble.casino.goal.lifecycle.initiation.service;

import com.clemble.casino.goal.lifecycle.management.GoalRole;
import com.clemble.casino.lifecycle.initiation.service.InitiationService;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;

/**
 * Created by mavarazy on 9/13/14.
 */
public interface GoalInitiationService extends InitiationService<GoalInitiation> {

    public GoalInitiation bid(String goalKey, GoalRole role);

}
