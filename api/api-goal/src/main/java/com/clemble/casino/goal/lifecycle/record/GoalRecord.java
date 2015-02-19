package com.clemble.casino.goal.lifecycle.record;

import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.record.EventRecord;
import com.clemble.casino.lifecycle.record.Record;
import com.clemble.casino.lifecycle.record.RecordState;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.payment.BankAware;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.tag.TagAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by mavarazy on 9/20/14.
 */
public class GoalRecord implements
    Record<GoalConfiguration>,
    GoalAware,
    GoalDescriptionAware,
    PlayerAware,
    BankAware,
    GoalConfigurationAware,
    TagAware {

    @Id
    final private String goalKey;
    final private RecordState state;
    final private String player;
    final private String goal;
    final private DateTimeZone timezone;
    final private String tag;
    final private Bank bank;
    final private GoalConfiguration configuration;
    final private SortedSet<EventRecord> eventRecords = new TreeSet<EventRecord>();
    final private Outcome outcome;

    @JsonCreator
    public GoalRecord(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("state") RecordState state,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("goal") String goal,
        @JsonProperty("timezone") DateTimeZone timezone,
        @JsonProperty("tag") String tag,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("eventRecords") Set<EventRecord> eventRecords,
        @JsonProperty("outcome") Outcome outcome) {
        this.goal = goal;
        this.timezone = timezone;
        this.tag = tag;
        this.state = state;
        this.player = player;
        this.goalKey = goalKey;
        this.bank = bank;
        this.configuration = configuration;
        this.eventRecords.addAll(eventRecords);
        this.outcome = outcome;
    }


    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public RecordState getState() {
        return state;
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
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public SortedSet<EventRecord> getEventRecords() {
        return eventRecords;
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
    public Outcome getOutcome() {
        return outcome;
    }

    public GoalRecord copy(RecordState state, Outcome outcome) {
        return new GoalRecord(goalKey,
            player,
            state,
            bank,
            goal,
            timezone,
            tag,
            configuration,
            eventRecords,
            outcome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalRecord that = (GoalRecord) o;

        if (!configuration.equals(that.configuration)) return false;
        if (!eventRecords.equals(that.eventRecords)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;
        if (state != that.state) return false;
        if (!bank.equals(that.bank)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + configuration.hashCode();
        result = 31 * result + eventRecords.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + bank.hashCode();
        return result;
    }

}
