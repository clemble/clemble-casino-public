package com.clemble.casino.game.lifecycle.management;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;
import com.clemble.casino.game.lifecycle.management.event.MatchStartedEvent;
import com.clemble.casino.game.lifecycle.management.event.RoundStartedEvent;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 09/03/14.
 */
@JsonTypeName("round")
public class RoundGameState implements GameState<RoundGameContext> {

    private RoundState state;
    private RoundGameContext context;
    private RoundGameConfiguration configuration;
    private int version;

    @JsonCreator
    public RoundGameState(
        @JsonProperty("configuration") RoundGameConfiguration configuration,
        @JsonProperty("context") RoundGameContext context,
        @JsonProperty("state") RoundState state,
        @JsonProperty("version") int version) {
        this.configuration = configuration;
        this.context = context;
        this.state = state;
        this.version = version;
    }

    @Override
    public RoundStartedEvent start(){
        return new RoundStartedEvent(context.getSessionKey(), this);
    }

    @Override
    public GameManagementEvent process(Event action) {
        if(action instanceof PlayerAction)
            return state.process((PlayerAction) action, this);
        return null;
    }

    @Override
    public String toKey() {
        return context.getSessionKey();
    }

    @Override
    public RoundGameContext getContext() {
        return context;
    }

    public RoundGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public RoundState getState() {
        return state;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoundGameState)) return false;

        RoundGameState that = (RoundGameState) o;

        if (version != that.version) return false;
        if (!context.equals(that.context)) return false;
        if (!state.equals(that.state)) return false;
        if (!configuration.equals(that.configuration)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + context.hashCode();
        result = 31 * result + configuration.hashCode();
        result = 31 * result + version;
        return result;
    }

}
