package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.GoalStatusAware;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.payment.BankAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 10/9/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = {
    @JsonSubTypes.Type(name = "long", value = ShortGoalState.class),
    @JsonSubTypes.Type(name = "short", value = LongGoalState.class)
})
public interface GoalState extends
    State<GoalEvent, GoalContext>,
    GoalAware,
    GoalDescriptionAware,
    GoalConfigurationAware,
    GoalStatusAware,
    PlayerAware,
    BankAware {

}
