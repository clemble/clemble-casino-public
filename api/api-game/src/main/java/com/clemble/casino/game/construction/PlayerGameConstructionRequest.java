package com.clemble.casino.game.construction;

import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
abstract public class PlayerGameConstructionRequest extends GameConstructionRequest implements PlayerAware {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 4949060894194971610L;

    final protected String player;

    public PlayerGameConstructionRequest(String player, GameConfiguration configuration) {
        super(configuration);
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((player == null) ? 0 : player.hashCode());
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
        PlayerGameConstructionRequest other = (PlayerGameConstructionRequest) obj;
        if (player == null) {
            if (other.player != null)
                return false;
        } else if (!player.equals(other.player))
            return false;
        return true;
    }

}
