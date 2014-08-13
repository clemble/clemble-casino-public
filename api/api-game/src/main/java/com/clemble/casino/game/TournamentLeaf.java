package com.clemble.casino.game;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * Created by mavarazy on 15/02/14.
 */
public class TournamentLeaf implements GameSessionAware, PlayerAware{

    /**
     * Generated 
     */
    private static final long serialVersionUID = 4460145959220006489L;

    final private String player;
    final private GameSessionKey sessionKey;
    final private Collection<TournamentLeaf> leafs;

    @JsonCreator
    public TournamentLeaf(@JsonProperty("player") String player,
                          @JsonProperty(GameSessionAware.SESSION_KEY) GameSessionKey sessionKey,
                          @JsonProperty("leafs") Collection<TournamentLeaf> leafs) {
        this.player = player;
        this.sessionKey = sessionKey;
        this.leafs = leafs;

    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GameSessionKey getSessionKey(){
        return sessionKey;
    }

    public Collection<TournamentLeaf> getLeafs() {
        return leafs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournamentLeaf that = (TournamentLeaf) o;

        if (leafs != null ? !leafs.equals(that.leafs) : that.leafs != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (sessionKey != null ? !sessionKey.equals(that.sessionKey) : that.sessionKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (sessionKey != null ? sessionKey.hashCode() : 0);
        result = 31 * result + (leafs != null ? leafs.hashCode() : 0);
        return result;
    }

}
