package com.clemble.casino.game.construct;

import com.clemble.casino.game.configuration.GameConfiguration;
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
    public AutomaticGameRequest(@JsonProperty(PLAYER) String player, @JsonProperty("configuration") GameConfiguration configuration) {
        super(player, configuration);
    }

}
