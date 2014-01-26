package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.Collection;

import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.game.outcome.PlayerWonOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePotContext {

    final private long potSize;
    final private Collection<GameOutcome> outcomes;

    @JsonCreator
    public GamePotContext(@JsonProperty("potSize") long potSize, @JsonProperty("outcomes") Collection<GameOutcome> outcomes) {
        this.potSize = potSize;
        this.outcomes = outcomes;
    }

    public long getPotSize() {
        return potSize;
    }

    public Collection<GameOutcome> getOutcomes() {
        return outcomes;
    }

    public Collection<GameOutcome> getOutcomes(String player) {
        // Step 1. Checking game outcomes
        Collection<GameOutcome> outcomesForPlayer = new ArrayList<GameOutcome>();
        // Step 2. Filtering valid outcomes
        for (GameOutcome outcome : outcomes) {
            if (outcome instanceof PlayerWonOutcome && player.equals(((PlayerWonOutcome) outcome).getWinner()))
                outcomesForPlayer.add(outcome);
        }
        // Step 3. Returning aggregated result
        return outcomesForPlayer;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((outcomes == null) ? 0 : outcomes.hashCode());
        result = prime * result + (int) (potSize ^ (potSize >>> 32));
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
        GamePotContext other = (GamePotContext) obj;
        if (outcomes == null) {
            if (other.outcomes != null)
                return false;
        } else if (!outcomes.equals(other.outcomes))
            return false;
        if (potSize != other.potSize)
            return false;
        return true;
    }
}
