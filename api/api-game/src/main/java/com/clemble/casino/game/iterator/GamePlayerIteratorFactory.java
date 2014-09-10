package com.clemble.casino.game.iterator;

import com.clemble.casino.game.construction.GameInitiation;

public class GamePlayerIteratorFactory {

    public static GamePlayerIterator create(GameInitiation initiation) {
        return SequentialPlayerIterator.fromCollection(initiation.getParticipants());
    }

}
