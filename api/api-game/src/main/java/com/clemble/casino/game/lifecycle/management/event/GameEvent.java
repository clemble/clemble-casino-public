package com.clemble.casino.game.lifecycle.management.event;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.GameSessionAware;

public interface GameEvent extends Event, GameSessionAware {

}