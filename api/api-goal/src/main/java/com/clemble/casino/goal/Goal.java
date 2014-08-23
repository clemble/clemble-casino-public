package com.clemble.casino.goal;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.BidAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by mavarazy on 8/2/14.
 */
public class Goal implements GoalAware, GoalDescriptionAware, PlayerAware, BidAware {

    @Id
    final private String goalKey;
    final private String player;
    final private String judge;
    final private String goal;
    final private Date startDate;
    final private Date dueDate;
    final private Bid bid;
    final private GoalStatus status;
    final private GoalState state;

    @JsonCreator
    public Goal(
            @JsonProperty(GOAL_KEY) String goalKey,
            @JsonProperty(PLAYER) String player,
            @JsonProperty("judge") String judge,
            @JsonProperty("goal") String goal,
            @JsonProperty("startDate") Date startDate,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("state") GoalState state,
            @JsonProperty("status") GoalStatus status,
            @JsonProperty("bid") Bid bid) {
        this.goalKey = goalKey;
        this.player = player;
        this.judge = judge;
        this.state = state;
        this.bid = bid;
        this.dueDate = dueDate;
        this.startDate = startDate;
        this.goal = goal;
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

    @Override
    public Bid getBid(){
        return bid;
    }

    @Override
    public String getJudge(){
        return judge;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    public GoalState getState() {
        return state;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public GoalStatus getStatus() {
        return status;
    }

    public Goal cloneWithStatus(GoalStatus status) {
        return new Goal(goalKey, player, judge, goal, startDate, dueDate, state, status, bid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal = (Goal) o;

        if (this.goal != null ? !this.goal.equals(goal.goal) : goal.goal != null) return false;
        if (dueDate != null ? !dueDate.equals(goal.dueDate) : goal.dueDate != null) return false;
        if (goalKey != null ? !goalKey.equals(goal.goalKey) : goal.goalKey != null) return false;
        if (player != null ? !player.equals(goal.player) : goal.player != null) return false;
        if (startDate != null ? !startDate.equals(goal.startDate) : goal.startDate != null) return false;
        if (state != goal.state) return false;
        if (status != null ? !status.equals(goal.status) : goal.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "goal:" + goalKey +
            ":player:" + player +
            ":judge:" + judge +
            ":goal:" + goal +
            ":" + startDate +
            ":to:" + dueDate +
            ":bid:" + bid +
            ":status:" + status +
            ":state:" + state;
    }
}
