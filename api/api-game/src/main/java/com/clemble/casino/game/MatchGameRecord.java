package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.clemble.casino.game.specification.GameConfigurationKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name = "GAME_POT_RECORD")
@JsonTypeName("match")
public class MatchGameRecord implements GameRecord {

    /**
     * Generated 28/01/14
     */
    private static final long serialVersionUID = 2681729492829267205L;

    @EmbeddedId
    private GameSessionKey sessionKey;
    @Column(name = "RECORD_STATE")
    @Enumerated(EnumType.STRING)
    private GameSessionState sessionState;
    @Embedded
    private GameConfigurationKey configurationKey;
    @ElementCollection
    @CollectionTable(name =  "GAME_POT_TO_SUB_RECORDS",
        joinColumns = {
            @JoinColumn(referencedColumnName = "SESSION_ID", name = "POT_SESSION_ID"),
            @JoinColumn(referencedColumnName = "GAME", name = "POT_GAME")
        }
    )
    private List<GameSessionKey> matchRecords = new ArrayList<GameSessionKey>();

    public MatchGameRecord() {
    }

    @JsonCreator
    public MatchGameRecord(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("configurationKey") GameConfigurationKey configurationKey,
                           @JsonProperty("sessionState") GameSessionState sessionState, @JsonProperty("matchRecords") List<GameSessionKey> matchRecords) {
        this.sessionKey = sessionKey;
        this.sessionState = sessionState;
        this.configurationKey = configurationKey;
        if(matchRecords != null)
            this.matchRecords.addAll(matchRecords);
    }

    public List<GameSessionKey> getSubRecords() {
        return matchRecords;
    }

    public void setSubRecords(List<GameSessionKey> sessions) {
        this.matchRecords = sessions;
    }

    @Override
    public GameSessionKey getSession() {
        return sessionKey;
    }

    public void setSession(GameSessionKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    public void serConfigurationKey(GameConfigurationKey configurationKey) {
        this.configurationKey = configurationKey;
    }

    @Override
    public GameSessionState getSessionState() {
        return sessionState;
    }

    public void setSessionState(GameSessionState sessionState) {
        this.sessionState = sessionState;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((configurationKey == null) ? 0 : configurationKey.hashCode());
        result = prime * result + ((matchRecords == null) ? 0 : matchRecords.hashCode());
        result = prime * result + ((sessionKey == null) ? 0 : sessionKey.hashCode());
        result = prime * result + ((sessionState == null) ? 0 : sessionState.hashCode());
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
        MatchGameRecord other = (MatchGameRecord) obj;
        if (configurationKey == null) {
            if (other.configurationKey != null)
                return false;
        } else if (!configurationKey.equals(other.configurationKey))
            return false;
        if (matchRecords == null) {
            if (other.matchRecords != null)
                return false;
        } else if (!matchRecords.equals(other.matchRecords))
            return false;
        if (sessionKey == null) {
            if (other.sessionKey != null)
                return false;
        } else if (!sessionKey.equals(other.sessionKey))
            return false;
        if (sessionState != other.sessionState)
            return false;
        return true;
    }
}
