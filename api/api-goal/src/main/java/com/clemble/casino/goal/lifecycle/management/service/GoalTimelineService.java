package com.clemble.casino.goal.lifecycle.management.service;

import com.clemble.casino.goal.lifecycle.management.GoalState;

import java.util.List;

/**
 * Created by mavarazy on 11/18/14.
 */
public interface GoalTimelineService {

    public GoalState myConnectionTimeLine(String goalKey);

    public List<GoalState> myConnectionsTimeLine();

    public GoalState getConnectionTimeLine(String player, String goalKey);

    public List<GoalState> getConnectionsTimeLine(String player);

}
