package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.outcome.PlayerWonOutcome;
import com.clemble.casino.game.specification.GameConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PotGamePlayerContext implements GamePlayerContext {

    /**
     * Generated 02/02/14
     */
    private static final long serialVersionUID = 1006013553217498447L;

    final private String player;
    final private GamePlayerClock clock;
    final private GamePlayerAccount account;
    final private List<PlayerWonOutcome> wonOutcomes = new ArrayList<PlayerWonOutcome>();

    @JsonCreator
    public PotGamePlayerContext(
        @JsonProperty("player") String player,
        @JsonProperty("account") GamePlayerAccount account,
        @JsonProperty("clock") GamePlayerClock clock,
        @JsonProperty("wonOutcomes") List<PlayerWonOutcome> outcomes) {
        this.player = player;
        this.clock = clock;
        this.account = account;
        this.wonOutcomes.addAll(outcomes);
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GamePlayerClock getClock() {
        return clock;
    }

    @Override
    public GamePlayerAccount getAccount() {
        return account;
    }

    public List<PlayerWonOutcome> getWonOutcomes() {
        return wonOutcomes;
    }

    public void addOutcome(PlayerWonOutcome outcome) {
        // Step 1. Sanity check
        if (!player.equals(outcome.getWinner()))
            throw new IllegalArgumentException();
        // Step 2. Adding to outcomes
        this.wonOutcomes.add(outcome);
    }

    public static List<PotGamePlayerContext> construct(GameInitiation initiation) {
        GameConfiguration specification = initiation.getConfiguration();
        List<PotGamePlayerContext> playerContexts = new ArrayList<PotGamePlayerContext>();
        for(String player: initiation.getParticipants()) {
            GamePlayerAccount account = new GamePlayerAccount(specification.getPrice());
            GamePlayerClock clock = new GamePlayerClock(0, 0);
            playerContexts.add(new PotGamePlayerContext(player, account, clock, Collections.<PlayerWonOutcome>emptyList()));
        }
        return playerContexts;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((clock == null) ? 0 : clock.hashCode());
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        PotGamePlayerContext other = (PotGamePlayerContext) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (clock == null) {
            if (other.clock != null)
                return false;
        } else if (!clock.equals(other.clock))
            return false;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        if (wonOutcomes == null) {
            if (other.wonOutcomes != null)
                return false;
        } else if (!wonOutcomes.equals(other.wonOutcomes))
            return false;
        return true;
    }

}
