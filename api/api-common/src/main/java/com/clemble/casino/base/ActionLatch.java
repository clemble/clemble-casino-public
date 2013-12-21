package com.clemble.casino.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.event.PlayerAwareEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ActionLatch implements Serializable {

    /**
     * Generated 04/07/13
     */
    private static final long serialVersionUID = -7689529505293361503L;

    final private Map<String, PlayerAwareEvent> actions = new HashMap<String, PlayerAwareEvent>();
    final private Class<?> expectedClass;

    public ActionLatch(final String player, final String action) {
        this(player, action, null);
    }

    public ActionLatch(final String player, String action, Class<?> expectedClass) {
        this.actions.put(player, new ExpectedEvent(player, action));
        this.expectedClass = expectedClass;
    }

    public ActionLatch(final Collection<String> participants, final String action) {
        for (String participant : participants) {
            this.actions.put(participant, new ExpectedEvent(participant, action));
        }
        this.expectedClass = null;
    }

    public ActionLatch(final Collection<String> participants, final String action, Class<?> expectedClass) {
        for (String participant : participants) {
            this.actions.put(participant, new ExpectedEvent(participant, action));
        }
        this.expectedClass = expectedClass;
    }

    public ActionLatch(final String player, final Collection<String> participants, final String action) {
        this(player, participants, action, null);
    }

    public ActionLatch(final String player, final Collection<String> participants, final String action, final Class<?> expectedClass) {
        this.expectedClass = expectedClass;
        this.actions.put(player, new ExpectedEvent(player, action));
        for (String participant : participants) {
            this.actions.put(participant, new ExpectedEvent(participant, action));
        }
    }

    @JsonCreator
    public ActionLatch(@JsonProperty("actions") final Collection<? extends PlayerAwareEvent> expectedActions) {
        this.expectedClass = null;
        for (PlayerAwareEvent expectedAction : expectedActions) {
            actions.put(expectedAction.getPlayer(), expectedAction);
        }
    }

    public boolean contains(String participant) {
        return actions.keySet().contains(participant);
    }

    public boolean acted(String player) {
        return !(actions.get(player) instanceof ExpectedEvent);
    }

    public Set<String> fetchParticipants() {
        return actions.keySet();
    }

    @SuppressWarnings("unchecked")
    public <T extends PlayerAwareEvent> Collection<T> getActions() {
        return (Collection<T>) actions.values();
    }

    public Map<String, PlayerAwareEvent> fetchActionsMap() {
        return actions;
    }

    public PlayerAwareEvent fetchAction(String player) {
        return actions.get(player);
    }

    public <T extends PlayerAwareEvent> PlayerAwareEvent put(T action) {
        PlayerAwareEvent event = actions.get(action.getPlayer());
        if (event instanceof ExpectedEvent) {
            if (expectedClass != null && action.getClass() != expectedClass)
                throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayWrongMoveType);
            return actions.put(action.getPlayer(), action);
        } else if (event != null) {
            throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayMoveAlreadyMade);
        }
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayNoMoveExpected);
    }

    public boolean complete() {
        for (PlayerAwareEvent action : actions.values())
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
