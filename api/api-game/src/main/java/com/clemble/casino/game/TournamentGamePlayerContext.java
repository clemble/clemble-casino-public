package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.List;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.specification.GameConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TournamentGamePlayerContext implements GamePlayerContext {

    /**
     * Generated 15/02/14
     */
    private static final long serialVersionUID = -4672061886105008404L;

    final private String player;
    final private GamePlayerClock clock;
    final private GamePlayerAccount account;

    @JsonCreator
    public TournamentGamePlayerContext(
        @JsonProperty("player") String player,
        @JsonProperty("account") GamePlayerAccount account,
        @JsonProperty("clock") GamePlayerClock clock) {
        this.player = player;
        this.clock = clock;
        this.account = account;
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

    public static List<TournamentGamePlayerContext> construct(GameInitiation initiation) {
        GameConfiguration specification = initiation.getConfiguration();
        List<TournamentGamePlayerContext> playerContexts = new ArrayList<TournamentGamePlayerContext>();
        for(String player: initiation.getParticipants()) {
            GamePlayerAccount account = new GamePlayerAccount(specification.getPrice());
            GamePlayerClock clock = new GamePlayerClock(0, 0);
            playerContexts.add(new TournamentGamePlayerContext(player, account, clock));
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
        TournamentGamePlayerContext other = (TournamentGamePlayerContext) obj;
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
        return true;
    }

}
