package com.clemble.casino.game;

/**
 * Created by mavarazy on 13/02/14.
 */
public class TournamentPosition {

    final private int level;
    final private int group;

    public TournamentPosition(int level, int group) {
        this.level = level;
        this.group = group;
    }

    public int getLevel(){
        return level;
    }

    public int group() {
        return group;
    }
}
