package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.goal.GoalPartsAware;
import com.clemble.casino.goal.event.GoalEvent;
import com.clemble.casino.goal.event.action.GoalStatusUpdateAction;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfiguration;
import com.clemble.casino.goal.lifecycle.configuration.GoalConfigurationAware;
import com.clemble.casino.goal.lifecycle.management.event.GoalMissedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalReachedEvent;
import com.clemble.casino.goal.lifecycle.management.event.GoalStatusUpdatedEvent;
import com.clemble.casino.lifecycle.management.State;
import com.clemble.casino.lifecycle.management.event.action.surrender.SurrenderAction;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mavarazy on 10/9/14.
 */
public class GoalState implements State<GoalEvent, GoalContext>, GoalAware, GoalDescriptionAware, GoalConfigurationAware, GoalPartsAware, PlayerAware {

    final private String goalKey;
    final private String player;
    final private String goal;
    final private GoalConfiguration configuration;
    final private int parts;
    final private GoalContext context;
    private String status;
    private int progress;

    @JsonCreator
    public GoalState(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("goal") String goal,
        @JsonProperty("configuration") GoalConfiguration configuration,
        @JsonProperty("context") GoalContext context,
        @JsonProperty("status") String status,
        @JsonProperty("progress") int progress,
        @JsonProperty("parts") int parts) {
        this.goalKey = goalKey;
        this.player = player;
        this.configuration = configuration;
        this.context = context;
        this.goal = goal;
        this.status = status;
        this.progress = progress;
        this.parts = parts;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    public int getParts() {
        return parts;
    }

    public String getGoal() {
        return goal;
    }

    public int getProgress() {
        return progress;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public GoalContext getContext() {
        return context;
    }

    @Override
    public GoalConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public GoalEvent process(Event action){
        if(action instanceof GoalStatusUpdateAction) {
            GoalStatusUpdateAction statusUpdateAction = ((GoalStatusUpdateAction) action);
            this.status = statusUpdateAction.getStatus();
            this.progress = progress + statusUpdateAction.getProgress();
            if(this.progress >= parts) {
                return new GoalReachedEvent(goalKey);
            } else {
                return new GoalStatusUpdatedEvent(goalKey, statusUpdateAction.getPlayer(), status, progress);
            }
        } else if(action instanceof SurrenderAction) {
            return new GoalMissedEvent(goalKey);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GoalState that = (GoalState) o;

        if (parts != that.parts) return false;
        if (progress != that.progress) return false;
        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (goalKey != null ? !goalKey.equals(that.goalKey) : that.goalKey != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey != null ? goalKey.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + parts;
        result = 31 * result + (context != null ? context.hashCode() : 0);
        result = 31 * result + progress;
        return result;
    }
}
