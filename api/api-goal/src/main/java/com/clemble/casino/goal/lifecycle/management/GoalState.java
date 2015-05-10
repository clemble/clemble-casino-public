package com.clemble.casino.goal.lifecycle.management;

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
import com.clemble.casino.goal.lifecycle.configuration.GoalRoleConfiguration;
import com.clemble.casino.goal.lifecycle.management.event.*;
import com.clemble.casino.event.lifecycle.LifecycleStartedEvent;
import com.clemble.casino.lifecycle.configuration.rule.time.DeadlineAware;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.management.event.action.Action;
import com.clemble.casino.lifecycle.management.event.action.PlayerAction;
import com.clemble.casino.lifecycle.management.event.action.TimeoutPunishmentAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.management.event.action.bet.BetOffAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.SurrenderAction;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.PlayerLostOutcome;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.clemble.casino.lifecycle.record.EventRecord;
import com.clemble.casino.lifecycle.record.Record;
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
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalState implements
    Record<GoalConfiguration>,
    State<GoalEvent, GoalContext>,
    GoalAware,
    GoalPhaseAware,
    GoalDescriptionAware,
    GoalConfigurationAware,
    GoalStatusAware,
    GoalRoleAware,
    BankAware,
    DeadlineAware,
    TagAware {

    @Id
    final private String goalKey;
    final private String player;
    final private String goal;
    final private String status;
    final private String tag;
    final private Bank bank;
    final private GoalPhase phase;
    final private GoalContext context;
    final private GoalConfiguration configuration;
    final private Set<String> supporters;
    final private Action lastAction;
    final private DateTime startDate;
    final private DateTime deadline;
    final private String timezone;
    final private SortedSet<EventRecord> eventRecords;
    final private Outcome outcome;

    @JsonCreator
    public GoalState(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("startDate") DateTime startDate,
        @JsonProperty("deadline") DateTime deadline,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty(TIME_ZONE) String timezone,
        @JsonProperty("tag") String tag,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("context") GoalContext context,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("status") String status,
        @JsonProperty("phase") GoalPhase phase,
        @JsonProperty("lastAction") Action lastAction,
        @JsonProperty("eventRecords") SortedSet<EventRecord> eventRecords,
        @JsonProperty("outcome") Outcome outcome) {
        this.goalKey = goalKey;
        this.player = player;
        this.phase = phase;
        this.startDate = startDate;
        this.deadline = deadline;
        this.supporters = supporters;
        this.configuration = configuration;
        this.context = context;
        this.bank = bank;
        this.goal = goal;
        this.tag = tag;
        this.status = status;
        this.lastAction = lastAction;
        this.timezone = timezone;
        this.eventRecords = eventRecords;
        this.outcome = outcome;
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
    public String getTimezone() {
        return timezone;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public String getStatus() {
        return status;
    }

    public Action getLastAction() {
        return lastAction;
    }

    @Override
    public GoalPhase getPhase() {
        return phase;
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
    public SortedSet<EventRecord> getEventRecords() {
        return eventRecords;
    }

    @Override
    public Outcome getOutcome() {
        return outcome;
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
                return new GoalChangedStatusEvent(player, this.copyWithStatus(newStatus, action));
            } else if (action instanceof SurrenderAction) {
                Outcome outcome = new PlayerLostOutcome(actor);
                return new GoalEndedEvent(player, this.finish(outcome), outcome);
            } else if (action instanceof BetAction) {
                switch (phase) {
                    case betOff:
                    case finished:
                        throw new IllegalArgumentException();
                }
                if(this.supporters.contains(actor) || this.player.equals(actor))
                    throw new IllegalArgumentException();
                GoalRoleConfiguration roleConfiguration = configuration.getSupporterConfiguration();
                int amount = ((BetAction) action).getBet();
                int interest = ((100 + roleConfiguration.getPercentage()) * amount) / 100;
                Bet bet = new Bet(Money.create(Currency.point, amount), Money.create(Currency.point, interest));
                PlayerBet playerBid = new PlayerBet(actor, bet);
                this.bank.add(playerBid);
                this.supporters.add(actor);
                return new GoalChangedBetEvent(player, this, playerBid);
            } else if (action instanceof GoalReachedAction) {
                GoalReachedAction reachedAction = (GoalReachedAction) action;
                String newStatus = reachedAction.getStatus();
                Outcome outcome = new PlayerWonOutcome(actor);
                return new GoalEndedEvent(player, this.copyWithStatus(newStatus, reachedAction).finish(outcome), outcome);
            } else if (action instanceof TimeoutPunishmentAction) {
                TimeoutPunishmentAction punishmentAction = (TimeoutPunishmentAction) action;
                bank.addPenalty(player, punishmentAction.getAmount());
                long interest = bank.getBet(player).getBet().getInterest().getAmount();
                if (interest <= 0L) {
                    Outcome outcome = new PlayerLostOutcome(player);
                    return new GoalEndedEvent(player, this.copyWithAction(action).finish(outcome), outcome);
                } else {
                    return new GoalChangedStatusUpdateMissedEvent(player, this.copyWithAction(action));
                }
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    public GoalState copyWithStatus(String newStatus, Action latestAction) {
        TreeSet<EventRecord> newRecords = new TreeSet<EventRecord>(eventRecords);
        newRecords.add(new EventRecord(latestAction, DateTime.now()));
        return new GoalState(
            goalKey,
            startDate,
            deadline,
            player,
            bank,
            goal,
            timezone,
            tag,
            configuration,
            context,
            supporters,
            newStatus,
            phase,
            latestAction,
            newRecords,
            outcome
        );
    }

    public GoalState copyWithAction(Action latestAction) {
        TreeSet<EventRecord> newRecords = new TreeSet<EventRecord>(eventRecords);
        newRecords.add(new EventRecord(latestAction, DateTime.now()));
        return new GoalState(
            goalKey,
            startDate,
            deadline,
            player,
            bank,
            goal,
            timezone,
            tag,
            configuration,
            context,
            supporters,
            status,
            phase,
            latestAction,
            newRecords,
            outcome
        );
    }

    public GoalState finish(Outcome outcome) {
         switch (phase) {
            case started:
            case betOff:
                TreeSet<EventRecord> newRecords = new TreeSet<EventRecord>(eventRecords);
                newRecords.add(new EventRecord(new GoalEndedEvent(player, this, outcome), DateTime.now()));
                return new GoalState(
                    goalKey,
                    startDate,
                    deadline,
                    player,
                    bank,
                    goal,
                    timezone,
                    tag,
                    configuration,
                    context,
                    supporters,
                    status,
                    GoalPhase.finished,
                    lastAction,
                    newRecords,
                    outcome
                );
            default:
                return this;
        }
    }

    public GoalState forbidBet() {
        switch (phase) {
            case started:
                TreeSet<EventRecord> newRecords = new TreeSet<EventRecord>(eventRecords);
                newRecords.add(new EventRecord(new GoalStartedEvent(player, this), DateTime.now()));
                return new GoalState(
                    goalKey,
                    startDate,
                    deadline,
                    player,
                    bank,
                    goal,
                    timezone,
                    tag,
                    configuration,
                    context,
                    supporters,
                    status,
                    GoalPhase.betOff,
                    lastAction,
                    newRecords,
                    outcome
                );
            default:
                return this;
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
