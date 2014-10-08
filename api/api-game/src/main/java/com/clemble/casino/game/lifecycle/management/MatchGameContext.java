package com.clemble.casino.game.lifecycle.management;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.game.lifecycle.management.outcome.GameOutcome;
import com.clemble.casino.game.lifecycle.management.outcome.PlayerWonOutcome;
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
    final private List<GameOutcome> outcomes = new ArrayList<GameOutcome>();

    @JsonCreator
    public MatchGameContext(@JsonProperty(GameSessionAware.SESSION_KEY) String sessionKey,
                            @JsonProperty("currentSession") String currentSession,
                            @JsonProperty("playerContexts") List<MatchGamePlayerContext> playerContexts,
                            @JsonProperty(value = "parent", required = false) GameContext<?> parent,
                            @JsonProperty("pot") long pot,
                            @JsonProperty("outcomes") List<GameOutcome> outcomes) {
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
            Collections.<GameOutcome>emptyList());
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

    public List<GameOutcome> getOutcomes() {
        return outcomes;
    }

    public void addOutcome(GameOutcome outcome) {
        this.outcomes.add(outcome);
        if (outcome instanceof PlayerWonOutcome) {
            getPlayerContext(((PlayerWonOutcome) outcome).getWinner()).addOutcome((PlayerWonOutcome) outcome);
        }
    }

}
