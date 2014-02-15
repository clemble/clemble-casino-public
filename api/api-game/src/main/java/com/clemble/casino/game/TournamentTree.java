package com.clemble.casino.game;

import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.game.rule.construct.PlayerNumberRule;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mavarazy on 13/02/14.
 */
public class TournamentTree {

    final private Map<Integer, Map<Integer, GameOutcome>> tree = new HashMap<Integer, Map<Integer, GameOutcome>>();

    public TournamentTree(PlayerNumberRule numberRule){


    }
}
