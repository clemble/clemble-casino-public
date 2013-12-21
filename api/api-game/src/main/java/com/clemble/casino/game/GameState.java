package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.game.account.GameAccount;
import com.clemble.casino.game.event.client.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.iterator.GamePlayerIterator;
import com.clemble.casino.game.outcome.GameOutcome;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
public interface GameState extends Serializable {

    public <State extends GameState> GameManagementEvent<State> process(GameSession<State> session, GameAction move);

    public GameAccount getAccount();

    public GamePlayerIterator getPlayerIterator();

    public ActionLatch getActionLatch();

    public GameOutcome getOutcome();

    public int getVersion();

}
