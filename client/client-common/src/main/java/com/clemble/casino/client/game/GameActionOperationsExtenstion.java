package com.clemble.casino.client.game;

import java.util.Collection;

import com.clemble.casino.game.GamePlayerAccount;
import com.clemble.casino.game.GamePlayerClock;
import com.clemble.casino.game.GameState;

public interface GameActionOperationsExtenstion<S extends GameState> extends GameActionOperations<S> {

    public Collection<String> getOpponents();

    public boolean isToMove();

    public boolean isToMove(String player);

    public Class<?> expectedMove();

    public Class<?> expectedMove(String player);

    public GamePlayerAccount getPlayerAccount();

    public GamePlayerAccount getPlayerAccount(String player);

    public GamePlayerClock getPlayerClock();

    public GamePlayerClock getPlayerClock(String player);

}
