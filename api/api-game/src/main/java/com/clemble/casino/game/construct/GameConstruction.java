package com.clemble.casino.game.construct;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

import com.clemble.casino.VersionAware;
import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.event.schedule.InvitationAcceptedEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class GameConstruction implements GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = 2712386710995109913L;

    @Id
    final private String sessionKey;
    final private GameConstructionRequest request;
    final private GameConstructionState state;
    final private ActionLatch responses;

    @JsonCreator
    public GameConstruction(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("request") GameConstructionRequest request,
        @JsonProperty("state") GameConstructionState state,
        @JsonProperty("responses") ActionLatch responses) {
        this.sessionKey = sessionKey;
        this.request = request;
        this.state = state;
        this.responses = responses;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    public GameConstructionRequest getRequest() {
        return request;
    }

    public GameConstructionState getState() {
        return state;
    }

    public ActionLatch getResponses() {
        return responses;
    }

    public List<String> fetchAcceptedParticipants() {
        List<String> acceptedParticipants = new ArrayList<String>(responses.fetchParticipants().size());

        for (PlayerAwareEvent responseEntry : responses.getActions()) {
            if (responseEntry instanceof InvitationAcceptedEvent)
                acceptedParticipants.add(responseEntry.getPlayer());
        }

        return acceptedParticipants;
    }

    public GameInitiation toInitiation() {
        return new GameInitiation(this);
    }

    public GameConstruction cloneWithState(GameConstructionState state) {
        return new GameConstruction(sessionKey, request, state, responses);
    }

    public GameConstruction cloneWithResponses(ActionLatch responses) {
        return new GameConstruction(sessionKey, request, state, responses);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameConstruction that = (GameConstruction) o;

        if (!request.equals(that.request)) return false;
        if (!responses.equals(that.responses)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey.hashCode();
        result = 31 * result + request.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + responses.hashCode();
        return result;
    }

    public static GameConstruction fromAvailability(String sessionKey, AvailabilityGameRequest request) {
        // Step 1. Generating Responses
        ActionLatch responses = new ActionLatch();
        responses.expectNext(request.getParticipants(), InvitationResponseEvent.class);
        responses.put(new InvitationAcceptedEvent(request.getPlayer(), sessionKey));
        // Step 2. Creating new GameConstruction
        return new GameConstruction(sessionKey, request, GameConstructionState.pending, responses);
    }

    public static GameConstruction fromRequest(String sessionKey, GameConstructionRequest request) {
        // Step 1. Generating Responses
        ActionLatch responses = new ActionLatch();
        // Step 2. Creating new GameConstruction
        return new GameConstruction(sessionKey, request, GameConstructionState.pending, responses);
    }

}
