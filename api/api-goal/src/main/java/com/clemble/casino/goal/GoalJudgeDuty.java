package com.clemble.casino.goal;

import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 8/17/14.
 */
public class GoalJudgeDuty implements GoalAware, GoalDescriptionAware, PlayerAware {

    @Id
    final private String goalKey;
    final private String goal;
    final private String player;
    final private String judge;
    final private Date dueDate;
    final private GoalJudgeDutyStatus status;

    @JsonCreator
    public GoalJudgeDuty(
        @JsonProperty(GOAL_KEY) String goalKey,
        @JsonProperty("goal") String goal,
        @JsonProperty(PLAYER) String player,
        @JsonProperty(JUDGE) String judge,
        @JsonProperty("status") GoalJudgeDutyStatus status,
        @JsonProperty("dueDate") Date dueDate) {
        this.goalKey = goalKey;
        this.goal = goal;
        this.player = player;
        this.judge = judge;
        this.status = status;
        this.dueDate = dueDate;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getJudge() {
        return judge;
    }

    public GoalJudgeDutyStatus getStatus() {
        return status;
    }

    public GoalJudgeDuty cloneWithStatus(GoalJudgeDutyStatus status) {
        return new GoalJudgeDuty(goalKey, goal, player, judge, status, dueDate);
    }

    public static GoalJudgeDuty fromInvitation(GoalJudgeInvitation invitation){
        return new GoalJudgeDuty(invitation.getGoalKey(), invitation.getGoal(), invitation.getPlayer(), invitation.getJudge(), GoalJudgeDutyStatus.pending, invitation.getDueDate());
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

    @Override
    public String toString() {
        return "goal:duty:" + goalKey + ":judge:" + judge + ":player:" + player + ":status:" + status;
    }

}
