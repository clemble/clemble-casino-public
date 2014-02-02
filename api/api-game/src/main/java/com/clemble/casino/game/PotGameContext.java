package com.clemble.casino.game;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potContext")
public class PotGameContext extends GameContext {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 554706514619333631L;

    private long pot; // TODO make this immutable
    final private List<PotPlayerGameContext> playerContexts;

    @JsonCreator
    public PotGameContext(@JsonProperty("playerContexts") List<PotPlayerGameContext> playerContexts,
            @JsonProperty(value = "parent", required = false) GameContext parent, @JsonProperty("pot") long pot) {
        super(parent);
        this.pot = pot;
        this.playerContexts = playerContexts;
    }

    public PotPlayerGameContext get(String player) {
        if (player == null)
            throw new IllegalArgumentException();
        for (PotPlayerGameContext context : playerContexts)
            if (player.equals(context.getPlayer()))
                return context;
        return null;
    }

    public void add(String player, long amount) {
        this.pot += amount;
        get(player).add(amount);
    }

    public List<PotPlayerGameContext> getPlayerContexts() {
        return playerContexts;
    }

    public long getPot() {
        return pot;
    }
}
