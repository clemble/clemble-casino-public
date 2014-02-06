package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.clemble.casino.game.specification.GameConfigurationKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "GAME_POT_RECORD")
public class PotGameRecord implements GameRecord {

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
    @Type(type = "com.clemble.casino.game.GameRecordHibernate")
    @Column(name = "GAME_STATE", length = 40960)
    private List<GameRecord> matchRecords = new ArrayList<GameRecord>();

    public PotGameRecord() {
    }

    @JsonCreator
    public PotGameRecord(@JsonProperty("session") GameSessionKey sessionKey, @JsonProperty("configurationKey") GameConfigurationKey configurationKey,
            @JsonProperty("sessionState") GameSessionState sessionState, @JsonProperty("matchRecords") List<GameRecord> matchRecords) {
        this.sessionKey = sessionKey;
        this.sessionState = sessionState;
        this.configurationKey = configurationKey;
        this.matchRecords.addAll(matchRecords);
    }

    public List<GameRecord> getMatchRecords() {
        return matchRecords;
    }

    public void setMatchRecords(List<GameRecord> sessions) {
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
        PotGameRecord other = (PotGameRecord) obj;
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
