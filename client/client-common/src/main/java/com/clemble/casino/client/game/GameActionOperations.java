package com.clemble.casino.client.game;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventSelector;
import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.event.GameSessionAwareEvent;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.player.PlayerAware;

public interface GameActionOperations<S extends GameState> extends GameSessionAware, PlayerAware {

    public S getState();

    public GameContext<?> getContext();

    public GameManagementEvent process(GameAction move);

    public GameManagementEvent giveUp();

    public EventListenerController subscribe(EventListener<GameSessionAwareEvent> eventListener);

    public EventListenerController subscribe(EventSelector selector, EventListener<? extends GameSessionAwareEvent> eventListener);

}
