package com.clemble.casino.goal.notification;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.BidAware;
import com.clemble.casino.bet.PlayerBid;
import com.clemble.casino.goal.lifecycle.initiation.GoalInitiation;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalBidNotification.JSON_TYPE)
public class GoalBidNotification implements GoalNotification {

    final public static String JSON_TYPE = "notification:goal:bid";

    final private String player;
    final private Bank bank;
    final private PlayerBid playerBid;
    final private String goal;
    final private String goalKey;
    final private long deadline;

    @JsonCreator
    public GoalBidNotification(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("created") Date created,
        @JsonProperty("playerBid") PlayerBid playerBid
    ) {
        this.goalKey = goalKey;
        this.player = player;
        this.playerBid = playerBid;
        this.goal = goal;
        this.bank = bank;
        this.deadline = deadline;
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

    public PlayerBid getPlayerBid() {
        return playerBid;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public long getDeadline() {
        return deadline;
    }

    public static GoalBidNotification create(PlayerBid bid, GoalInitiation initiation) {
        return new GoalBidNotification(
            initiation.getGoalKey(),
            initiation.getPlayer(),
            initiation.getBank(),
            initiation.getGoal(),
            initiation.getStartDate().getTime() + initiation.getConfiguration().getTotalTimeRule().getLimit(),
            new Date(),
            bid
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalBidNotification that = (GoalBidNotification) o;

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
