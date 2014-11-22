package com.clemble.casino.goal.lifecycle.initiation.service;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.lifecycle.initiation.service.InitiationService;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;

import java.util.List;
import java.util.Set;

/**
 * Created by mavarazy on 9/13/14.
 */
public interface GoalInitiationService extends InitiationService<GoalInitiation> {

    public GoalInitiation bid(String goalKey, Bid bid);

}
