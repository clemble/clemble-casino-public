package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 14/10/14.
 */
@JsonTypeName(TournamentChangedEvent.JSON_TYPE)
public class TournamentChangedEvent extends TournamentEvent {

    final public static String JSON_TYPE = "game:tournament:changed";

    final private String nextSession;
    final private GameInitiation nextInitiation;

    @JsonCreator
    public TournamentChangedEvent(
        @JsonProperty(SESSION_KEY) String sessionKey,
        @JsonProperty("nextSession") String nextSession,
        @JsonProperty("nextInitiation") GameInitiation nextInitiation) {
        super(sessionKey);
        this.nextSession = nextSession;
        this.nextInitiation = nextInitiation;
    }

    public String getNextSession() {
        return nextSession;
    }

    public GameInitiation getNextInitiation() {
        return nextInitiation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentChangedEvent)) return false;
        if (!super.equals(o)) return false;

        TournamentChangedEvent that = (TournamentChangedEvent) o;

        if (!nextInitiation.equals(that.nextInitiation)) return false;
        if (!nextSession.equals(that.nextSession)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + nextSession.hashCode();
        result = 31 * result + nextInitiation.hashCode();
        return result;
    }
}
