package com.clemble.casino.client.game;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventSelector;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.management.GameContext;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.event.GameEvent;
import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.player.PlayerAware;

public interface GameActionOperations<S extends GameState> extends GameSessionAware, PlayerAware {

    public S getState();

    public GameContext<?> getContext();

    public GameManagementEvent process(Action move);

    public GameManagementEvent giveUp();

    public EventListenerController subscribe(EventListener<GameEvent> eventListener);

    public EventListenerController subscribe(EventSelector selector, EventListener<? extends GameEvent> eventListener);

}
