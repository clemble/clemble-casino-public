package com.clemble.casino.game.lifecycle.management.event.action;

import com.clemble.casino.game.lifecycle.management.unit.GameUnit;

/**
 * Created by mavarazy on 15/03/14.
 */
public interface UseGameUnitAction extends PlayerGameAction {

    public GameUnit getUnit();

}
