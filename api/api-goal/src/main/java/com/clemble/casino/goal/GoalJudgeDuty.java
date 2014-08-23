package com.clemble.casino.goal;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudgeDuty implements GoalAware, PlayerAware {

    @Id
    final private String goalKey;
    final private String player;
    final private String judge;
    final private GoalJudgeDutyStatus status;

    @JsonCreator
    public GoalJudgeDuty(
        @JsonProperty(GOAL_KEY) String goalKey,
        @JsonProperty(PLAYER) String player,
        @JsonProperty(JUDGE) String judge,
        @JsonProperty("status") GoalJudgeDutyStatus status) {
        this.goalKey = goalKey;
        this.player = player;
        this.judge = judge;
        this.status = status;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public String getJudge() {
        return judge;
    }

    public GoalJudgeDutyStatus getStatus() {
        return status;
    }

    public static GoalJudgeDuty fromInvitation(GoalJudgeInvitation invitation){
        return new GoalJudgeDuty(invitation.getGoalKey(), invitation.getPlayer(), invitation.getJudge(), GoalJudgeDutyStatus.pending);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalJudgeDuty that = (GoalJudgeDuty) o;

        if (!goalKey.equals(that.goalKey)) return false;
        if (!judge.equals(that.judge)) return false;
        if (!player.equals(that.player)) return false;
        if (status != that.status) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + judge.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

}
