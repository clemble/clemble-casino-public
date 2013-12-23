package com.clemble.casino.json;

import com.clemble.casino.base.ActionLatch;
import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.game.GameSession;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.account.GameAccount;
import com.clemble.casino.game.action.GameAction;
import com.clemble.casino.game.event.server.GameManagementEvent;
import com.clemble.casino.game.iterator.GamePlayerIterator;
import com.clemble.casino.game.outcome.GameOutcome;
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

    final private ActionLatch actionLatch;
    final private GameAccount gameAccount;
    final private GamePlayerIterator playerIterator;

    private GameOutcome outcome;
    private int version;

    public FakeState(ActionLatch expectedActions, GameAccount gameAccount, GamePlayerIterator playerIterator) {
        this(expectedActions, gameAccount, playerIterator, null, 0);
    }

    @JsonCreator
    public FakeState(@JsonProperty("actionLatch") ActionLatch expectedActions,
                     @JsonProperty("account") GameAccount gameAccount,
                     @JsonProperty("playerIterator") GamePlayerIterator playerIterator,
                     @JsonProperty("outcome") GameOutcome outcome,
                     @JsonProperty("version") int version) {
        this.actionLatch = expectedActions;
        this.gameAccount = gameAccount;
        this.playerIterator = playerIterator;
        this.outcome = outcome;
    }

    @Override
    public <State extends GameState> GameManagementEvent<State> process(GameSession<State> session, GameAction action) {
        // Step 1. Processing Select cell move
        throw ClembleCasinoException.fromError(ClembleCasinoError.GamePlayGameEnded);
    }

    @Override
    public GameAccount getAccount() {
        return gameAccount;
    }

    @Override
    public GamePlayerIterator getPlayerIterator() {
        return playerIterator;
    }

    @Override
    public ActionLatch getActionLatch() {
        return actionLatch;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FakeState fakeState = (FakeState) o;

        if (version != fakeState.version) return false;
        if (actionLatch != null ? !actionLatch.equals(fakeState.actionLatch) : fakeState.actionLatch != null)
            return false;
        if (gameAccount != null ? !gameAccount.equals(fakeState.gameAccount) : fakeState.gameAccount != null)
            return false;
        if (outcome != null ? !outcome.equals(fakeState.outcome) : fakeState.outcome != null) return false;
        if (playerIterator != null ? !playerIterator.equals(fakeState.playerIterator) : fakeState.playerIterator != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actionLatch != null ? actionLatch.hashCode() : 0;
        result = 31 * result + (gameAccount != null ? gameAccount.hashCode() : 0);
        result = 31 * result + (playerIterator != null ? playerIterator.hashCode() : 0);
        result = 31 * result + (outcome != null ? outcome.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }
}
