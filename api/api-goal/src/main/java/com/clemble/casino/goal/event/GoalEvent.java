package com.clemble.casino.goal.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.player.PlayerAware;

/**
 * Created by mavarazy on 9/13/14.
 */
public interface GoalEvent<T extends GoalAware> extends Event, PlayerAware {

    T getBody();

}
