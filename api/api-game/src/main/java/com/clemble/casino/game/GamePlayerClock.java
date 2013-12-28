package com.clemble.casino.game;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by mavarazy on 24/12/13.
 */
public class GamePlayerClock implements PlayerAware {

    /**
     * Generated 29/12/13
     */
    private static final long serialVersionUID = 8179910545586680100L;

    final private String player;

    private long timeSpent;
    private long moveStart;

    @JsonCreator
    public GamePlayerClock(@JsonProperty("player") String player, @JsonProperty("timeSpent") long timeSpent, @JsonProperty("moveStart") long moveStart) {
        this.player = player;
        this.timeSpent = timeSpent;
        this.moveStart = moveStart;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public long getTimeSpent() {
        return timeSpent;
    }

    public long getMoveStart() {
        return moveStart;
    }

    public void markToMove() {
        markToMove(0);
    }

    public void markToMove(long timeout) {
        if (moveStart == 0)
            moveStart = System.currentTimeMillis() + timeout;
    }

    public void markMoved() {
        if (moveStart != 0) {
            long add = System.currentTimeMillis() - moveStart;
            this.timeSpent += add > 0 ? add : 0;
            this.moveStart = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GamePlayerClock that = (GamePlayerClock) o;

        if (moveStart != that.moveStart)
            return false;
        if (timeSpent != that.timeSpent)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (timeSpent ^ (timeSpent >>> 32));
        result = 31 * result + (int) (moveStart ^ (moveStart >>> 32));
        return result;
    }

    public static Collection<GamePlayerClock> fromPlayersList(Collection<String> players) {
        Collection<GamePlayerClock> clocks = new ArrayList<GamePlayerClock>();
        for (String player : players) {
            clocks.add(new GamePlayerClock(player, 0, 0));
        }
        return clocks;
    }
}
