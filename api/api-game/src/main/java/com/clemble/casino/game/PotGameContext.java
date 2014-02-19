package com.clemble.casino.game;

import java.util.ArrayList;
import java.util.List;

import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.outcome.GameOutcome;
import com.clemble.casino.game.outcome.PlayerWonOutcome;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("potContext")
public class PotGameContext extends GameContext<PotGamePlayerContext> {

    /**
     * Generated 01/02/14
     */
    private static final long serialVersionUID = 554706514619333631L;

    private long pot; // TODO make this immutable
    private GameSessionKey currentSession;
    final private List<GameOutcome> outcomes = new ArrayList<GameOutcome>();

    @JsonCreator
    public PotGameContext(@JsonProperty("session") GameSessionKey session,
                          @JsonProperty("currentSession") GameSessionKey currentSession,
                          @JsonProperty("playerContexts") List<PotGamePlayerContext> playerContexts,
                          @JsonProperty(value = "parent", required = false) GameContext<?> parent,
                          @JsonProperty("pot") long pot,
                          @JsonProperty("outcomes") List<GameOutcome> outcomes) {
        super(session, parent, playerContexts);
        this.pot = pot;
        this.currentSession = currentSession;
        this.outcomes.addAll(outcomes);
    }

    public PotGameContext(GameInitiation initiation, GameContext<?> parent) {
        super(initiation.getSession(), parent, PotGamePlayerContext.construct(initiation));
    }

    public long getPot() {
        return pot;
    }

    public GameSessionKey getCurrentSession() {
        return currentSession;
    }
    
    public void setCurrentSession(GameSessionKey currentSession) {
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
