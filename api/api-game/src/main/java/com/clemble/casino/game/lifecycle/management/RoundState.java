package com.clemble.casino.game.lifecycle.management;

import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by mavarazy on 14/10/14.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface RoundState extends GameUnit {

    public GameManagementEvent process(PlayerAction action, RoundGameState state);

}
