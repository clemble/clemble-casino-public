package com.clemble.casino.game.action;

import com.clemble.casino.game.unit.GameUnit;

/**
 * Created by mavarazy on 15/03/14.
 */
public interface UseGameUnitAction extends PlayerGameAction {

    public GameUnit getUnit();

}
