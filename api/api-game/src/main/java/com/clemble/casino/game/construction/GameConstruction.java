package com.clemble.casino.game.construction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import com.clemble.casino.lifecycle.construction.Construction;
import com.clemble.casino.lifecycle.construction.ConstructionState;
import com.clemble.casino.lifecycle.initiation.InitiationState;
import com.clemble.casino.game.configuration.GameConfiguration;

import com.clemble.casino.ActionLatch;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.construction.event.GameInvitationAcceptedEvent;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

public class GameConstruction implements Construction<GameConfiguration>, PlayerAware, GameSessionAware, GameParticipantsAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = 2712386710995109913L;

    @Id
    final private String sessionKey;
    final private String player;
    final private ConstructionState state;
    final private Collection<String> participants;
    final private GameConfiguration configuration;
    final private ActionLatch responses;

    @JsonCreator
    public GameConstruction(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("player") String player,
        @JsonProperty("state") ConstructionState state,
        @JsonProperty("responses") ActionLatch responses,
        @JsonProperty("configuration") GameConfiguration configuration,
        @JsonProperty("participants") Collection<String> participants) {
        this.sessionKey = sessionKey;
        this.player = player;
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
    public String getPlayer() {
        return player;
    }

    @Override
    public GameConfiguration getConfiguration(){
        return configuration;
    }

    @Override
    public Collection<String> getParticipants() {
        return participants;
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

        for (PlayerEvent responseEntry : responses.getActions()) {
            if (responseEntry instanceof GameInvitationAcceptedEvent)
                acceptedParticipants.add(responseEntry.getPlayer());
        }

        return acceptedParticipants;
    }

    @Override
    public GameInitiation toInitiation() {
        return new GameInitiation(sessionKey, InitiationState.pending, fetchAcceptedParticipants(), configuration);
    }

    public GameConstruction cloneWithState(ConstructionState state) {
        return new GameConstruction(sessionKey, player, state, responses, configuration, participants);
    }

    public GameConstruction cloneWithResponses(ActionLatch responses) {
        return new GameConstruction(sessionKey, player, state, responses, configuration, participants);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameConstruction that = (GameConstruction) o;

        if (!player.equals(that.player)) return false;
        if (!responses.equals(that.responses)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + responses.hashCode();
        return result;
    }

}
