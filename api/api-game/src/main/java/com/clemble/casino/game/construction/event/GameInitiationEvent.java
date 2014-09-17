package com.clemble.casino.game.construction.event;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import com.clemble.casino.game.construction.GameInitiation;
import com.clemble.casino.game.construction.GameInitiationAware;
import com.clemble.casino.game.event.GameManagementEvent;

public interface GameInitiationEvent extends GameConstructionEvent, GameInitiationAware {

}
