package com.clemble.casino.error;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ClembleCasinoFailure implements PlayerAware {

    /**
     * Generated 12/06/13
     */
    private static final long serialVersionUID = 8151325637613551745L;

    final private String player;
    final private ClembleCasinoError error;

    public ClembleCasinoFailure(final ClembleCasinoError error) {
        this(PlayerAware.DEFAULT_PLAYER, error);
    }

    public ClembleCasinoFailure(final ClembleCasinoError error, final String playerId) {
        this(playerId, error);
    }

    @JsonCreator
    public ClembleCasinoFailure(
        @JsonProperty(PlayerAware.JSON_ID) final String player,
        @JsonProperty("error") final ClembleCasinoError error) {
        this.player = player;
        this.error = error;
    }

    public ClembleCasinoError getError() {
        return error;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "ClembleCasinoFailure [error = " + error + ", player = " + player + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((error == null) ? 0 : error.hashCode());
        result = prime * result + (int) (player != null ? player.hashCode() : 0);
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
        if (!player.equals(other.player))
            return false;
        return true;
    }

    public static Collection<ClembleCasinoFailure> construct(ClembleCasinoError error, Collection<String> players) {
        List<ClembleCasinoFailure> failures = new ArrayList<ClembleCasinoFailure>();
        for(String player: players)
            failures.add(new ClembleCasinoFailure(error, player));
        return failures;
    }

}
