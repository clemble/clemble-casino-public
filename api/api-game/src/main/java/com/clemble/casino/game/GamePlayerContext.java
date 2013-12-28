package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.List;

import com.clemble.casino.game.account.GamePlayerAccount;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.specification.GameSpecification;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlayerContext implements PlayerAware {

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -6980469472050707009L;

    final private String player;
    final private GamePlayerAccount account;
    final private GamePlayerClock clock;
    final private GamePlayerRole role;

    @JsonCreator
    public GamePlayerContext(
            @JsonProperty("player") String player,
            @JsonProperty("account") GamePlayerAccount account,
            @JsonProperty("clock") GamePlayerClock clock,
            @JsonProperty("role") GamePlayerRole role) {
        this.player = player;
        this.account = account;
        this.clock = clock;
        this.role = role;
    }

    @Override
    public String getPlayer(){
        return player;
    }

    public GamePlayerAccount getAccount() {
        return account;
    }

    public GamePlayerClock getClock() {
        return clock;
    }

    public GamePlayerRole getRole() {
        return role;
    }

    public static List<GamePlayerContext> construct(GameInitiation initiation) {
        List<GamePlayerContext> playerContexts = new ArrayList<GamePlayerContext>();
        GameSpecification specification = initiation.getSpecification();
        for(GamePlayerRole role: initiation.getParticipants()) {
            GamePlayerAccount account = new GamePlayerAccount(role.getPlayer(), specification);
            GamePlayerClock clock = new GamePlayerClock(role.getPlayer(), 0, 0);
            playerContexts.add(new GamePlayerContext(role.getPlayer(), account, clock, role));
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
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        GamePlayerContext other = (GamePlayerContext) obj;
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
        if (role == null) {
            if (other.role != null)
                return false;
        } else if (!role.equals(other.role))
            return false;
        return true;
    }
}
