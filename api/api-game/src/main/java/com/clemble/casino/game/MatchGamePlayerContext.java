package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.outcome.PlayerWonOutcome;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchGamePlayerContext implements GamePlayerContext {

    /**
     * Generated 02/02/14
     */
    private static final long serialVersionUID = 1006013553217498447L;

    final private String player;
    final private GamePlayerClock clock;
    final private GamePlayerAccount account;
    final private GamePlayerUnit units;
    final private List<PlayerWonOutcome> wonOutcomes = new ArrayList<PlayerWonOutcome>();

    @JsonCreator
    public MatchGamePlayerContext(
            @JsonProperty(PLAYER) String player,
            @JsonProperty("account") GamePlayerAccount account,
            @JsonProperty("clock") GamePlayerClock clock,
            @JsonProperty("wonOutcomes") List<PlayerWonOutcome> wonOutcomes,
            @JsonProperty("units") GamePlayerUnit units) {
        this.units = units;
        this.clock = clock;
        this.player = player;
        this.account = account;
        this.wonOutcomes.addAll(wonOutcomes);
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

    @Override
    public GamePlayerUnit getUnits() {
        return units;
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

    public static List<MatchGamePlayerContext> construct(GameInitiation initiation) {
        GameConfiguration specification = initiation.getConfiguration();
        List<MatchGamePlayerContext> playerContexts = new ArrayList<MatchGamePlayerContext>();
        for(String player: initiation.getParticipants()) {
            GamePlayerAccount account = new GamePlayerAccount(specification.getPrice().getAmount(), 0, 0);
            GamePlayerClock clock = new GamePlayerClock(0, 0);
            GamePlayerUnit unit = new GamePlayerUnit(initiation.getConfiguration().getPlayerUnits());
            playerContexts.add(new MatchGamePlayerContext(player, account, clock, Collections.<PlayerWonOutcome>emptyList(), unit));
        }
        return playerContexts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchGamePlayerContext)) return false;

        MatchGamePlayerContext that = (MatchGamePlayerContext) o;

        if (account != null ? !account.equals(that.account) : that.account != null) return false;
        if (clock != null ? !clock.equals(that.clock) : that.clock != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (units != null ? !units.equals(that.units) : that.units != null) return false;
        if (wonOutcomes != null ? !wonOutcomes.equals(that.wonOutcomes) : that.wonOutcomes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (clock != null ? clock.hashCode() : 0);
        result = 31 * result + (account != null ? account.hashCode() : 0);
        result = 31 * result + (units != null ? units.hashCode() : 0);
        result = 31 * result + (wonOutcomes != null ? wonOutcomes.hashCode() : 0);
        return result;
    }

}
