package com.clemble.casino.goal.service;

import com.clemble.casino.goal.Goal;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public interface GoalService extends GoalServiceContract {

    public Goal addMyGoal(Goal goal);

    public Collection<Goal> myGoals();

    public Collection<Goal> myPendingGoals();

    public Collection<Goal> myReachedGoals();

    public Collection<Goal> myMissedGoals();

}
