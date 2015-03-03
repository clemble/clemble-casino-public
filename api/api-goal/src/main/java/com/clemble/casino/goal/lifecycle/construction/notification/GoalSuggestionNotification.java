package com.clemble.casino.goal.lifecycle.construction.notification;

import com.clemble.casino.goal.lifecycle.construction.GoalSuggestion;
import com.clemble.casino.goal.notification.GoalNotification;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;

/**
 * Created by mavarazy on 3/3/15.
 */
@JsonTypeName(GoalSuggestionNotification.JSON_TYPE)
public class GoalSuggestionNotification implements GoalNotification {

    final public static String JSON_TYPE = "notification:goal:suggestion";

    final private String key;
    final private String player;
    final private GoalSuggestion suggestion;
    final private boolean accepted;
    final private DateTime created;

    @JsonCreator
    public GoalSuggestionNotification(
        @JsonProperty("key") String key,
        @JsonProperty(PLAYER) String player,
        @JsonProperty("suggestion") GoalSuggestion suggestion,
        @JsonProperty("accepted") boolean accepted,
        @JsonProperty("created") DateTime created) {
        this.key = key;
        this.player = player;
        this.suggestion = suggestion;
        this.created = created;
        this.accepted = accepted;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    public GoalSuggestion getSuggestion() {
        return suggestion;
    }

    public boolean getAccepted(){
        return accepted;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalSuggestionNotification)) return false;

        GoalSuggestionNotification that = (GoalSuggestionNotification) o;

        if (!created.equals(that.created)) return false;
        if (!key.equals(that.key)) return false;
        if (!player.equals(that.player)) return false;
        if (!suggestion.equals(that.suggestion)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + suggestion.hashCode();
        result = 31 * result + created.hashCode();
        return result;
    }

    public static GoalSuggestionNotification create(GoalSuggestion suggestion, boolean accepted) {
        return new GoalSuggestionNotification(
            suggestion.getPlayer(),
            suggestion.getSuggester(),
            suggestion,
            accepted,
            DateTime.now()
        );
    }

}
