package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.GoalStatusAware;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.event.action.GoalReachedAction;
import com.clemble.casino.goal.event.action.GoalStatusUpdateAction;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.configuration.ShortGoalConfiguration;
import com.clemble.casino.goal.lifecycle.management.event.GoalChangedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalEndedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalStartedEvent;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.event.lifecycle.LifecycleStartedEvent;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.SurrenderAction;
import com.clemble.casino.lifecycle.management.outcome.PlayerLostOutcome;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.payment.BankAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

/**
 * Created by mavarazy on 10/9/14.
 */
public class ShortGoalState implements GoalState {

    @Id
    final private String goalKey;
    final private String player;
    final private String goal;
    final private Bank bank;
    final private ShortGoalConfiguration configuration;
    final private GoalContext context;
    private String status;

    @JsonCreator
    public ShortGoalState(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("configuration") ShortGoalConfiguration configuration,
        @JsonProperty("context") GoalContext context,
        @JsonProperty("status") String status) {
        this.goalKey = goalKey;
        this.player = player;
        this.configuration = configuration;
        this.context = context;
        this.bank = bank;
        this.goal = goal;
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
    public String getStatus() {
        return status;
    }

    @Override
    public GoalContext getContext() {
        return context;
    }

    @Override
    public ShortGoalConfiguration getConfiguration() {
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
            String player = ((PlayerAction) actionEvent).getPlayer();
            Action action = ((PlayerAction) actionEvent).getAction();
            if(action instanceof GoalStatusUpdateAction) {
                GoalStatusUpdateAction statusUpdateAction = ((GoalStatusUpdateAction) action);
                this.status = statusUpdateAction.getStatus();
                return new GoalChangedEvent(player, this);
            } else if(action instanceof SurrenderAction) {
                return new GoalEndedEvent(player, this, new PlayerLostOutcome(player));
            } else if (action instanceof GoalReachedAction) {
                GoalReachedAction reachedAction = (GoalReachedAction) action;
                this.status = reachedAction.getStatus();
                return new GoalEndedEvent(player, this, new PlayerWonOutcome(player));
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShortGoalState that = (ShortGoalState) o;

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
