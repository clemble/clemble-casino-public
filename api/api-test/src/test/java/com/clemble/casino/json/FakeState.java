package com.clemble.casino.json;

import com.clemble.casino.error.ClembleCasinoError;
import com.clemble.casino.error.ClembleCasinoException;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.management.RoundGameContext;
import com.clemble.casino.game.lifecycle.management.RoundGameState;
import com.clemble.casino.game.lifecycle.management.RoundState;
import com.clemble.casino.game.lifecycle.management.event.GameManagementEvent;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 22/12/13.
 */
@JsonTypeName("fake")
public class FakeState implements RoundState {

    @Override
    public GameManagementEvent process(PlayerAction action, RoundGameState state) {
        return null;
    }

    @Override
    public int hashCode() {
        return 29;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof FakeState;
    }

}
