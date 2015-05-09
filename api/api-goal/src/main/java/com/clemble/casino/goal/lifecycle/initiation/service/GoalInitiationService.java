package com.clemble.casino.goal.lifecycle.initiation.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;

import java.util.Collection;

/**
 * Created by mavarazy on 9/13/14.
 */
public interface GoalInitiationService extends ClembleService {

    GoalInitiation confirm(String key);

    Collection<GoalInitiation> getPending();

    GoalInitiation get(String key);

}
