package com.clemble.casino.game.lifecycle.management.outcome;

import com.clemble.casino.player.WinnerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("playerWon")
// TODO make PlayerAware
public class PlayerWonOutcome extends GameOutcome implements WinnerAware {

    /**
     * Generated 22/01/14
     */
    private static final long serialVersionUID = -3259192146118069428L;

    final private String winner;

    @JsonCreator
    public PlayerWonOutcome(@JsonProperty("winner") String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public int hashCode() {
        return winner == null ? 0 : winner.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlayerWonOutcome other = (PlayerWonOutcome) obj;
        return winner.equals(other.winner);
    }

    @Override
    public String toString(){
        return "won:" + winner;
    }
}
