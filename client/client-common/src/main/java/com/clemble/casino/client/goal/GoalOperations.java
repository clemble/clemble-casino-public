package com.clemble.casino.client.goal;

import com.clemble.casino.goal.Goal;
import com.clemble.casino.goal.service.GoalService;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public interface GoalOperations extends GoalService {

    public Goal addGoal(Goal goal);

    public Collection<Goal> getGoals();

    public Collection<Goal> getPendingGoals();

    public Collection<Goal> getReachedGoals();

    public Collection<Goal> getMissedGoals();

}
