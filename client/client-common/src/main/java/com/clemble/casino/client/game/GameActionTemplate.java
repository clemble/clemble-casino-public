package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.game.GamePlayerClock;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.account.GamePlayerAccount;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.game.service.GameActionService;

import java.util.Collection;

public class GameActionTemplate<State extends GameState> implements GameActionOperations<State> {

    private static final long serialVersionUID = -2263303118851762598L;

    final private String player;
    final private GameSessionKey session;
    final private GameActionService<State> gameActionService;
    final private EventListenerOperations eventListenersManager;

    public GameActionTemplate(String player, GameSessionKey session, EventListenerOperations eventListenersManager, GameActionService<State> gameActionService) {
        this.player = player;
        this.session = session;
        this.eventListenersManager = checkNotNull(eventListenersManager);
        this.gameActionService = checkNotNull(gameActionService);
    }

    @Override
    public State getState(){
        return gameActionService.getState(session.getGame(), session.getSession());
    }

    @Override
    public boolean isToMove(){
        return isToMove(player);
    }

    @Override
    public boolean isToMove(String player) {
        State currentState = getState();
        return currentState != null && !currentState.getContext().getActionLatch().acted(player);
    }

    @Override
    public Collection<String> getOpponents() {
        State currentState = getState();
        return currentState.getContext().getPlayerIterator().whoIsOpponents(player);
    }

    @Override
    public Class<?> expectedMove() {
        return expectedMove(player);
    }

    @Override
    public Class<?> expectedMove(String player) {
        State currentState = getState();
        return currentState != null ? currentState.getContext().getActionLatch().expectedClass() : null;
    }

    @Override
    public GamePlayerClock getPlayerClock(){
        return getPlayerClock(player);
    }

    @Override
    public GamePlayerClock getPlayerClock(String player) {
        State currentState = getState();
        return currentState != null ? currentState.getContext().getClock().getClock(player) : null;
    }

    @Override
    public GamePlayerAccount getPlayerAccount() {
        return getPlayerAccount(player);
    }

    @Override
    public GamePlayerAccount getPlayerAccount(String player){
        State currentState = getState();
        return currentState != null ? currentState.getContext().getAccount().getPlayerAccount(player) : null;
    }

    @Override
    public State process(GameAction move) {
        return gameActionService.process(session.getGame(), session.getSession(), move);
    }

    @Override
    public MadeMove getAction(int actionId) {
        return gameActionService.getAction(session.getGame(), session.getSession(), actionId);
    }

    @Override
    public void subscribe(EventListener eventListener) {
        eventListenersManager.subscribe(session, eventListener);
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
