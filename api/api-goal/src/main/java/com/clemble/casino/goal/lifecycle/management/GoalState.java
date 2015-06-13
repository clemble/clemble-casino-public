package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.ImmutablePair;
import com.clemble.casino.TimeZoneAware;
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
import com.clemble.casino.lifecycle.configuration.rule.timeout.GoalTimeSpanAware;
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
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;

import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalState implements
    Record<GoalConfiguration>,
    GoalAware,
    GoalPhaseAware,
    GoalDescriptionAware,
    GoalConfigurationAware,
    GoalStatusAware,
    GoalRoleAware,
    BankAware,
    GoalTimeSpanAware,
    TagAware {

    @Id
    final private String goalKey;
    final private String player;
    final private String goal;
    final private String status;
    final private String tag;
    final private Bank bank;
    final private GoalPhase phase;
    final private GoalConfiguration configuration;
    final private Set<String> supporters;
    final private DateTime startDate;
    final private DateTime deadline;
    final private DateTime lastUpdated;
    final private DateTimeZone timezone;
    final private SortedSet<EventRecord> eventRecords;
    final private SortedSet<GoalInspiration> inspirations;
    final private Outcome outcome;

    @JsonCreator
    public GoalState(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("startDate") DateTime startDate,
        @JsonProperty("deadline") DateTime deadline,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty(TIME_ZONE) DateTimeZone timezone,
        @JsonProperty("tag") String tag,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("status") String status,
        @JsonProperty("phase") GoalPhase phase,
        @JsonProperty("eventRecords") SortedSet<EventRecord> eventRecords,
        @JsonProperty("outcome") Outcome outcome,
        @JsonProperty("inspirations") SortedSet<GoalInspiration> inspirations,
        @JsonProperty("lastUpdated") DateTime lastUpdated) {
        this.goalKey = goalKey;
        this.player = player;
        this.phase = phase;
        this.startDate = startDate;
        this.deadline = deadline;
        this.supporters = supporters;
        this.configuration = configuration;
        this.bank = bank;
        this.goal = goal;
        this.tag = tag;
        this.status = status;
        this.timezone = timezone;
        this.eventRecords = eventRecords;
        this.outcome = outcome;
        this.inspirations = inspirations;
        this.lastUpdated = lastUpdated;
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
    public DateTimeZone getTimezone() {
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

    @Override
    public GoalPhase getPhase() {
        return phase;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    @Override
    public DateTime getStartDate() {
        return startDate;
    }

    @Override
    public DateTime getDeadline() {
        return deadline;
    }

    @Override
    public DateTime getLastUpdated() {
        return lastUpdated;
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

    public GoalStartedEvent start() {
        return new GoalStartedEvent(player, this);
    }

    public SortedSet<GoalInspiration> getInspirations() {
        return inspirations;
    }

    public Map.Entry<GoalEvent, GoalState> process(Event actionEvent){
        if(actionEvent instanceof LifecycleStartedEvent) {
            return new ImmutablePair<GoalEvent, GoalState>(new GoalStartedEvent(player, this), this);
        }
        if(actionEvent instanceof PlayerAction<?>) {
            String actor = ((PlayerAction) actionEvent).getPlayer();
            Action action = ((PlayerAction) actionEvent).getAction();
            if (action instanceof BetOffAction) {
                GoalState newState = this.forbidBet();
                return new ImmutablePair<GoalEvent, GoalState>(new GoalChangedBetOffEvent(player, newState), newState);
            } else if (action instanceof GoalStatusUpdateAction) {
                GoalStatusUpdateAction statusUpdateAction = ((GoalStatusUpdateAction) action);
                String newStatus = statusUpdateAction.getStatus();
                GoalState newState = this.copyWithStatus(newStatus, action);
                return new ImmutablePair<GoalEvent, GoalState>(new GoalChangedStatusEvent(player, this.copyWithStatus(newStatus, action)), newState);
            } else if (action instanceof SurrenderAction) {
                Outcome outcome = new PlayerLostOutcome(actor);
                GoalState newState = this.finish(outcome);
                return new ImmutablePair<GoalEvent, GoalState>(new GoalEndedEvent(player, newState, outcome), newState);
            } else if (action instanceof BetAction) {
                switch (phase) {
                    case betOff:
                    case finished:
                        throw new IllegalArgumentException();
                }
                if (this.supporters.contains(actor) || this.player.equals(actor))
                    throw new IllegalArgumentException();
                GoalRoleConfiguration roleConfiguration = configuration.getSupporterConfiguration();
                int amount = ((BetAction) action).getBet();
                int interest = ((100 + roleConfiguration.getPercentage()) * amount) / 100;
                Bet bet = new Bet(Money.create(Currency.point, amount), Money.create(Currency.point, interest));
                PlayerBet playerBid = new PlayerBet(actor, bet);
                this.bank.add(playerBid);
                this.supporters.add(actor);
                return new ImmutablePair<GoalEvent, GoalState>(new GoalChangedBetEvent(player, this, playerBid), this);
            } else if (action instanceof GoalReachedAction) {
                GoalReachedAction reachedAction = (GoalReachedAction) action;
                String newStatus = reachedAction.getStatus();
                Outcome outcome = new PlayerWonOutcome(actor);
                GoalState newState = this.copyWithStatus(newStatus, reachedAction).finish(outcome);
                return new ImmutablePair<GoalEvent, GoalState>(new GoalEndedEvent(player, newState, outcome), newState);
            } else if (action instanceof TimeoutPunishmentAction) {
                TimeoutPunishmentAction punishmentAction = (TimeoutPunishmentAction) action;
                bank.addPenalty(player, punishmentAction.getAmount());
                long interest = bank.getBet(player).getBet().getInterest().getAmount();
                if (interest <= 0L) {
                    Outcome outcome = new PlayerLostOutcome(player);
                    GoalState newState = this.copyWithAction(action).finish(outcome);
                    return new ImmutablePair<GoalEvent, GoalState>(new GoalEndedEvent(player, newState, outcome), newState);
                } else {
                    GoalState newState = this.copyWithAction(action);
                    return new ImmutablePair<GoalEvent, GoalState>(new GoalChangedStatusUpdateMissedEvent(player, this.copyWithAction(action)), newState);
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private GoalState copyWithStatus(String newStatus, Action latestAction) {
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
            supporters,
            newStatus,
            phase,
            newRecords,
            outcome,
            inspirations,
            DateTime.now(timezone)
        );
    }

    private GoalState copyWithAction(Action latestAction) {
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
            supporters,
            status,
            phase,
            newRecords,
            outcome,
            inspirations,
            DateTime.now(timezone)
        );
    }

    private GoalState finish(Outcome outcome) {
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
                    supporters,
                    status,
                    GoalPhase.finished,
                    newRecords,
                    outcome,
                    inspirations,
                    DateTime.now(timezone)
                );
            default:
                return this;
        }
    }

    private GoalState forbidBet() {
        switch (phase) {
            case started:
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
                    supporters,
                    status,
                    GoalPhase.betOff,
                    eventRecords,
                    outcome,
                    inspirations,
                    lastUpdated
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
        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return !(bank != null ? !bank.equals(that.bank) : that.bank != null);

    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (bank != null ? bank.hashCode() : 0);
        return result;
    }
}
