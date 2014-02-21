package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import com.clemble.casino.client.event.*;
import com.clemble.casino.game.*;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.game.action.surrender.GiveUpAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.event.server.GameMatchEvent;
import com.clemble.casino.game.service.GameActionService;

public class GameActionTemplate<State extends GameState> implements GameActionOperationsExtenstion<State> {

    private static final long serialVersionUID = -2263303118851762598L;

    final private String player;
    final private GameSessionKey session;
    final private GameActionService gameActionService;
    final private EventListenerOperations eventListenersManager;
    final private AtomicReference<GameState> currentState = new AtomicReference<GameState>();

    public GameActionTemplate(String player, GameSessionKey session, EventListenerOperations eventListenersManager, GameActionService gameActionService) {
        this.player = player;
        this.session = session;
        this.eventListenersManager = checkNotNull(eventListenersManager);
        this.eventListenersManager.subscribe(EventSelectors
                .where(new GameSessionEventSelector(session))
                .and(new EventTypeSelector(GameMatchEvent.class)),
            new EventListener<GameMatchEvent>() {
            @Override
            public void onEvent(GameMatchEvent event) {
                currentState.set(event.getState());
            }
        });
        this.gameActionService = checkNotNull(gameActionService);
    }

    @Override
    public State getState(){
        // TODO fix it
        return (State) gameActionService.getState(session.getGame(), session.getSession());
    }

    @Override
    public GameContext<?> getContext() {
        return gameActionService.getContext(session.getGame(), session.getSession());
    }

    private State getCurrentState(){
        // TODO fix it
        return (State) (currentState.get() == null ? setCurrentState(getState()) : currentState.get());
    }

    private State setCurrentState(State state) {
        if(currentState.get() == null || state.getVersion() > currentState.get().getVersion())
            currentState.set(state);
        // TODO fix it
        return (State) currentState.get();
    }

    @Override
    public boolean isToMove(){
        return isToMove(player);
    }

    @Override
    public boolean isToMove(String player) {
        State currentState = getCurrentState();
        return currentState != null && !currentState.getContext().getActionLatch().acted(player);
    }

    @Override
    public Collection<String> getOpponents() {
        State currentState = getCurrentState();
        return currentState != null ? currentState.getContext().getPlayerIterator().whoIsOpponents(player) : null;
    }

    @Override
    public Class<?> expectedMove() {
        return expectedMove(player);
    }

    @Override
    public Class<?> expectedMove(String player) {
        State currentState = getCurrentState();
        return currentState != null ? currentState.getContext().getActionLatch().expectedClass() : null;
    }

    @Override
    public GamePlayerClock getPlayerClock(){
        return getPlayerClock(player);
    }

    @Override
    public GamePlayerClock getPlayerClock(String player) {
        State currentState = getCurrentState();
        return currentState != null ? currentState.getContext().getPlayerContext(player).getClock() : null;
    }

    @Override
    public GamePlayerAccount getPlayerAccount() {
        return getPlayerAccount(player);
    }

    @Override
    public GamePlayerAccount getPlayerAccount(String player){
        State currentState = getCurrentState();
        return currentState != null ? currentState.getContext().getPlayerContext(player).getAccount() : null;
    }

    @Override
    public GameManagementEvent giveUp(){
        return process(new GiveUpAction(player));
    }

    @Override
    public GameManagementEvent process(GameAction move) {
        return gameActionService.process(session.getGame(), session.getSession(), move);
    }

    @Override
    public MadeMove getAction(int actionId) {
        return gameActionService.getAction(session.getGame(), session.getSession(), actionId);
    }

    @Override
    public EventListenerController subscribe(EventListener<GameSessionAwareEvent> eventListener) {
        return eventListenersManager.subscribe(session, eventListener);
    }

    @Override
    public EventListenerController subscribe(EventSelector selector, EventListener<? extends GameSessionAwareEvent> eventListener) {
        return eventListenersManager.subscribe(EventSelectors.where(new GameSessionEventSelector(session)).and(selector), eventListener);
    }

    @Override
    public GameSessionKey getSession() {
        return session;
    }

    @Override
    public String getPlayer() {
        return player;
    }

}
