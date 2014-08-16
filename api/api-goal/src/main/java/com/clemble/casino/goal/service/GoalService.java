package com.clemble.casino.goal.service;

import com.clemble.casino.AdvancedClembleService;
import com.clemble.casino.goal.Goal;
import com.clemble.casino.goal.GoalRequest;
import com.clemble.casino.goal.GoalStatus;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public interface GoalService extends AdvancedClembleService {

    /**
     * @throws com.clemble.casino.error.ClembleCasinoException {@link com.clemble.casino.error.ClembleCasinoError#GoalStateIncorrect} in case state is not null or pending
     * @throws com.clemble.casino.error.ClembleCasinoException {@link com.clemble.casino.error.ClembleCasinoError#GoalDueDateInPast} in case due date is in the past
     * @throws com.clemble.casino.error.ClembleCasinoException {@link com.clemble.casino.error.ClembleCasinoError#GoalPlayerIncorrect} in case player is incorrect
     * @return Goal saved goal
     */
    public Goal addMyGoal(GoalRequest goal);

    public GoalStatus myGoalStatuses(String id);

    public GoalStatus updateMyGoal(String id, GoalStatus status);

    public Goal myGoal(String id);

    public Collection<Goal> myGoals();

    public Collection<Goal> myPendingGoals();

    public Collection<Goal> myReachedGoals();

    public Collection<Goal> myMissedGoals();

    public Goal getGoal(String player, String id);

    public Collection<Goal> getGoals(String player);

    public Collection<Goal> getPendingGoals(String player);

    public Collection<Goal> getReachedGoals(String player);

    public Collection<Goal> getMissedGoals(String player);




}
