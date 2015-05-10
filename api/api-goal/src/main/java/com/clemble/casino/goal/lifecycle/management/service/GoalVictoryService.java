package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.GoalService;
import com.clemble.casino.goal.lifecycle.management.GoalState;

import java.util.List;

/**
 * Created by mavarazy on 3/14/15.
 */
public interface GoalVictoryService extends GoalService {

    public List<GoalState> listMy();

    public List<GoalState> list(String player);

}
