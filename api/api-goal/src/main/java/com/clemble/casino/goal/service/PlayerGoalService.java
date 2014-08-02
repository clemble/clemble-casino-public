package com.clemble.casino.goal.service;

import com.clemble.casino.goal.PlayerGoal;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public interface PlayerGoalService {

    public PlayerGoal addGoal(String player, PlayerGoal goal);

    public Collection<PlayerGoal> getGoals(String player);

    public Collection<PlayerGoal> getPendingGoals(String player);

    public Collection<PlayerGoal> getReachedGoals(String player);

    public Collection<PlayerGoal> getMissedGoals(String player);

}
