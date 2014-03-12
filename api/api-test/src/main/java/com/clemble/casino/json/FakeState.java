package com.clemble.casino.json;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.RoundGameContext;
import com.clemble.casino.game.RoundGameState;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 22/12/13.
 */
@JsonTypeName("fake")
public class FakeState implements RoundGameState {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = 6467228372170341563L;

    final private RoundGameContext context;
    private int version;

    @JsonCreator
    public FakeState(@JsonProperty("context") RoundGameContext context,
            @JsonProperty("state") GameUnit root,
            @JsonProperty("version") int version) {
        this.context = context;
    }

    @Override
    public GameManagementEvent process(Event action) {
        // Step 1. Processing Select cell move
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayGameEnded);
    }

    @Override
    public GameUnit getState() {
        return null;
    }

    @Override
    public RoundGameContext getContext() {
        return context;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        FakeState fakeState = (FakeState) o;

        if (version != fakeState.version)
            return false;
        if (!context.equals(fakeState.context))
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = context.hashCode();
        result = 31 * result + version;
        return result;
    }
}
