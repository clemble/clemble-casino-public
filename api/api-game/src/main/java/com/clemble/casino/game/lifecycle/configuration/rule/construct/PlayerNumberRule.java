package com.clemble.casino.game.lifecycle.configuration.rule.construct;

import com.clemble.casino.lifecycle.configuration.rule.ConfigurationRule;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("rule:player:number")
public enum PlayerNumberRule implements ConfigurationRule {

    two(2, 2),
    twoToSix(2, 6);

    final private int minPlayers;
    final private int maxPlayers;

    private PlayerNumberRule(int minPlayers, int maxPlayers) {
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    final static public PlayerNumberRule DEFAULT = PlayerNumberRule.two;

}
