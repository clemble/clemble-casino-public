package com.clemble.casino.goal.notification;

import com.clemble.casino.bet.Bid;
import com.clemble.casino.bet.BidAware;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Date;

/**
 * Created by mavarazy on 11/29/14.
 */
@JsonTypeName(GoalBidNotification.JSON_TYPE)
public class GoalBidNotification implements GoalNotification, BidAware {

    final public static String JSON_TYPE = "notification:goal:bid";

    final private String player;
    final private Bank bank;
    final private String bidder;
    final private Bid bid;
    final private String goal;
    final private String goalKey;
    final private long deadline;

    @JsonCreator
    public GoalBidNotification(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bidder") String bidder,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("bid") Bid bid,
        @JsonProperty("goal") String goal,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("created") Date created
    ) {
        this.goalKey = goalKey;
        this.player = player;
        this.bidder = bidder;
        this.goal = goal;
        this.bank = bank;
        this.bid = bid;
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

    @Override
    public Bid getBid() {
        return bid;
    }

    public String getBidder() {
        return bidder;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalBidNotification that = (GoalBidNotification) o;

        if (deadline != that.deadline) return false;
        if (!bank.equals(that.bank)) return false;
        if (!bid.equals(that.bid)) return false;
        if (!bidder.equals(that.bidder)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + bidder.hashCode();
        result = 31 * result + bid.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + goalKey.hashCode();
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        return result;
    }

}
