package com.clemble.casino.client.game;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.game.GamePlayerClock;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.account.GamePlayerAccount;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.player.PlayerAware;

import java.util.Collection;

public interface GameActionOperations<State extends GameState> extends GameSessionAware, PlayerAware {

    public State getState();

    public Collection<String> getOpponents();

    public boolean isToMove();

    public boolean isToMove(String player);

    public Class<?> expectedMove();

    public Class<?> expectedMove(String player);

    public GamePlayerAccount getPlayerAccount();

    public GamePlayerAccount getPlayerAccount(String player);

    public GamePlayerClock getPlayerClock();

    public GamePlayerClock getPlayerClock(String player);

    public State process(GameAction move);

    public MadeMove getAction(int actionId);

    public void subscribe(EventListener eventListener);

}
