package com.clemble.casino.goal.lifecycle.initiation.event;

import com.clemble.casino.bet.PlayerBid;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiationAware;
import com.clemble.casino.goal.notification.GoalBidNotification;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 11/24/14.
 */
@JsonTypeName(GoalInitiationChangedEvent.JSON_TYPE)
public class GoalInitiationChangedEvent implements GoalInitiationEvent, PlayerNotificationConvertible {
    final public static String JSON_TYPE = "goal:initiation:changed";

    final private String player;
    final private PlayerBid bid;
    final private GoalInitiation initiation;

    @JsonCreator
    public GoalInitiationChangedEvent(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("body") GoalInitiation initiation,
        @JsonProperty("bid") PlayerBid bid) {
        this.bid = bid;
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

    public PlayerBid getBid() {
        return bid;
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

    public static GoalInitiationChangedEvent create(PlayerBid bid, GoalInitiation initiation) {
        return new GoalInitiationChangedEvent(initiation.getPlayer(), initiation, bid);
    }

    @Override
    public PlayerNotification toNotification() {
        return GoalBidNotification.create(bid, initiation);
    }
}
