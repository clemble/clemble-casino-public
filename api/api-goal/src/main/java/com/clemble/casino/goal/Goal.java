package com.clemble.casino.goal;

import com.clemble.casino.money.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by mavarazy on 8/2/14.
 */
public class Goal implements GoalAware {

    final private String player;
    final private String goal;
    final private String description;
    final private GoalState state;
    final private Date dueDate;

    @JsonCreator
    public Goal(
            @JsonProperty("player") String player,
            @JsonProperty("goal") String goal,
            @JsonProperty("description") String description,
            @JsonProperty("dueDate") Date dueDate,
            @JsonProperty("state") GoalState state) {
        this.player = player;
        this.dueDate = dueDate;
        this.description = description;
        this.state = state;
        this.goal = goal;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    public String getDescription() {
        return description;
    }

    public GoalState getState() {
        return state;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Goal cloneWithPlayerAndGoal(String player, String goal, GoalState state) {
        return new Goal(player, goal, description, dueDate, state);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goal goal1 = (Goal) o;

        if (description != null ? !description.equals(goal1.description) : goal1.description != null) return false;
        if (dueDate != null ? !dueDate.equals(goal1.dueDate) : goal1.dueDate != null) return false;
        if (goal != null ? !goal.equals(goal1.goal) : goal1.goal != null) return false;
        if (player != null ? !player.equals(goal1.player) : goal1.player != null) return false;
        if (state != goal1.state) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = player != null ? player.hashCode() : 0;
        result = 31 * result + (goal != null ? goal.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Goal{" +
            "player='" + player + '\'' +
            ", goal='" + goal + '\'' +
            ", description='" + description + '\'' +
            ", state=" + state +
            ", dueDate=" + dueDate +
            '}';
    }
}
