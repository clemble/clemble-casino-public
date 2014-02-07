package com.clemble.casino.game;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.event.server.GameManagementEvent;

public interface GameProcessor<R extends GameRecord, A extends Event> {

    public GameManagementEvent process(R session, A action);

}
