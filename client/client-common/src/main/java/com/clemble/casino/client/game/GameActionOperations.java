package com.clemble.casino.client.game;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionAwareEvent;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.player.PlayerAware;

public interface GameActionOperations<S extends GameState> extends GameSessionAware, PlayerAware {

    public S getState();

    public S process(GameAction move);

    public MadeMove getAction(int actionId);

    public void subscribe(EventListener<GameSessionAwareEvent> eventListener);

}
