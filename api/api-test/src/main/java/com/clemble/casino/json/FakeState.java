package com.clemble.casino.json;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.game.GameContext;
import com.clemble.casino.game.GameSession;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.game.unit.GameUnit;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 22/12/13.
 */
@JsonTypeName("fake")
public class FakeState implements GameState {

    /**
     * Generated 20/12/13
     */
    private static final long serialVersionUID = 6467228372170341563L;

    final private GameContext context;
    private GameOutcome outcome;
    private int version;

    @JsonCreator
    public FakeState(@JsonProperty("context") GameContext context,
            @JsonProperty("root") GameUnit root,
            @JsonProperty("outcome") GameOutcome outcome,
            @JsonProperty("version") int version) {
        this.context = context;
        this.outcome = outcome;
    }

    @Override
    public <State extends GameState> GameManagementEvent process(GameSession<State> session, GameAction action) {
        // Step 1. Processing Select cell move
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayGameEnded);
    }

    @Override
    public GameUnit getRoot() {
        return null;
    }

    @Override
    public GameContext getContext() {
        return context;
    }

    @Override
    public GameOutcome getOutcome() {
        return outcome;
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
        if (outcome != null ? !outcome.equals(fakeState.outcome) : fakeState.outcome != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = context.hashCode();
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
