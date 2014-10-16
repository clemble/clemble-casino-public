package com.clemble.casino.game.lifecycle.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potContext")
public class MatchGameContext extends GameContext<MatchGamePlayerContext> {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 554706514619333631L;

    private long pot; // TODO make this immutable
    private String currentSession;
    final private List<Outcome> outcomes = new ArrayList<Outcome>();

    @JsonCreator
    public MatchGameContext(@JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey,
                            @JsonProperty("currentSession") String currentSession,
                            @JsonProperty("playerContexts") List<MatchGamePlayerContext> playerContexts,
                            @JsonProperty(value = "parent", required = false) GameContext<?> parent,
                            @JsonProperty("pot") long pot,
                            @JsonProperty("outcomes") List<Outcome> outcomes) {
        super(sessionKey, parent, playerContexts);
        this.pot = pot;
        this.currentSession = currentSession;
        this.outcomes.addAll(outcomes);
    }

    public static MatchGameContext fromInitiation(GameInitiation initiation, GameContext<?> parent) {
        return new MatchGameContext(
            initiation.getSessionKey(),
            null,
            MatchGamePlayerContext.construct(initiation),
            parent,
            0L,
            Collections.<Outcome>emptyList());
    }

    public long getPot() {
        return pot;
    }

    public String getCurrentSession() {
        return currentSession;
    }
    
    public void setCurrentSession(String currentSession) {
        this.currentSession = currentSession;
    }

    public void add(long addition) {
        this.pot += addition;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public void addOutcome(Outcome outcome) {
        this.outcomes.add(outcome);
        if (outcome instanceof PlayerWonOutcome) {
            getPlayerContext(((PlayerWonOutcome) outcome).getPlayer()).addOutcome((PlayerWonOutcome) outcome);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchGameContext)) return false;
        if (!super.equals(o)) return false;

        MatchGameContext that = (MatchGameContext) o;

        if (pot != that.pot) return false;
        if (currentSession != null ? !currentSession.equals(that.currentSession) : that.currentSession != null)
            return false;
        if (outcomes != null ? !outcomes.equals(that.outcomes) : that.outcomes != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (pot ^ (pot >>> 32));
        result = 31 * result + (currentSession != null ? currentSession.hashCode() : 0);
        result = 31 * result + (outcomes != null ? outcomes.hashCode() : 0);
        return result;
    }
}
