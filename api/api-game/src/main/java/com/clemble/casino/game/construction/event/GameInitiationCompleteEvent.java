package com.clemble.casino.game.construction.event;

import com.clemble.casino.game.construction.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/17/14.
 */
@JsonTypeName("game:initiation:complete")
public class GameInitiationCompleteEvent extends GameInitiationEvent {

    @JsonCreator
    public GameInitiationCompleteEvent(@JsonProperty(SESSION_KEY) String sessionKey, @JsonProperty("initiation") GameInitiation initiation) {
        super(sessionKey, initiation);
    }

}
