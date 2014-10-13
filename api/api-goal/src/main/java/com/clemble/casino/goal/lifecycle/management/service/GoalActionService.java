package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.service.ActionService;

import java.util.List;

/**
 * Created by mavarazy on 10/9/14.
 */
public interface GoalActionService extends ActionService {

    public List<GoalState> myActive();

    public List<GoalState> getActive(String player);

    @Override
    public GoalEvent process(String goalKey, Action action);

    public GoalState getState(String goalKey);

}
