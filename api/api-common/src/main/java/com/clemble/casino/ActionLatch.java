package com.clemble.casino;

import com.clemble.casino.event.ExpectedEvent;
import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.player.PlayerAwareUtils;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.*;

public class ActionLatch implements Serializable {

    /**
     * Generated 04/07/13
     */
    private static final long serialVersionUID = -7689529505293361503L;

    private Set<String> participants = new HashSet<String>();
    private Collection<PlayerEvent> actions = new ArrayList<PlayerEvent>();

    public ActionLatch() {
    }

    @JsonCreator
    public ActionLatch(@JsonProperty("actions") final Collection<? extends PlayerEvent> expectedActions) {
        this.actions.addAll(expectedActions);
    }

    public ActionLatch expectNext(final String player, Class<? extends PlayerEvent> expectedClass) {
        this.actions.clear();
        this.actions.add(ExpectedEvent.fromClass(player, expectedClass));
        return this;
    }

    public ActionLatch expectNext(final Collection<String> participants, Class<? extends PlayerEvent> expectedClass) {
        this.actions.clear();
        for (String participant : participants) {
            this.actions.add(ExpectedEvent.fromClass(participant, expectedClass));
        }
        return this;
    }

    public void expectNext(final String player, final Collection<String> participants, final Class<? extends PlayerEvent> expectedClass) {
        this.actions.add(ExpectedEvent.fromClass(player, expectedClass));
        for (String participant : participants) {
            this.actions.add(ExpectedEvent.fromClass(participant, expectedClass));
        }
    }

    public boolean contains(String participant) {
        return PlayerAwareUtils.contains(actions, participant);
    }

    public boolean acted(String player) {
        // Step 1. Processing all assosiated player events
        for(PlayerEvent playerEvent: filterAllActions(player))
            if(playerEvent instanceof ExpectedEvent)
                return false;
        // Step 2. If no events expected return true
        return true;
    }

    public Set<String> fetchParticipants() {
        try {
            return PlayerAwareUtils.toPlayerSet(actions);
        } catch (ConcurrentModificationException cme) {
            return fetchParticipants();
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends PlayerEvent> Collection<T> getActions() {
        return (Collection<T>) actions;
    }

    public void append(PlayerEvent event) {
        this.actions.add(event);
    }

    public PlayerEvent filterAction(String player) {
        return PlayerAwareUtils.filter(player, actions);
    }

    public Collection<PlayerEvent> filterAllActions(String player) {
        return PlayerAwareUtils.filterAll(player, actions);
    }

    public <T extends PlayerEvent> ActionLatch put(T action) {
        PlayerEvent event = filterAction(action.getPlayer());
        if (event instanceof ExpectedEvent) {
            if (!((ExpectedEvent) event).isExpected(action.getClass()))
                throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayWrongMoveType, action.getPlayer());
            actions.remove(event);
            actions.add(action);
            return this;
        } else if (event != null) {
            throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayMoveAlreadyMade, action.getPlayer());
        }
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayNoMoveExpected, action.getPlayer());
    }

    public boolean complete() {
        for (PlayerEvent action : actions)
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
