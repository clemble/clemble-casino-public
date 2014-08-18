package com.clemble.casino.goal;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudgeInvitation implements PlayerAware, GoalAware {

    @Id
    final private GoalKey goalKey;
    final private String player;
    final private String judge;
    final private Goal goal;
    final private GoalJudgeInvitationStatus status;

    @JsonCreator
    public GoalJudgeInvitation(@JsonProperty("player") String player, @JsonProperty("judge") String judge, @JsonProperty("goalKey") GoalKey goalKey, @JsonProperty("goal") Goal goal, @JsonProperty("status") GoalJudgeInvitationStatus status) {
        this.player = player;
        this.judge = judge;
        this.goalKey = goalKey;
        this.status = status;
        this.goal = goal;
    }

    @Override
    public GoalKey getGoalKey() {
        return goalKey;
    }

    public Goal getGoal() {
        return goal;
    }

    public GoalJudgeInvitationStatus getStatus() {
        return status;
    }

    public String getJudge() {
        return judge;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalJudgeInvitation that = (GoalJudgeInvitation) o;

        if (goal != null ? !goal.equals(that.goal) : that.goal != null) return false;
        if (judge != null ? !judge.equals(that.judge) : that.judge != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (judge != null ? judge.hashCode() : 0);
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        return result;
    }
}
