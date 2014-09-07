package com.clemble.casino.game.construct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.clemble.casino.construct.ConstructionState;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;

import com.clemble.casino.ActionLatch;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.event.schedule.InvitationAcceptedEvent;
import com.clemble.casino.construct.Construction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class GameConstruction implements Construction<GameConfiguration>, GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = 2712386710995109913L;

    @Id
    final private String sessionKey;
    final private ConstructionState state;
    final private Collection<String> participants;
    final private GameConfiguration configuration;
    final private GameConstructionRequest request;
    final private ActionLatch responses;

    @JsonCreator
    public GameConstruction(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("request") GameConstructionRequest request,
        @JsonProperty("state") ConstructionState state,
        @JsonProperty("responses") ActionLatch responses,
        @JsonProperty("configuration") GameConfiguration configuration,
        @JsonProperty("participants") Collection<String> participants) {
        this.sessionKey = sessionKey;
        this.request = request;
        this.configuration = configuration;
        this.participants = participants;
        this.state = state;
        this.responses = responses;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public GameConfiguration getConfiguration(){
        return configuration;
    }

    public GameConstructionRequest getRequest() {
        return request;
    }

    @Override
    public ConstructionState getState() {
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

    public GameConstruction cloneWithState(ConstructionState state) {
        return new GameConstruction(sessionKey, request, state, responses, configuration, participants);
    }

    public GameConstruction cloneWithResponses(ActionLatch responses) {
        return new GameConstruction(sessionKey, request, state, responses, configuration, participants);
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

    public static GameConstruction fromRequest(String sessionKey, GameConstructionRequest request) {
        // Step 1. Generating empty Responses
        ActionLatch responses = new ActionLatch();
        if (request instanceof AvailabilityGameRequest) {
            // Step 2. In case of Availability request construct with expected responses
            AvailabilityGameRequest aRequest = (AvailabilityGameRequest) request;
            responses.expectNext(aRequest.getParticipants(), InvitationResponseEvent.class);
            responses.put(new InvitationAcceptedEvent(aRequest.getPlayer(), sessionKey));
            // Step 2. Creating new GameConstruction
            return new GameConstruction(sessionKey, request, ConstructionState.pending, responses, request.getConfiguration(), aRequest.getParticipants());
        }if (request instanceof PlayerGameConstructionRequest) {
            String player = ((PlayerGameConstructionRequest) request).getPlayer();
            List<String> participants = new ArrayList<String>();
            participants.add(player);
            // Step 2. Creating new GameConstruction
            return new GameConstruction(sessionKey, request, ConstructionState.pending, responses, request.getConfiguration(), participants);
        } else {
            return new GameConstruction(sessionKey, request, ConstructionState.pending, responses, request.getConfiguration(), new ArrayList<String>());
        }
    }

}
