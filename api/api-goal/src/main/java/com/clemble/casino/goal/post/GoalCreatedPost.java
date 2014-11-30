package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalCreatedPost.JSON_TYPE)
public class GoalCreatedPost implements GoalPost {

    final public static String JSON_TYPE = "notification:goal:created";

    final private String player;
    final private Bank bank;
    final private String goal;
    final private String goalKey;
    final private Date startDate;
    final private long deadline;
    final private Date created;

    @JsonCreator
    public GoalCreatedPost(
        @JsonProperty("key") String key,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("created") Date created) {
        this.goalKey = goalKey;
        this.player = player;
        this.startDate = startDate;
        this.goal = goal;
        this.bank = bank;
        this.deadline = deadline;
        this.created = created;
    }

    @Override
    public String getKey() {
        return goalKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public long getDeadline() {
        return deadline;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalCreatedPost that = (GoalCreatedPost) o;

        if (deadline != that.deadline) return false;
        if (!bank.equals(that.bank)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;
        if (!startDate.equals(that.startDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + goalKey.hashCode();
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        return result;
    }

    public static GoalCreatedPost create(GoalInitiation initiation) {
        return new GoalCreatedPost(
            initiation.getGoalKey(),
            initiation.getGoalKey(),
            initiation.getPlayer(),
            initiation.getBank(),
            initiation.getGoal(),
            initiation.getStartDate(),
            initiation.getStartDate().getTime() + initiation.getConfiguration().getTotalTimeRule().getLimit(),
            new Date()
        );
    }

}
