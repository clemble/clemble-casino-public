package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.bet.BetsAllowedAware;
import com.clemble.casino.bet.Bet;
import com.clemble.casino.bet.PlayerBet;
import com.clemble.casino.event.Event;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.GoalStatusAware;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.event.action.GoalReachedAction;
import com.clemble.casino.goal.event.action.GoalStatusUpdateAction;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.management.event.*;
import com.clemble.casino.event.lifecycle.LifecycleStartedEvent;
import com.clemble.casino.lifecycle.configuration.rule.time.DeadlineAware;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.TimeoutPunishmentAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BetOffAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BidAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.SurrenderAction;
import com.clemble.casino.lifecycle.management.outcome.PlayerLostOutcome;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.payment.BankAware;
import com.clemble.casino.tag.TagAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalState implements
    State<GoalEvent, GoalContext>,
    GoalAware,
    GoalDescriptionAware,
    GoalConfigurationAware,
    GoalStatusAware,
    GoalRoleAware,
    BetsAllowedAware,
    BankAware,
    DeadlineAware,
    TagAware {

    @Id
    final private String goalKey;
    final private String player;
    final private String goal;
    final private String status;
    final private String reward;
    final private String tag;
    final private Bank bank;
    final private GoalContext context;
    final private GoalConfiguration configuration;
    final private Set<String> supporters;
    final private DateTime startDate;
    final private DateTime deadline;
    final private boolean betsAllowed;

    @JsonCreator
    public GoalState(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("startDate") DateTime startDate,
        @JsonProperty("deadline") DateTime deadline,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("reward") String reward,
        @JsonProperty("tag") String tag,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("context") GoalContext context,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("betsAllowed") boolean betsAllowed,
        @JsonProperty("status") String status) {
        this.goalKey = goalKey;
        this.player = player;
        this.startDate = startDate;
        this.deadline = deadline;
        this.supporters = supporters;
        this.betsAllowed = betsAllowed;
        this.configuration = configuration;
        this.context = context;
        this.bank = bank;
        this.goal = goal;
        this.reward = reward;
        this.tag = tag;
        this.status = status;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Bank getBank() {
        return bank;
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
    public String getReward() {
        return reward;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public boolean getBetsAllowed() {
        return betsAllowed;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    @Override
    public GoalContext getContext() {
        return context;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    @Override
    public DateTime getDeadline() {
        return deadline;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public GoalStartedEvent start() {
        return new GoalStartedEvent(player, this);
    }

    @Override
    public GoalEvent process(Event actionEvent){
        if(actionEvent instanceof LifecycleStartedEvent) {
            return new GoalStartedEvent(player, this);
        } else if(actionEvent instanceof PlayerAction<?>) {
            String actor = ((PlayerAction) actionEvent).getPlayer();
            Action action = ((PlayerAction) actionEvent).getAction();
            if (action instanceof BetOffAction) {
                return new GoalChangedBetOffEvent(player, this.forbidBet());
            } else if (action instanceof GoalStatusUpdateAction) {
                GoalStatusUpdateAction statusUpdateAction = ((GoalStatusUpdateAction) action);
                String newStatus = statusUpdateAction.getStatus();
                return new GoalChangedStatusEvent(player, this.copy(newStatus));
            } else if (action instanceof SurrenderAction) {
                return new GoalEndedEvent(player, this, new PlayerLostOutcome(actor));
            } else if (action instanceof BidAction) {
                if(!this.betsAllowed || this.supporters.contains(actor) || this.player.equals(actor))
                    throw new IllegalArgumentException();
                Bet bet = configuration.getSupporterConfiguration().getBet();
                PlayerBet playerBid = new PlayerBet(actor, bet);
                this.bank.add(playerBid);
                this.supporters.add(actor);
                return new GoalChangedBetEvent(player, this, playerBid);
            } else if (action instanceof GoalReachedAction) {
                GoalReachedAction reachedAction = (GoalReachedAction) action;
                String newStatus = reachedAction.getStatus();
                return new GoalEndedEvent(player, this.copy(newStatus), new PlayerWonOutcome(actor));
            } else if (action instanceof TimeoutPunishmentAction) {
                TimeoutPunishmentAction punishmentAction = (TimeoutPunishmentAction) action;
                bank.add(new PlayerBet(player, new Bet(Money.create(Currency.FakeMoney, 0), punishmentAction.getAmount().negate())));
                if (bank.getBet(player).getBet().getInterest().getAmount() == 0) {
                    return new GoalEndedEvent(player, this, new PlayerLostOutcome(player));
                } else {
                    return new GoalChangedStatusUpdateMissedEvent(player, this);
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GoalState copy(String newStatus) {
        return new GoalState(
            goalKey,
            startDate,
            deadline,
            player,
            bank,
            goal,
            reward,
            tag,
            configuration,
            context,
            supporters,
            betsAllowed,
            newStatus
        );
    }

    public GoalState forbidBet() {
        if (!betsAllowed) {
            return this;
        } else {
            return new GoalState(
                goalKey,
                startDate,
                deadline,
                player,
                bank,
                goal,
                reward,
                tag,
                configuration,
                context,
                supporters,
                false,
                status
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalState that = (GoalState) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null) return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (bank != null ? !bank.equals(that.bank) : that.bank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        return result;
    }
}
