package com.clemble.casino.goal.post;

import com.clemble.casino.bet.PlayerBid;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;
import java.util.Set;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalBidPost.JSON_TYPE)
public class GoalBidPost implements GoalPost, GoalConfigurationAware {

    final public static String JSON_TYPE = "post:goal:bid";

    final private String goalKey;
    final private String player;
    final private Bank bank;
    final private GoalConfiguration configuration;
    final private PlayerBid playerBid;
    final private String goal;
    final private Set<String> observers;
    final private Set<String> supporters;
    final private Date startDate;
    final private long deadline;

    @JsonCreator
    public GoalBidPost(
        @JsonProperty("key") String key,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("goal") String goal,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("observers") Set<String> observers,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("startDate") Date startDate,
        @JsonProperty("playerBid") PlayerBid playerBid
    ) {
        this.goalKey = goalKey;
        this.player = player;
        this.playerBid = playerBid;
        this.observers = observers;
        this.supporters = supporters;
        this.goal = goal;
        this.bank = bank;
        this.configuration = configuration;
        this.startDate = startDate;
        this.deadline = deadline;
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

    @Override
    public Set<String> getObservers() {
        return observers;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    public PlayerBid getPlayerBid() {
        return playerBid;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public long getDeadline() {
        return deadline;
    }

    public static GoalBidPost create(PlayerBid bid, GoalInitiation initiation) {
        return new GoalBidPost(
            initiation.getGoalKey(),
            initiation.getGoalKey(),
            initiation.getPlayer(),
            initiation.getBank(),
            initiation.getConfiguration(),
            initiation.getGoal(),
            initiation.getConfiguration().getTotalTimeoutRule().getTimeoutCalculator().calculate(initiation.getStartDate().getTime()),
            initiation.getObservers(),
            initiation.getSupporters(),
            initiation.getStartDate(),
            bid
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalBidPost that = (GoalBidPost) o;

        if (deadline != that.deadline) return false;
        if (!bank.equals(that.bank)) return false;
        if (!playerBid.equals(that.playerBid)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + playerBid.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + goalKey.hashCode();
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        return result;
    }

}
