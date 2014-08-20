package com.clemble.casino.game.iterator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("sequential")
public class SequentialPlayerIterator implements GamePlayerIterator {

    /**
     * Generated 12/04/13
     */
    private static final long serialVersionUID = -4182637038671660855L;

    private int index;
    final private List<String> players;

    public static SequentialPlayerIterator fromCollection(Collection<String> playerIds) {
        return new SequentialPlayerIterator(0, new ArrayList<String>(playerIds));
    }

    @JsonCreator
    public SequentialPlayerIterator(@JsonProperty("index") int index, @JsonProperty("players") List<String> players) {
        this.index = index;
        this.players = players;
    }

    @Override
    public String next() {
        return players.get(++index % getPlayers().size());
    }

    @Override
    public String current() {
        return players.get(index % getPlayers().size());
    }

    @Override
    public Collection<String> getPlayers() {
        return players;
    }

    @Override
    public Collection<String> whoIsOpponents(String playerId) {
        Collection<String> playerIds = new ArrayList<String>(players);
        playerIds.remove(playerId);
        return playerIds;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public boolean contains(String targetPlayerId) {
        for (String playerId : players) {
            if (playerId.equals(targetPlayerId))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + index;
        result = prime * result + players.hashCode();
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
        SequentialPlayerIterator other = (SequentialPlayerIterator) obj;
        if (index != other.index)
            return false;
        if (!players.equals(other.players))
            return false;
        return true;
    }

    public static SequentialPlayerIterator create(Collection<? extends PlayerAware> playerAwares) {
        List<String> playerIds = new ArrayList<String>(playerAwares.size());
        // Parsing player aware values
        for (PlayerAware playerAware : playerAwares) {
            playerIds.add(playerAware.getPlayer());
        }
        return SequentialPlayerIterator.fromCollection(playerIds);
    }

}
