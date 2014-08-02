package com.clemble.casino.client.goal;

import com.clemble.casino.goal.Goal;
import com.clemble.casino.goal.service.GoalService;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public class GoalTemplate implements GoalOperations {

    final private String player;
    final private GoalService goalService;

    public GoalTemplate(String player, GoalService goalService) {
        this.player = player;
        this.goalService = goalService;
    }

    @Override
    public Goal addGoal(String player, Goal goal) {
        return goalService.addGoal(player, goal);
    }

    @Override
    public Collection<Goal> getGoals(String player) {
        return goalService.getGoals(player);
    }

    @Override
    public Collection<Goal> getPendingGoals(String player) {
        return goalService.getPendingGoals(player);
    }

    @Override
    public Collection<Goal> getReachedGoals(String player) {
        return goalService.getReachedGoals(player);
    }

    @Override
    public Collection<Goal> getMissedGoals(String player) {
        return goalService.getMissedGoals(player);
    }

    @Override
    public Goal addGoal(Goal goal) {
        return goalService.addGoal(player, goal);
    }

    @Override
    public Collection<Goal> getGoals() {
        return goalService.getGoals(player);
    }

    @Override
    public Collection<Goal> getPendingGoals() {
        return goalService.getPendingGoals(player);
    }

    @Override
    public Collection<Goal> getReachedGoals() {
        return goalService.getReachedGoals(player);
    }

    @Override
    public Collection<Goal> getMissedGoals() {
        return goalService.getMissedGoals(player);
    }
}
