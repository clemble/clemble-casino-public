package com.clemble.casino.game;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.Map;

/**
 * Created by mavarazy on 24/12/13.
 */
public class GameClock {

    private Map<String, GamePlayerClock> playerToClock;

    public GameClock(GameInitiation initiation) {
        this(GamePlayerClock.fromPlayersList(initiation.getParticipants()));
    }

    @JsonCreator
    public GameClock(@JsonProperty("clocks") Collection<GamePlayerClock> clocks) {
        this.playerToClock = PlayerAwareUtils.toImmutableMap(clocks);
    }

    public GamePlayerClock getClock(String player) {
        return playerToClock.get(player);
    }

    public Collection<GamePlayerClock> getClocks() {
        return playerToClock.values();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameClock gameClock = (GameClock) o;

        if (playerToClock != null ? !playerToClock.equals(gameClock.playerToClock) : gameClock.playerToClock != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return playerToClock != null ? playerToClock.hashCode() : 0;
    }
}
