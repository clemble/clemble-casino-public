package com.clemble.casino.game.lifecycle.record;

import java.util.*;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.GameConfigurationAware;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.record.EventRecord;
import com.clemble.casino.lifecycle.record.Record;
import com.clemble.casino.lifecycle.record.RecordState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.*;

public class GameRecord implements Record<GameConfiguration>, GameConfigurationAware, GameSessionAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = -6572596573895530995L;

    @Id
    final private String sessionKey;
    final private GameConfiguration configuration;
    final private RecordState state;
    final private List<String> players = new ArrayList<String>();
    final private SortedSet<EventRecord> eventRecords = new TreeSet<EventRecord>();
    final private Outcome outcome;

    @JsonCreator
    public GameRecord(
        @JsonProperty("sessionKey") String sessionKey,
        @JsonProperty("configuration") GameConfiguration configuration,
        @JsonProperty("state") RecordState state,
        @JsonProperty("players") Collection<String> players,
        @JsonProperty("eventRecords") Set<EventRecord> eventRecords,
        @JsonProperty("outcome") Outcome outcome) {
        this.sessionKey = sessionKey;
        this.configuration = configuration;
        this.state = state;
        this.players.addAll(players);
        this.eventRecords.addAll(eventRecords);
        this.outcome = outcome;
    }

    @Override
    public String getSessionKey() {
        return sessionKey;
    }

    @Override
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public RecordState getState() {
        return state;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
    }

    public List<String> getPlayers() {
        return players;
    }

    @Override
    public SortedSet<EventRecord> getEventRecords() {
        return eventRecords;
    }

    public GameRecord copy(RecordState state, Outcome outcome) {
        return new GameRecord(
            sessionKey,
            configuration,
            state,
            players,
            eventRecords,
            outcome
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameRecord that = (GameRecord) o;

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
        return result;
    }

}
