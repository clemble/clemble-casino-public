package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.hibernate.annotations.Type;

import com.clemble.casino.VersionAware;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.action.MadeMove;
import com.clemble.casino.game.specification.GameConfigurationKey;

@Entity
@Table(name = "GAME_SESSION")
@JsonTypeName("round")
public class RoundGameRecord implements GameRecord, VersionAware {

    /**
     * Generated 16/02/13
     */
    private static final long serialVersionUID = -6572596573895530995L;

    @EmbeddedId
    private GameSessionKey session;

    @Embedded
    private GameConfigurationKey configurationKey;

    @Column(name = "SESSION_STATE")
    private GameSessionState sessionState;

    @ElementCollection(fetch = FetchType.EAGER)
    @OrderColumn(name = "PLAYERS_ORDER")
    @CollectionTable(name = "GAME_SESSION_PLAYERS", joinColumns = {@JoinColumn(name = "SESSION_ID"), @JoinColumn(name = "GAME")})
    private List<String> players = new ArrayList<String>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "GAME_SESSION_MOVES", joinColumns = {@JoinColumn(name = "SESSION_ID"), @JoinColumn(name = "GAME")})
    private List<MadeMove> madeMoves = new ArrayList<MadeMove>();

    @Version
    @Column(name = "VERSION")
    private int version;

    public RoundGameRecord() {
    }

    @Override
    public GameSessionKey getSession() {
        return session;
    }

    public RoundGameRecord setSession(GameSessionKey newSession) {
        this.session = newSession;
        return this;
    }

    @Override
    public GameConfigurationKey getConfigurationKey() {
        return configurationKey;
    }

    public RoundGameRecord setConfiguration(GameConfigurationKey configurationKey) {
        this.configurationKey = configurationKey;
        return this;
    }

    @Override
    public GameSessionState getSessionState() {
        return sessionState;
    }

    public RoundGameRecord setSessionState(GameSessionState gameSessionState) {
        this.sessionState = gameSessionState;
        return this;
    }

    public List<String> getPlayers() {
        return players;
    }

    public RoundGameRecord setPlayers(Collection<String> players) {
        this.players.clear();
        this.players.addAll(players);
        return this;
    }

    public void addPlayer(String player) {
        this.players.add(player);
    }

    public void addPlayers(Collection<String> players) {
        this.players.addAll(players);
    }

    public List<MadeMove> getMadeMoves() {
        return madeMoves;
    }

    public void addMadeMove(GameAction madeMove) {
        MadeMove move = new MadeMove().setMove(madeMove).setMoveId(version + 1).setProcessingTime(System.currentTimeMillis());
        this.madeMoves.add(move);
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
        result = prime * result + ((madeMoves == null) ? 0 : madeMoves.hashCode());
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
        RoundGameRecord other = (RoundGameRecord) obj;
        if (madeMoves == null) {
            if (other.madeMoves != null)
                return false;
        } else if (!madeMoves.containsAll(other.madeMoves) || !(other.madeMoves.containsAll(madeMoves)))
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
