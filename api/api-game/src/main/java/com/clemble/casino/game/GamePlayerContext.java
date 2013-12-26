package com.clemble.casino.game;

import java.io.Serializable;

import com.clemble.casino.game.account.GamePlayerAccount;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlayerContext implements Serializable {

    /**
     * Generated 25/12/13
     */
    private static final long serialVersionUID = -6980469472050707009L;

    final private GamePlayerAccount account;

    final private GamePlayerClock clock;

    final private GamePlayerRole role;

    @JsonCreator
    public GamePlayerContext(@JsonProperty("account") GamePlayerAccount account,
            @JsonProperty("clock") GamePlayerClock clock,
            @JsonProperty("role") GamePlayerRole role) {
        this.account = account;
        this.clock = clock;
        this.role = role;
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
}
