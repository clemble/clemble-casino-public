package com.clemble.casino.error;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClembleCasinoFailure implements PlayerAware, GameSessionAware {

    /**
     * Generated 12/06/13
     */
    private static final long serialVersionUID = 8151325637613551745L;

    final private String player;
    final private ClembleCasinoError error;
    final private GameSessionKey session;

    public ClembleCasinoFailure(final ClembleCasinoError error) {
        this(error, PlayerAware.DEFAULT_PLAYER, GameSessionAware.DEFAULT_SESSION);
    }

    public ClembleCasinoFailure(final ClembleCasinoError error, final String playerId) {
        this(error, playerId, GameSessionAware.DEFAULT_SESSION);
    }

    @JsonCreator
    public ClembleCasinoFailure(@JsonProperty("error") final ClembleCasinoError error,
            @JsonProperty(PlayerAware.JSON_ID) final String player,
            @JsonProperty("session") final GameSessionKey session) {
        this.error = error;
        this.session = session;
        this.player = player;
    }

    public ClembleCasinoError getError() {
        return error;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GameSessionKey getSession() {
        return session;
    }

    @Override
    public String toString() {
        return "ClembleCasinoFailure [error = " + error + ", player = " + player + ", session = " + session + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((error == null) ? 0 : error.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + ((session == null) ? 0 : session.hashCode());
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
        ClembleCasinoFailure other = (ClembleCasinoFailure) obj;
        if (error != other.error)
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (session == null) {
            if (other.session != null)
                return false;
        } else if (!session.equals(other.session))
            return false;
        return true;
    }

}
