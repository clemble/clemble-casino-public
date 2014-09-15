package com.clemble.casino.game.construction;

import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
abstract public class PlayerGameConstructionRequest extends GameConstructionRequest {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 4949060894194971610L;

    public PlayerGameConstructionRequest(GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        return true;
    }

}
