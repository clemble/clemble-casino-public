package com.clemble.casino.game.construct;

import com.clemble.casino.game.specification.GameSpecification;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("automatic")
public class AutomaticGameRequest extends PlayerGameConstructionRequest {

    /**
     * Generated 25/06/13
     */
    private static final long serialVersionUID = -529992778342722143L;

    @JsonCreator
    public AutomaticGameRequest(@JsonProperty(PlayerAware.JSON_ID) String player, @JsonProperty("specification") GameSpecification specification) {
        super(player, specification);
    }

}
