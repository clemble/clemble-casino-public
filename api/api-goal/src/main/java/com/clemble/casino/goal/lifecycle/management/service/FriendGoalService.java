package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.goal.lifecycle.management.GoalState;

import java.util.List;

/**
 * Created by mavarazy on 11/18/14.
 */
public interface FriendGoalService {

    public GoalState myFriendGoal(String goalKey);

    public List<GoalState> myFriendGoals();

    public List<GoalState> getFriendGoals(String player);

    public GoalState getFriendGoal(String player, String goalKey);

}
