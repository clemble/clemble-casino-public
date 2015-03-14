package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.lifecycle.management.GoalVictory;

import java.util.List;

/**
 * Created by mavarazy on 3/14/15.
 */
public interface GoalVictoryService extends ClembleService {

    public List<GoalVictory> listMy();

    public List<GoalVictory> list(String player);

}
