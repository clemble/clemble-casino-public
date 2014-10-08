package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import com.clemble.casino.event.PlayerExpectedAction;
import com.clemble.casino.client.event.*;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.management.GameContext;
import com.clemble.casino.game.lifecycle.management.GamePlayerAccount;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.management.RoundGameState;
import com.clemble.casino.lifecycle.management.event.surrender.GiveUpAction;
import com.clemble.casino.game.event.GameEvent;
import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;
import com.clemble.casino.game.lifecycle.management.event.RoundEvent;
import com.clemble.casino.game.lifecycle.management.service.GameActionService;
import com.clemble.casino.player.event.PlayerEvent;
import com.clemble.casino.lifecycle.configuration.rule.time.PlayerClock;

public class GameActionTemplate<State extends GameState> implements GameActionOperationsExtenstion<State> {

    // TODO this must be more generic applicable for matches and tournaments

    private static final long serialVersionUID = -2263303118851762598L;

    final private String player;
    final private String session;
    final private GameActionService gameActionService;
    final private EventListenerOperations eventListenersManager;
    final private AtomicReference<RoundGameState> currentState = new AtomicReference<RoundGameState>();

    public GameActionTemplate(String player, String session, EventListenerOperations eventListenersManager, GameActionService gameActionService) {
        this.player = player;
        this.session = session;
        this.eventListenersManager = checkNotNull(eventListenersManager);
        this.eventListenersManager.subscribe(EventSelectors
                .where(new GameSessionEventSelector(session))
                .and(new EventTypeSelector(RoundEvent.class)),
            new EventListener<RoundEvent>() {
            @Override
            public void onEvent(RoundEvent event) {
                currentState.set(event.getState());
            }
        });
        this.gameActionService = checkNotNull(gameActionService);
    }

    @Override
    public State getState(){
        // TODO fix it
        return (State) gameActionService.getState(session);
    }

    @Override
    public GameContext<?> getContext() {
        return gameActionService.getContext(session);
    }

    private RoundGameState getCurrentState(){
        return (RoundGameState) (currentState.get() == null ? setCurrentState((RoundGameState) getState()) : currentState.get());
    }

    private RoundGameState setCurrentState(RoundGameState state) {
        if(currentState.get() == null || state.getVersion() > currentState.get().getVersion())
            currentState.set(state);
        // TODO fix it
        return (RoundGameState) currentState.get();
    }

    @Override
    public boolean isToMove(){
        return isToMove(player);
    }

    @Override
    public boolean isToMove(String player) {
        RoundGameState state = currentState.get();
        return state != null && !state.getContext().getActionLatch().acted(player);
    }

    @Override
    public Collection<String> getOpponents() {
        RoundGameState state = currentState.get();
        return state != null ? state.getContext().getPlayerIterator().whoIsOpponents(player) : null;
    }

    @Override
    public Class<?> expectedMove() {
        return expectedMove(player);
    }

    @Override
    public Class<?> expectedMove(String player) {
        RoundGameState state = currentState.get();
        PlayerEvent event = state != null ? state.getContext().getActionLatch().filterAction(player) : null;
        return event == null || !(event instanceof PlayerExpectedAction) ? null : ((PlayerExpectedAction) event).expected();
    }

    @Override
    public PlayerClock getPlayerClock(){
        return getPlayerClock(player);
    }

    @Override
    public PlayerClock getPlayerClock(String player) {
        RoundGameState state = currentState.get();
        return state != null ? state.getContext().getPlayerContext(player).getClock() : null;
    }

    @Override
    public GamePlayerAccount getPlayerAccount() {
        return getPlayerAccount(player);
    }

    @Override
    public GamePlayerAccount getPlayerAccount(String player){
        RoundGameState state = currentState.get();
        return state != null ? state.getContext().getPlayerContext(player).getAccount() : null;
    }

    @Override
    public GameManagementEvent giveUp(){
        return process(new GiveUpAction(player));
    }

    @Override
    public GameManagementEvent process(Event move) {
        return gameActionService.process(session, move);
    }

    @Override
    public EventListenerController subscribe(EventListener<GameEvent> eventListener) {
        return eventListenersManager.subscribeToGameSession(session, eventListener);
    }

    @Override
    public EventListenerController subscribe(EventSelector selector, EventListener<? extends GameEvent> eventListener) {
        return eventListenersManager.subscribe(EventSelectors.where(new GameSessionEventSelector(session)).and(selector), eventListener);
    }

    @Override
    public String getSessionKey() {
        return session;
    }

    @Override
    public String getPlayer() {
        return player;
    }

}
