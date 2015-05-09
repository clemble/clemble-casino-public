package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.lifecycle.management.event.action.Action;

import java.util.List;

/**
 * Created by mavarazy on 10/9/14.
 */
public interface GoalActionService extends ClembleService {

    public List<GoalState> myActive();

    public List<GoalState> getActive(String player);

    public GoalEvent process(String goalKey, Action action);

    public GoalState getState(String goalKey);

}
