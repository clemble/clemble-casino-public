package com.clemble.casino.goal.lifecycle.management;

import com.clemble.casino.goal.GoalAware;
import com.clemble.casino.goal.GoalDescriptionAware;
import com.clemble.casino.payment.Bank;
import com.clemble.casino.payment.BankAware;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * Created by mavarazy on 3/14/15.
 */
public class GoalVictory implements GoalDescriptionAware, GoalAware, GoalRoleAware, BankAware {

    @Id
    final private String goalKey;
    final private String player;
    final private Set<String> supporters;
    final private String goal;
    final private Bank bank;
    final private String timezone;

    @JsonCreator
    public GoalVictory(
        @JsonProperty("goalKey") String goalKey,
        @JsonProperty("player") String player,
        @JsonProperty("supporters") Set<String> supporters,
        @JsonProperty("goal") String goal,
        @JsonProperty("bank") Bank bank,
        @JsonProperty(TIME_ZONE) String timezone
    ) {
        this.goalKey = goalKey;
        this.player = player;
        this.supporters = supporters;
        this.goal = goal;
        this.bank = bank;
        this.timezone = timezone;
    }

    @Override
    public String getGoalKey() {
        return goalKey;
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public Set<String> getSupporters() {
        return supporters;
    }

    @Override
    public String getGoal() {
        return goal;
    }

    @Override
    public Bank getBank() {
        return bank;
    }

    @Override
    public String getTimezone() {
        return timezone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GoalVictory)) return false;

        GoalVictory that = (GoalVictory) o;

        if (!bank.equals(that.bank)) return false;
        if (!goal.equals(that.goal)) return false;
        if (!goalKey.equals(that.goalKey)) return false;
        if (!player.equals(that.player)) return false;
        if (!supporters.equals(that.supporters)) return false;
        if (!timezone.equals(that.timezone)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = goalKey.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + supporters.hashCode();
        result = 31 * result + goal.hashCode();
        result = 31 * result + bank.hashCode();
        result = 31 * result + timezone.hashCode();
        return result;
    }

    public static GoalVictory create(GoalState state) {
        return new GoalVictory(
            state.getGoalKey(),
            state.getPlayer(),
            state.getSupporters(),
            state.getGoal(),
            state.getBank(),
            state.getTimezone()
        );
    }

}
