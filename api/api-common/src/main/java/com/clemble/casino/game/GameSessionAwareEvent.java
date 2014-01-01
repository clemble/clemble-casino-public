package com.clemble.casino.game;

import com.clemble.casino.event.GameEvent;

public interface GameSessionAwareEvent extends GameEvent, GameSessionAware {
}
