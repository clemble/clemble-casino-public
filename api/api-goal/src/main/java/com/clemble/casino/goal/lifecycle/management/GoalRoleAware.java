package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.player.PlayerAware;

import java.util.Set;

/**
 * Created by mavarazy on 12/30/14.
 */
public interface GoalRoleAware extends PlayerAware {

    public Set<String> getSupporters();

}
