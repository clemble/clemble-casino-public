package com.clemble.casino.game.lifecycle.construction;

import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.GameConfigurationAware;
import com.clemble.casino.lifecycle.construction.ConstructionRequest;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
abstract public class GameConstructionRequest implements GameConfigurationAware, ConstructionRequest<GameConfiguration> {

    /**
     * Generated 28/11/13
     */
    private static final long serialVersionUID = -7124151122750295287L;

    final protected GameConfiguration configuration;

    public GameConstructionRequest(GameConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public int hashCode() {
        return ((configuration == null) ? 0 : configuration.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GameConstructionRequest other = (GameConstructionRequest) obj;
        if (configuration == null) {
            if (other.configuration != null)
                return false;
        } else if (!configuration.equals(other.configuration))
            return false;
        return true;
    }

}
