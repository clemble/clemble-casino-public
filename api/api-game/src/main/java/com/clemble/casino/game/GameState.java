package com.clemble.casino.game;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.event.GameManagementEvent;
import com.clemble.casino.game.unit.GameUnit;
import com.clemble.casino.lifecycle.management.State;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameState<GC extends GameContext, A extends Event> extends State {

    public GC getContext();

    public GameUnit getState();

    public GameManagementEvent process(A action);

    public int getVersion();

}
