package com.clemble.casino.goal.lifecycle.initiation.event;

import com.clemble.casino.bet.PlayerBet;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.post.GoalBetPost;
import com.clemble.casino.post.PlayerPost;
import com.clemble.casino.post.PlayerPostConvertible;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/24/14.
 */
@JsonTypeName(GoalInitiationChangedEvent.JSON_TYPE)
public class GoalInitiationChangedEvent implements GoalInitiationEvent, PlayerPostConvertible {
    final public static String JSON_TYPE = "goal:initiation:changed";

    final private String player;
    final private PlayerBet bet;
    final private GoalInitiation initiation;

    @JsonCreator
    public GoalInitiationChangedEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalInitiation initiation,
        @JsonProperty("bet") PlayerBet bet) {
        this.bet = bet;
        this.player = player;
        this.initiation = initiation;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GoalInitiation getBody() {
        return initiation;
    }

    public PlayerBet getBet() {
        return bet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiationChangedEvent that = (GoalInitiationChangedEvent) o;

        if (!initiation.equals(that.initiation)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + initiation.hashCode();
        return result;
    }

    public static GoalInitiationChangedEvent create(PlayerBet bet, GoalInitiation initiation) {
        return new GoalInitiationChangedEvent(initiation.getPlayer(), initiation, bet);
    }

    @Override
    public PlayerPost toPost() {
        return GoalBetPost.create(bet, initiation);
    }

}
