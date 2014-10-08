package com.clemble.casino.game.lifecycle.management.iterator;

import com.clemble.casino.game.lifecycle.initiation.GameInitiation;

public class GamePlayerIteratorFactory {

    public static GamePlayerIterator create(GameInitiation initiation) {
        return SequentialPlayerIterator.fromCollection(initiation.getParticipants());
    }

}
