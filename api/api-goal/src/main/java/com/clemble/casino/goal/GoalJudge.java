package com.clemble.casino.goal;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudge implements PlayerAware {

    final private String player;
    final private Collection<GoalJudgeDuty> duties;

    @JsonCreator
    public GoalJudge(@JsonProperty("player") String player,
        @JsonProperty("duties") Collection<GoalJudgeDuty> duties) {
        this.player = player;
        this.duties = duties;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Collection<GoalJudgeDuty> getDuties() {
        return duties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalJudge goalJudge = (GoalJudge) o;

        if (duties != null ? !duties.equals(goalJudge.duties) : goalJudge.duties != null) return false;
        if (player != null ? !player.equals(goalJudge.player) : goalJudge.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (duties != null ? duties.hashCode() : 0);
        return result;
    }
}
