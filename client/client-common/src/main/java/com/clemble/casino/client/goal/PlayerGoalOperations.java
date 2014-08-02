package com.clemble.casino.client.goal;

import com.clemble.casino.goal.PlayerGoal;
import com.clemble.casino.goal.service.PlayerGoalService;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public interface PlayerGoalOperations extends PlayerGoalService {

    public PlayerGoal addGoal(PlayerGoal goal);

    public Collection<PlayerGoal> getGoals();

    public Collection<PlayerGoal> getPendingGoals();

    public Collection<PlayerGoal> getReachedGoals();

    public Collection<PlayerGoal> getMissedGoals();

}
