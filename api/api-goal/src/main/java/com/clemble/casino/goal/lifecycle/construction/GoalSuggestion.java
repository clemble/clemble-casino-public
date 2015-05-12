package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.lifecycle.construction.ConstructionState;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.tag.TagAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.data.annotation.Id;

import java.util.HashSet;

/**
 * Created by mavarazy on 1/3/15.
 */
public class GoalSuggestion implements
    GoalDescriptionAware,
    PlayerAware,
    GoalAware,
    TagAware,
    CreatedAware {

    @Id
    final private String goalKey;
    final private String goal;
    final private String tag;
    final private String player;
    final private String suggester;
    final private GoalSuggestionState state;
    final private DateTime created;
    final private DateTimeZone timezone;

    @JsonCreator
    public GoalSuggestion(
        @JsonProperty(GOAL_KEY) String goalKey,
        @JsonProperty("goal") String goal,
        @JsonProperty(TIME_ZONE) DateTimeZone timezone,
        @JsonProperty("tag") String tag,
        @JsonProperty("player") String player,
        @JsonProperty("suggester") String suggester,
        @JsonProperty("state") GoalSuggestionState state,
        @JsonProperty("created") DateTime created) {
        this.goalKey = goalKey;
        this.player = player;
        this.suggester = suggester;
        this.goal = goal;
        this.timezone = timezone;
        this.tag = tag;
        this.state = state;
        this.created = created;
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
    public String getPlayer() {
        return player;
    }

    public String getSuggester() {
        return suggester;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    public GoalSuggestionState getState() {
        return  state;
    }

    public GoalConstruction toConstruction(GoalConfiguration configuration) {
        return new GoalConstruction(
            goalKey,
            player,
            goal,
            timezone,
            tag,
            DateTime.now(timezone),
            configuration,
            ConstructionState.constructed
        );
    }

    public GoalSuggestion copyWithStatus(GoalSuggestionState state) {
        return new GoalSuggestion(goalKey, goal, timezone, tag, player, suggester, state, DateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalSuggestion that = (GoalSuggestion) o;

        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!suggester.equals(that.suggester)) return false;
        if (!player.equals(that.player)) return false;
        if (state != that.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + state.hashCode();
        result = 31 * result + suggester.hashCode();
        result = 31 * result + player.hashCode();
        return result;
    }

}
