package com.clemble.casino.game.lifecycle.record;

import java.util.*;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.GameConfigurationAware;
import com.clemble.casino.lifecycle.record.EventRecord;
import com.clemble.casino.lifecycle.record.Record;
import com.clemble.casino.lifecycle.record.RecordState;

import javax.persistence.*;

@Entity
@Table(name = "GAME_RECORD")
public class GameRecord implements Record<GameConfiguration>, GameConfigurationAware, GameSessionAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = -6572596573895530995L;

    @org.springframework.data.annotation.Id
    private String sessionKey;

    private GameConfiguration configuration;

    private RecordState state;

    private List<String> players = new ArrayList<String>();

    private SortedSet<EventRecord> eventRecords = new TreeSet<EventRecord>();

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

    public RecordState getState() {
        return state;
    }

    public GameRecord setState(RecordState gameSessionState) {
        this.state = gameSessionState;
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

    @Override
    public SortedSet<EventRecord> getEventRecords() {
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
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionKey.hashCode();
        result = 31 * result + configuration.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + players.hashCode();
        result = 31 * result + eventRecords.hashCode();
        result = 31 * result + version;
        return result;
    }
}
