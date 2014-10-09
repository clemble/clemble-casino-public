package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalPartsAware;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;
import com.clemble.casino.player.PlayerAware;
import org.springframework.data.annotation.Id;

/**
 * Created by mavarazy on 9/20/14.
 */
public interface GoalState extends State<GoalEvent, GoalContext>, GoalAware, GoalConfigurationAware, GoalPartsAware, PlayerAware {

    @Id
    public String getGoalKey();

}
