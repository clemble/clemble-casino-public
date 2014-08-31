package com.clemble.casino.game;

import java.io.Serializable;
import java.util.*;

import com.clemble.casino.game.action.GameEventRecord;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.game.configuration.GameConfigurationAware;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;

import javax.persistence.*;

@Entity
@Table(name = "GAME_RECORD")
public class GameRecord implements GameConfigurationAware, GameSessionAware, Serializable {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = -6572596573895530995L;

    @EmbeddedId
    @org.springframework.data.annotation.Id
    private String sessionKey;

    @Embedded
    private GameConfiguration configuration;

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
        return sessionKey;
    }

    public GameRecord setSessionKey(String newSession) {
        this.sessionKey= newSession;
        return this;
    }

    @Override
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    public GameRecord setConfiguration(GameConfiguration configurationKey) {
        this.configuration = configurationKey;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameRecord that = (GameRecord) o;

        if (version != that.version) return false;
        if (!configuration.equals(that.configuration)) return false;
        if (!eventRecords.equals(that.eventRecords)) return false;
        if (!players.equals(that.players)) return false;
        if (!sessionKey.equals(that.sessionKey)) return false;
        if (sessionState != that.sessionState) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey.hashCode();
        result = 31 * result + configuration.hashCode();
        result = 31 * result + sessionState.hashCode();
        result = 31 * result + players.hashCode();
        result = 31 * result + eventRecords.hashCode();
        result = 31 * result + version;
        return result;
    }
}
