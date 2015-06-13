package com.clemble.casino.bet.construction;

import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.lifecycle.construction.Construction;
import com.clemble.casino.lifecycle.construction.ConstructionState;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 9/7/14.
 */
public class BetConstruction implements Construction<BetConfiguration> {

    final private ConstructionState state;
    final private BetConfiguration configuration;

    @JsonCreator
    public BetConstruction(
        @JsonProperty("configuration") BetConfiguration configuration,
        @JsonProperty("state") ConstructionState state) {
        this.state = state;
        this.configuration = configuration;
    }

    @Override
    public ConstructionState getState() {
        return state;
    }

    @Override
    public BetConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetConstruction that = (BetConstruction) o;

        if (!configuration.equals(that.configuration)) return false;
        return state == that.state;

    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + configuration.hashCode();
        return result;
    }

}
