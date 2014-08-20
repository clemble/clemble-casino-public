package com.clemble.casino.game;

import java.io.Serializable;
import java.util.*;

import com.clemble.casino.game.action.GameEventRecord;
import com.clemble.casino.game.specification.GameConfigurationKey;
import com.clemble.casino.game.specification.GameConfigurationKeyAware;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;

@Entity
@Table(name = "GAME_RECORD")
public class GameRecord implements GameConfigurationKeyAware, GameSessionAware, Serializable {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = -6572596573895530995L;

    @EmbeddedId
    private String session;

    @Embedded
    private GameConfigurationKey configurationKey;

    @Column(name = "RECORD_STATE")
    private GameSessionState sessionState;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name = "PLAYERS_ORDER")
    @CollectionTable(name = "GAME_RECORD_PLAYER",
        joinColumns = {@JoinColumn(name = "SESSION_ID"), @JoinColumn(name = "GAME")})
    private List<String> players = new ArrayList<String>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Sort(type = SortType.NATURAL)
    @CollectionTable(name = "GAME_RECORD_EVENT",
        uniqueConstraints = @UniqueConstraint(columnNames = {"SESSION_ID", "GAME", "CREATED"}),
        joinColumns = {@JoinColumn(name = "SESSION_ID"), @JoinColumn(name = "GAME")})
    private SortedSet<GameEventRecord> eventRecords = new TreeSet<GameEventRecord>();

    @Version
    @Column(name = "VERSION")
    private int version;

    public GameRecord() {
    }

    @Override
    public String getSessionKey() {
        return session;
    }

    public GameRecord setSessionKey(String newSession) {
        this.session = newSession;
        return this;
    }

    @Override
    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    public GameRecord setConfiguration(GameConfigurationKey configurationKey) {
        this.configurationKey = configurationKey;
        return this;
    }

    public GameSessionState getSessionState() {
        return sessionState;
    }

    public GameRecord setSessionState(GameSessionState gameSessionState) {
        this.sessionState = gameSessionState;
        return this;
    }

    public List<String> getPlayers() {
        return players;
    }

    public GameRecord setPlayers(Collection<String> players) {
        this.players.clear();
        this.players.addAll(players);
        return this;
    }

    public SortedSet<GameEventRecord> getEventRecords() {
        return eventRecords;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((eventRecords == null) ? 0 : eventRecords.hashCode());
        result = prime * result + ((players == null) ? 0 : players.hashCode());
        result = prime * result + ((session == null) ? 0 : session.hashCode());
        result = prime * result + ((sessionState == null) ? 0 : sessionState.hashCode());
        result = prime * result + ((configurationKey == null) ? 0 : configurationKey.hashCode());
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
        GameRecord other = (GameRecord) obj;
        if (eventRecords == null) {
            if (other.eventRecords != null)
                return false;
        } else if (!eventRecords.containsAll(other.eventRecords) || !(other.eventRecords.containsAll(eventRecords)))
            return false;
        if (players == null) {
            if (other.players != null)
                return false;
        } else if (!players.equals(other.players))
            return false;
        if (!session.equals(other.session))
            return false;
        if (sessionState != other.sessionState)
            return false;
        if (configurationKey == null) {
            if (other.configurationKey != null)
                return false;
        } else if (!configurationKey.equals(other.configurationKey))
            return false;
        return true;
    }

}
