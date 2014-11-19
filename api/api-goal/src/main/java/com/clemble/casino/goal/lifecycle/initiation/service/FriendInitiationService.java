package com.clemble.casino.goal.lifecycle.initiation.service;

import com.clemble.casino.ClembleService;
import com.clemble.casino.goal.lifecycle.construction.GoalConstruction;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.lifecycle.management.GoalState;

import java.util.List;

/**
 * Created by mavarazy on 11/19/14.
 */
public interface FriendInitiationService extends ClembleService {

    GoalInitiation myFriendInitiation(String goalKey);

    List<GoalInitiation> myFriendInitiations();

    List<GoalInitiation> getFriendInitiations(String player);

    GoalInitiation getFriendInitiation(String player, String goalKey);

}
