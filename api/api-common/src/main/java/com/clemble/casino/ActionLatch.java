package com.clemble.casino;

import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.PlayerAware;
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

    private Set<String> participants = new HashSet<String>();
    private Collection<PlayerAction> actions = new ArrayList<PlayerAction>();

    public ActionLatch() {
    }

    @JsonCreator
    public ActionLatch(@JsonProperty("actions") final Collection<PlayerAction> actions) {
        this.actions.addAll(actions);
    }

    public ActionLatch expectNext(final String sessionKey, final Collection<String> participants, Class<? extends Action> expectedClass) {
        this.actions.clear();
        for (String participant : participants) {
            this.actions.add(new PlayerAction(sessionKey, participant, PlayerExpectedAction.fromClass(expectedClass)));
        }
        return this;
    }

    public boolean contains(String participant) {
        return PlayerAwareUtils.contains(actions, participant);
    }

    public boolean acted(String player) {
        // Step 1. Processing all assosiated player events
        for(PlayerAction playerEvent: filterAllActions(player))
            if(playerEvent.getAction() instanceof PlayerExpectedAction)
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
    public <T extends PlayerAction> Collection<T> getActions() {
        return (Collection<T>)(Collection<? extends PlayerAware>) actions;
    }

    public void append(PlayerAction event) {
        this.actions.add(event);
    }

    public PlayerAction filterAction(String player) {
        return PlayerAwareUtils.filter(player, actions);
    }

    public Collection<PlayerAction> filterAllActions(String player) {
        return PlayerAwareUtils.filterAll(player, actions);
    }

    public <T extends PlayerAction> ActionLatch put(T playerAction) {
        PlayerAction event = filterAction(playerAction.getPlayer());
        if (event.getAction() instanceof PlayerExpectedAction) {
            if (!((PlayerExpectedAction) event.getAction()).isExpected(playerAction.getAction().getClass()))
                throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayWrongMoveType, playerAction.getPlayer());
            actions.remove(event);
            actions.add(playerAction);
            return this;
        } else if (event != null) {
            throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayMoveAlreadyMade, playerAction.getPlayer());
        }
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayNoMoveExpected, playerAction.getPlayer());
    }

    public boolean complete() {
        for (PlayerAction action : actions)
            if (action.getAction() instanceof PlayerExpectedAction)
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
