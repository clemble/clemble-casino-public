package com.clemble.casino.goal.lifecycle.initiation;

import com.clemble.casino.goal.lifecycle.management.GoalRoleAware;
import com.clemble.casino.lifecycle.initiation.Initiation;
import com.clemble.casino.lifecycle.initiation.InitiationState;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.record.GoalRecord;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.lifecycle.record.EventRecord;
import com.clemble.casino.lifecycle.record.RecordState;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.payment.BankAware;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * Created by mavarazy on 9/12/14.
 */
public class GoalInitiation implements
    GoalAware,
    GoalDescriptionAware,
    GoalRoleAware,
    Initiation<GoalConfiguration>,
    BankAware {

    @Id
    final private String goalKey;
    final private String goal;
    final private String player;
    final private Bank bank;
    final private Date startDate;
    final private InitiationState state;
    final private Set<String> observers;
    final private Set<String> supporters;
    final private GoalConfiguration configuration;

    @JsonCreator
    public GoalInitiation(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("state") InitiationState state,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("player") String player,
        @JsonProperty("goal") String goal,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("observers") Set<String> observers,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("startDate") Date startDate) {
        this.goal = goal;
        this.state = state;
        this.bank = bank;
        this.player = player;
        this.goalKey = goalKey;
        this.observers = observers;
        this.supporters = supporters;
        this.startDate = startDate;
        this.configuration = configuration;
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
    public Bank getBank() {
        return bank;
    }

    @Override
    public InitiationState getState() {
        return state;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public Date getStartDate() {
        return startDate;
    }

    @Override
    public Set<String> getObservers() {
        return observers;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public GoalRecord toRecord() {
        return new GoalRecord(goalKey,
            player,
            RecordState.active,
            bank,
            goal,
            configuration,
            Collections.<EventRecord>emptySet(),
            null);
    }

    public GoalInitiation copyWithState(InitiationState state) {
        return new GoalInitiation(goalKey, state, bank, player, goal, configuration, observers, supporters, startDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalInitiation that = (GoalInitiation) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)  return false;
        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (bank != null ? !bank.equals(that.bank) : that.bank != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        return result;
    }

}
