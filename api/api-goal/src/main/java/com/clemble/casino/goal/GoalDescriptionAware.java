package com.clemble.casino.goal;

import com.clemble.casino.player.PlayerAware;

/**
 * Created by mavarazy on 8/16/14.
 */
public interface GoalDescriptionAware extends PlayerAware {

    public String getGoal();

    public String getJudge();

}
