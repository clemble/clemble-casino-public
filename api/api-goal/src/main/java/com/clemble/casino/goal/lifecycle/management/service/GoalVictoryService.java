package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.lifecycle.record.GoalRecord;

import java.util.List;

/**
 * Created by mavarazy on 3/14/15.
 */
public interface GoalVictoryService extends ClembleService {

    public List<GoalRecord> listMy();

    public List<GoalRecord> list(String player);

}
