package com.clemble.casino.game.construct;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.game.GamePlayerRole;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.utils.CollectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("manager")
public class ManagerGameConstructionRequest extends ServerGameConstructionRequest implements GameSessionAware {

    /**
     * Generated 28/11/13
     */
    private static final long serialVersionUID = 805665880501624573L;

    final private String sessionKey;
    final private List<GamePlayerRole> participants;

    @JsonCreator
    public ManagerGameConstructionRequest(@JsonProperty("participants") Collection<GamePlayerRole> participants, @JsonProperty(SESSION_KEY) String sessionKey, @JsonProperty("configuration") GameConfiguration configuration) {
        super(configuration);
        this.sessionKey = sessionKey;
        this.participants = CollectionUtils.immutableList(participants);
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    public Collection<GamePlayerRole> getParticipants() {
        return participants;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((participants == null) ? 0 : participants.hashCode());
        result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        ManagerGameConstructionRequest other = (ManagerGameConstructionRequest) obj;
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
        return true;
    }

}
