package com.clemble.casino.goal.lifecycle.initiation.event;

import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.goal.post.GoalCreatedPost;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.notification.PlayerNotificationConvertible;
import com.clemble.casino.post.PlayerPost;
import com.clemble.casino.post.PlayerPostConvertible;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 9/13/14.
 */
@JsonTypeName(GoalInitiationCreatedEvent.JSON_TYPE)
public class GoalInitiationCreatedEvent implements GoalInitiationEvent, PlayerPostConvertible {

    final public static String JSON_TYPE = "goal:initiation:created";

    final private String player;
    final private GoalInitiation initiation;

    @JsonCreator
    public GoalInitiationCreatedEvent(@JsonProperty(PLAYER) String player, @JsonProperty("body") GoalInitiation initiation) {
        this.player = player;
        this.initiation = initiation;
    }

    @Override
    public GoalInitiation getBody() {
        return initiation;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public static GoalInitiationCreatedEvent create(GoalInitiation initiation) {
        return new GoalInitiationCreatedEvent(initiation.getPlayer(), initiation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiationCreatedEvent that = (GoalInitiationCreatedEvent) o;

        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (initiation != null ? !initiation.equals(that.initiation) : that.initiation != null) return false;

        return true;
    }

    @Override
    public PlayerPost toPost() {
        return GoalCreatedPost.create(initiation);
    }

    @Override
    public int hashCode() {
        return initiation != null ? initiation.hashCode() : 0;
    }

    @Override
    public String toString() {
        return player + " > " + initiation.getGoalKey() + " > " + JSON_TYPE;
    }

}
