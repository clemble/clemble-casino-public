package com.clemble.casino.base;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;

public class ActionLatch implements Serializable {

    /**
     * Generated 04/07/13
     */
    private static final long serialVersionUID = -7689529505293361503L;

    private Collection<PlayerAwareEvent> actions = new ArrayList<PlayerAwareEvent>();
    private Class<?> expectedClass;

    public ActionLatch() {
    }

    @JsonCreator
    public ActionLatch(@JsonProperty("actions") final Collection<? extends PlayerAwareEvent> expectedActions) {
        this.expectedClass = null;
        this.actions.addAll(expectedActions);
    }

    public ActionLatch expectNext(final String player, final String action) {
        expectNext(player, action, null);
        return this;
    }

    public ActionLatch expectNext(final String player, String action, Class<?> expectedClass) {
        this.actions.clear();
        this.actions.add(new SimpleExpectedEvent(player, action));
        this.expectedClass = expectedClass;
        return this;
    }

    public ActionLatch expectNext(final Collection<String> participants, final String action) {
        this.actions.clear();
        for (String participant : participants) {
            this.actions.add(new SimpleExpectedEvent(participant, action));
        }
        this.expectedClass = null;
        return this;
    }

    public ActionLatch expectNext(final Collection<String> participants, final String action, Class<?> expectedClass) {
        this.actions.clear();
        for (String participant : participants) {
            this.actions.add(new SimpleExpectedEvent(participant, action));
        }
        this.expectedClass = expectedClass;
        return this;
    }

    public ActionLatch expectNext(final String player, final Collection<String> participants, final String action) {
        expectNext(player, participants, action, null);
        return this;
    }

    public void expectNext(final String player, final Collection<String> participants, final String action, final Class<?> expectedClass) {
        this.expectedClass = expectedClass;
        this.actions.add(new SimpleExpectedEvent(player, action));
        for (String participant : participants) {
            this.actions.add(new SimpleExpectedEvent(participant, action));
        }
    }

    public boolean contains(String participant) {
        return PlayerAwareUtils.contains(actions, participant);
    }

    public Class<?> expectedClass() {
        return expectedClass;
    }

    public boolean acted(String player) {
        // Step 1. Processing all assosiated player events
        for(PlayerAwareEvent playerEvent: filterAllActions(player))
            if(playerEvent instanceof ExpectedEvent)
                return false;
        // Step 2. If no events expected return true
        return true;
    }

    public Set<String> fetchParticipants() {
        return PlayerAwareUtils.toPlayerSet(actions);
    }

    @SuppressWarnings("unchecked")
    public <T extends PlayerAwareEvent> Collection<T> getActions() {
        return (Collection<T>) actions;
    }

    public void append(PlayerAwareEvent event) {
        this.actions.add(event);
    }

    public PlayerAwareEvent filterAction(String player) {
        return PlayerAwareUtils.filter(player, actions);
    }

    public Collection<PlayerAwareEvent> filterAllActions(String player) {
        return PlayerAwareUtils.filterAll(player, actions);
    }

    public <T extends PlayerAwareEvent> PlayerAwareEvent put(T action) {
        PlayerAwareEvent event = filterAction(action.getPlayer());
        if (event instanceof ExpectedEvent) {
            if (expectedClass != null && action.getClass() != expectedClass)
                throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayWrongMoveType);
            actions.remove(event);
            actions.add(action);
            return event;
        } else if (event != null) {
            throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayMoveAlreadyMade);
        }
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayNoMoveExpected);
    }

    public boolean complete() {
        for (PlayerAwareEvent action : actions)
            if (action instanceof ExpectedEvent)
                return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((actions == null) ? 0 : actions.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ActionLatch other = (ActionLatch) obj;
        if (actions == null) {
            if (other.actions != null)
                return false;
        } else if (!actions.equals(other.actions))
            return false;
        return true;
    }

}
