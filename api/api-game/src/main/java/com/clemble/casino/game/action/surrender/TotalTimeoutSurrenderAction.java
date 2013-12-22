package com.clemble.casino.game.action.surrender;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("totalTimeBreached")
public class TotalTimeoutSurrenderAction extends SurrenderAction {

    /**
     * Generated 10/06/13
     */
    private static final long serialVersionUID = 6999945454488627240L;

    @JsonCreator
    public TotalTimeoutSurrenderAction(@JsonProperty(PlayerAware.JSON_ID) String playerId) {
        super(playerId);
    }

}
