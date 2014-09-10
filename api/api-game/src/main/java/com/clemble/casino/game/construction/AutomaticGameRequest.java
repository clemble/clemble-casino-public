package com.clemble.casino.game.construction;

import com.clemble.casino.ActionLatch;
import com.clemble.casino.construction.ConstructionState;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Override
    public GameConstruction toConstruction(String sessionKey) {
        // Step 1. Generate participants
        List<String> participants = new ArrayList<String>();
        participants.add(player);
        // Step 2. Constructing GameConstruction
        return new GameConstruction(sessionKey, player, ConstructionState.pending, new ActionLatch(), configuration, participants);
    }

}
