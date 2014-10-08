package com.clemble.casino.game.lifecycle.initiation.event;

import com.clemble.casino.game.event.GameEvent;
import com.clemble.casino.game.lifecycle.construction.event.GameConstructionEvent;
import com.clemble.casino.game.lifecycle.initiation.GameInitiationAware;

public interface GameInitiationEvent extends GameEvent, GameInitiationAware {

}
