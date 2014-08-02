package com.clemble.casino.client.goal;

import com.clemble.casino.goal.PlayerGoal;
import com.clemble.casino.goal.service.PlayerGoalService;

import java.util.Collection;

/**
 * Created by mavarazy on 8/2/14.
 */
public class PlayerGoalTemplate implements PlayerGoalOperations {

    final private String player;
    final private PlayerGoalService goalService;

    public PlayerGoalTemplate(String player, PlayerGoalService goalService) {
        this.player = player;
        this.goalService = goalService;
    }

    @Override
    public PlayerGoal addGoal(String player, PlayerGoal goal) {
        return goalService.addGoal(player, goal);
    }

    @Override
    public Collection<PlayerGoal> getGoals(String player) {
        return goalService.getGoals(player);
    }

    @Override
    public Collection<PlayerGoal> getPendingGoals(String player) {
        return goalService.getPendingGoals(player);
    }

    @Override
    public Collection<PlayerGoal> getReachedGoals(String player) {
        return goalService.getReachedGoals(player);
    }

    @Override
    public Collection<PlayerGoal> getMissedGoals(String player) {
        return goalService.getMissedGoals(player);
    }

    @Override
    public PlayerGoal addGoal(PlayerGoal goal) {
        return goalService.addGoal(player, goal);
    }

    @Override
    public Collection<PlayerGoal> getGoals() {
        return goalService.getGoals(player);
    }

    @Override
    public Collection<PlayerGoal> getPendingGoals() {
        return goalService.getPendingGoals(player);
    }

    @Override
    public Collection<PlayerGoal> getReachedGoals() {
        return goalService.getReachedGoals(player);
    }

    @Override
    public Collection<PlayerGoal> getMissedGoals() {
        return goalService.getMissedGoals(player);
    }
}
