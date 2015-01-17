package com.clemble.casino.goal.post;

import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.payment.Bank;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.Set;

/**
 * Created by mavarazy on 1/17/15.
 */
public class GoalStatusMissedPost implements GoalPost, GoalConfigurationAware  {

    final public static String JSON_TYPE = "post:goal:updated:missed";

    final private String key;
    final private String goalKey;
    final private String player;
    final private Bank bank;
    final private GoalConfiguration configuration;
    final private String goal;
    final private Set<String> supporters;
    final private DateTime startDate;
    final private long deadline;
    final private DateTime created;

    @JsonCreator
    public GoalStatusMissedPost(
        @JsonProperty("key") String key,
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("bank") Bank bank,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("goal") String goal,
        @JsonProperty("deadline") long deadline,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("startDate") DateTime startDate,
        @JsonProperty("created") DateTime created
    ) {
        this.key = key;
        this.goalKey = goalKey;
        this.player = player;
        this.supporters = supporters;
        this.goal = goal;
        this.bank = bank;
        this.configuration = configuration;
        this.startDate = startDate;
        this.deadline = deadline;
        this.created = created;
    }

    @Override
    public String getKey() {
        return key;
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
    public Set<String> getSupporters() {
        return supporters;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    @Override
    public long getDeadline() {
        return deadline;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    public static GoalStatusMissedPost create(GoalState state) {
        return new GoalStatusMissedPost(
            state.getGoalKey(),
            state.getGoalKey(),
            state.getPlayer(),
            state.getBank(),
            state.getConfiguration(),
            state.getGoal(),
            state.getConfiguration().getTotalTimeoutRule().getTimeoutCalculator().calculate(state.getStartDate().getMillis()),
            state.getSupporters(),
            state.getStartDate(),
            DateTime.now(DateTimeZone.UTC)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalStatusMissedPost that = (GoalStatusMissedPost) o;

        if (deadline != that.deadline) return false;
        if (!bank.equals(that.bank)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + goalKey.hashCode();
        result = 31 * result + (int) (deadline ^ (deadline >>> 32));
        return result;
    }


}
