package com.clemble.casino.game.lifecycle.construction;

import com.clemble.casino.ActionLatch;
import com.clemble.casino.lifecycle.construction.ConstructionState;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.ArrayList;
import java.util.List;

@JsonTypeName("automatic")
public class AutomaticGameRequest extends PlayerGameConstructionRequest {

    /**
     * Generated 25/06/13
     */
    private static final long serialVersionUID = -529992778342722143L;

    @JsonCreator
    public AutomaticGameRequest(@JsonProperty("configuration") GameConfiguration configuration) {
        super(configuration);
    }

    @Override
    public GameConstruction toConstruction(String player, String sessionKey) {
        // Step 1. Generate participants
        List<String> participants = new ArrayList<String>();
        participants.add(player);
        // Step 2. Constructing GameConstruction
        return new GameConstruction(sessionKey, player, ConstructionState.pending, new ActionLatch(), configuration, participants);
    }

}
