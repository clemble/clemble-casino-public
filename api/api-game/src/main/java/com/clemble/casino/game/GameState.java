package com.clemble.casino.game;

import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.outcome.GameOutcome;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameState extends Serializable {

    public <State extends GameState> GameManagementEvent<State> process(GameSession<State> session, GameAction move);

    public GameContext getContext();

    public GameOutcome getOutcome();

    public int getVersion();

}
