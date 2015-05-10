package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 5/10/15.
 */
public class GoalInspiration implements PlayerAware, CreatedAware, Comparable<GoalInspiration> {

    // TODO add picture
    final private String player;
    final private String inspiration;
    final private DateTime created;

    @JsonCreator
    public GoalInspiration(
        @JsonProperty(PLAYER) String player,
        @JsonProperty("inspiration") String inspiration,
        @JsonProperty("created") DateTime created) {
        this.player = player;
        this.inspiration = inspiration;
        this.created = created;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getInspiration() {
        return inspiration;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    @Override
    public int compareTo(GoalInspiration o) {
        return o.created.compareTo(created);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalInspiration)) return false;

        GoalInspiration that = (GoalInspiration) o;

        if (!inspiration.equals(that.inspiration)) return false;
        if (!player.equals(that.player)) return false;
        if (!created.equals(that.created)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + inspiration.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }

}
