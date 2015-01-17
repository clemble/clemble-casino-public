package com.clemble.casino.goal.lifecycle.management.event;

import com.clemble.casino.bet.PlayerBid;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.goal.post.GoalBetPost;
import com.clemble.casino.goal.post.GoalPost;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 1/17/15.
 */
@JsonTypeName(GoalChangedBetEvent.JSON_TYPE)
public class GoalChangedBetEvent implements GoalManagementEvent {

    final public static String JSON_TYPE = "goal:management:changed:bet";

    final private String player;
    final private PlayerBid bid;
    final private GoalState body;

    @JsonCreator
    public GoalChangedBetEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalState body,
        @JsonProperty("bid") PlayerBid bid) {
        this.bid = bid;
        this.body = body;
        this.player = player;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public GoalState getBody() {
        return body;
    }

    public PlayerBid getBid() {
        return bid;
    }

    @Override
    public GoalPost toPost() {
        return GoalBetPost.create(bid, body);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalChangedBetEvent)) return false;

        GoalChangedBetEvent that = (GoalChangedBetEvent) o;

        if (!bid.equals(that.bid)) return false;
        if (!body.equals(that.body)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bid.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }

}
