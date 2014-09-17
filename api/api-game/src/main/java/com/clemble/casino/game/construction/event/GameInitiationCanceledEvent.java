package com.clemble.casino.game.construction.event;

import java.util.Set;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.construction.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("game:initiation:canceled")
public class GameInitiationCanceledEvent extends GameInitiationEvent {

    /**
     * Generated 04/01/14
     */
    private static final long serialVersionUID = -2108886096839930339L;

    final private Set<String> confirmed;

    @JsonCreator
    public GameInitiationCanceledEvent(@JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey,
            @JsonProperty("initiation") GameInitiation initiation,
            @JsonProperty("confirmed") Set<String> confirmed) {
        super(sessionKey, initiation);
        this.confirmed = confirmed;
    }

    public Set<String> getConfirmed(){
        return confirmed;
    }

    public String toString() {
        return "initiationCanceled:" + super.toString();
    }

}
