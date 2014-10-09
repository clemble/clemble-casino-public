package com.clemble.casino.goal.lifecycle.record;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.GoalJudgeAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.lifecycle.record.EventRecord;
import com.clemble.casino.lifecycle.record.Record;
import com.clemble.casino.lifecycle.record.RecordState;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.SortedSet;

/**
 * Created by mavarazy on 9/20/14.
 */
public class GoalRecord implements Record<GoalConfiguration>, GoalAware, GoalJudgeAware, GoalDescriptionAware, PlayerAware, GoalConfigurationAware {

    @Id
    final private String goalKey;
    final private RecordState state;
    final private String player;
    final private String goal;
    final private String judge;
    final private GoalConfiguration configuration;
    final private SortedSet<EventRecord> eventRecords;

    @JsonCreator
    public GoalRecord(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("state") RecordState state,
        @JsonProperty("goal") String goal,
        @JsonProperty("judge") String judge,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("eventRecords") SortedSet<EventRecord> eventRecords) {
        this.goal = goal;
        this.state = state;
        this.judge = judge;
        this.player = player;
        this.goalKey = goalKey;
        this.configuration = configuration;
        this.eventRecords = eventRecords;
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
    public String getJudge() {
        return judge;
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

    public GoalRecord copy(RecordState state) {
        return new GoalRecord(goalKey,
            player,
            state,
            goal,
            judge,
            configuration,
            eventRecords);
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
        if (!judge.equals(that.judge)) return false;
        if (!player.equals(that.player)) return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + judge.hashCode();
        result = 31 * result + configuration.hashCode();
        result = 31 * result + eventRecords.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

}
