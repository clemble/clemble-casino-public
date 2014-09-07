package com.clemble.casino.bet.construction;

import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.bet.configuration.BetConfigurationAware;
import com.clemble.casino.construct.ConstructionRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 9/7/14.
 */
public class BetConstructionRequest implements BetConfigurationAware, ConstructionRequest<BetConfiguration> {

    final private BetConfiguration configuration;

    @JsonCreator
    public BetConstructionRequest(@JsonProperty("configuration") BetConfiguration configuration) {
        this.configuration = configuration;
    }

    public BetConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetConstructionRequest that = (BetConstructionRequest) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return configuration != null ? configuration.hashCode() : 0;
    }
}
