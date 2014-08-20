package com.clemble.casino.game.construct;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.specification.GameConfiguration;
import com.clemble.casino.game.specification.GameConfigurationAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameInitiation implements GameConfigurationAware, GameSessionAware {

    /**
     * Generated 10/07/13
     */
    private static final long serialVersionUID = -8584404446775359390L;

    final private String sessionKey;
    final private GameConfiguration configuration;
    final private LinkedHashSet<String> participants = new LinkedHashSet<String>();

    public GameInitiation(GameConstruction construction) {
        this(construction.getSessionKey(), construction.fetchAcceptedParticipants(), construction.getRequest().getConfiguration());
    }

    public GameInitiation(String session, List<String> participants, GameConfiguration specification) {
        this.configuration = checkNotNull(specification);
        this.sessionKey = checkNotNull(session);
        this.participants.addAll(participants);
    }

    public GameInitiation(String session, GameConfiguration specification, Collection<String> participants) {
        this.configuration = checkNotNull(specification);
        this.sessionKey = checkNotNull(session);
        this.participants.addAll(participants);
    }

    @JsonCreator
    public GameInitiation(@JsonProperty(GameSessionAware.SESSION_KEY) String session, @JsonProperty("configuration") GameConfiguration specification,
            @JsonProperty("participants") Collection<String> playerRoles, @JsonProperty("confirmations") Collection<String> confirmations) {
        this.configuration = checkNotNull(specification);
        this.sessionKey = checkNotNull(session);
        this.participants.addAll(playerRoles);
    }

    @Override
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public LinkedHashSet<String> getParticipants() {
        return participants;
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
