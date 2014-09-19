package com.clemble.casino.game.construction;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.LinkedHashSet;

import com.clemble.casino.construction.Initiation;
import com.clemble.casino.construction.InitiationState;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.game.configuration.GameConfigurationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameInitiation implements Initiation<GameConfiguration>, GameConfigurationAware, GameParticipantsAware, GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = -8584404446775359390L;

    final private String sessionKey;
    final private InitiationState state;
    final private GameConfiguration configuration;
    final private LinkedHashSet<String> participants = new LinkedHashSet<String>();

    @JsonCreator
    public GameInitiation(
        @JsonProperty(GameSessionAware.SESSION_KEY) String session,
        @JsonProperty("state") InitiationState state,
        @JsonProperty("participants") Collection<String> participants,
        @JsonProperty("configuration") GameConfiguration specification) {
        this.state = state;
        this.sessionKey = checkNotNull(session);
        this.configuration = checkNotNull(specification);
        this.participants.addAll(participants);
    }

    @Override
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public LinkedHashSet<String> getParticipants() {
        return participants;
    }

    @Override
    public InitiationState getState() {
        return state;
    }

    public boolean isParticipates(String player) {
        return participants.contains(player);
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
        result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
        result = prime * result + ((configuration == null) ? 0 : configuration.hashCode());
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
        GameInitiation other = (GameInitiation) obj;
        if (participants == null) {
            if (other.participants != null)
                return false;
        } else if (!participants.equals(other.participants))
            return false;
        if (sessionKey == null) {
            if (other.sessionKey != null)
                return false;
        } else if (!sessionKey.equals(other.sessionKey))
            return false;
        if (configuration == null) {
            if (other.configuration != null)
                return false;
        } else if (!configuration.equals(other.configuration))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "initiation:" + sessionKey + ":" + participants;
    }

}
