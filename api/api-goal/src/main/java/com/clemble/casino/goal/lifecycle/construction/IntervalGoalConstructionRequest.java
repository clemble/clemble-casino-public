package com.clemble.casino.goal.lifecycle.construction;

import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.IntervalGoalConfigurationBuilder;
import com.clemble.casino.lifecycle.construction.Construction;
import com.clemble.casino.lifecycle.construction.ConstructionRequest;
import com.clemble.casino.lifecycle.construction.ConstructionState;
import com.clemble.casino.tag.TagUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Created by mavarazy on 2/6/15.
 */
public class IntervalGoalConstructionRequest implements ConstructionRequest<GoalConfiguration>, GoalDescriptionAware {

    final private int bet;
    final private String goal;
    final private String reward;
    final private DateTimeZone timezone;
    final private IntervalGoalConfigurationBuilder intervalBuilder;

    @JsonCreator
    public IntervalGoalConstructionRequest(
        @JsonProperty("bet") int bet,
        @JsonProperty("goal") String goal,
        @JsonProperty("reward") String reward,
        @JsonProperty("timezone") DateTimeZone timezone,
        @JsonProperty("intervalBuilder")IntervalGoalConfigurationBuilder intervalBuilder) {
        this.intervalBuilder = intervalBuilder;
        this.timezone = timezone;
        this.bet = bet;
        this.goal = goal;
        this.reward = reward;
    }

    @Override
    @JsonIgnore
    public GoalConfiguration getConfiguration() {
        return intervalBuilder.toConfiguration(bet);
    }

    public DateTimeZone getTimezone() {
        return timezone;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public String getReward() {
        return reward;
    }

    public IntervalGoalConfigurationBuilder getIntervalBuilder() {
        return intervalBuilder;
    }

    public GoalConstructionRequest toConstructionRequest() {
        return new GoalConstructionRequest(
            intervalBuilder.toConfiguration(bet),
            goal,
            reward,
            timezone);
    }

    @Override
    public GoalConstruction toConstruction(String player, String goalKey) {
        return new GoalConstruction(goalKey, player, goal, reward, TagUtils.getTag(goal), DateTime.now(timezone), getConfiguration(), ConstructionState.pending);
    }
}
