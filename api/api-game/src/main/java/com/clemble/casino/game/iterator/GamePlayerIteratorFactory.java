package com.clemble.casino.game.iterator;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.player.PlayerAwareUtils;

public class GamePlayerIteratorFactory {

    public static GamePlayerIterator create(GameInitiation initiation) {
        return new SequentialPlayerIterator(PlayerAwareUtils.toPlayerList(initiation.getParticipants()));
    }

}
