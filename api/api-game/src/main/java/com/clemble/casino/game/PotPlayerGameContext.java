package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.List;

import com.clemble.casino.game.outcome.PlayerWonOutcome;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PotPlayerGameContext implements PlayerAware {

    /**
     * Generated 02/02/14
     */
    private static final long serialVersionUID = 1006013553217498447L;

    private long pot; // TODO make this immutable
    final private String player;
    final private List<PlayerWonOutcome> wonOutcomes = new ArrayList<PlayerWonOutcome>();

    @JsonCreator
    public PotPlayerGameContext(@JsonProperty("player") String player, @JsonProperty("pot") long pot,
            @JsonProperty("wonOutcomes") List<PlayerWonOutcome> outcomes) {
        this.pot = pot;
        this.player = player;
        this.wonOutcomes.addAll(outcomes);
    }

    public void add(long amount) {
        this.pot += amount;
    }

    public void add(PlayerWonOutcome wonOutcome) {
        if (player.equals(wonOutcome.getWinner()))
            wonOutcomes.add(wonOutcome);
    }

    public long getPot() {
        return pot;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public List<PlayerWonOutcome> getWonOutcomes() {
        return wonOutcomes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((player == null) ? 0 : player.hashCode());
        result = prime * result + (int) (pot ^ (pot >>> 32));
        result = prime * result + ((wonOutcomes == null) ? 0 : wonOutcomes.hashCode());
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
        PotPlayerGameContext other = (PotPlayerGameContext) obj;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (pot != other.pot)
            return false;
        if (wonOutcomes == null) {
            if (other.wonOutcomes != null)
                return false;
        } else if (!wonOutcomes.equals(other.wonOutcomes))
            return false;
        return true;
    }

}
