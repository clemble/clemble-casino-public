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
    final private List<PotGamePlayerContext> playerContexts;

    @JsonCreator
    public PotGameContext(@JsonProperty("playerContexts") List<PotGamePlayerContext> playerContexts,
            @JsonProperty(value = "parent", required = false) GameContext parent, @JsonProperty("pot") long pot) {
        super(parent);
        this.pot = pot;
        this.playerContexts = playerContexts;
    }

    public PotGamePlayerContext get(String player) {
        if (player == null)
            throw new IllegalArgumentException();
        for (PotGamePlayerContext context : playerContexts)
            if (player.equals(context.getPlayer()))
                return context;
        return null;
    }

    public List<PotGamePlayerContext> getPlayerContexts() {
        return playerContexts;
    }

    public long getPot() {
        return pot;
    }
    
    public void add(long addition) {
        this.pot += addition;
    }
}
