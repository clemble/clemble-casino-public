package com.clemble.casino.goal;

import com.clemble.casino.KeyAware;
import com.clemble.casino.player.PlayerAware;

/**
 * Created by mavarazy on 8/2/14.
 */
public interface GoalAware extends KeyAware {

    final public static String GOAL_KEY = "goalKey";

    public GoalKey getGoalKey();

}
