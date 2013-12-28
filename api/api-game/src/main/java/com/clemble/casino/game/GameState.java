package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameState extends Serializable {

    public GameContext getContext();

    public GameUnit getParent();

    public <State extends GameState> GameManagementEvent process(GameSession<State> session, GameAction move);

    public GameOutcome getOutcome();

    public int getVersion();

}
