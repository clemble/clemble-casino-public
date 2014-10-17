package com.clemble.casino.game.lifecycle.management;

import com.clemble.casino.game.lifecycle.management.event.*;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.initiation.InitiationState;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.ComparatorUtils;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.lifecycle.management.outcome.DrawOutcome;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.clemble.casino.game.lifecycle.configuration.MatchGameConfiguration;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.clemble.casino.player.PlayerAwareUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

@JsonTypeName("match")
public class MatchGameState implements GameState<MatchGameContext> {

    final static private Logger LOG = LoggerFactory.getLogger(MatchGameState.class);

    final private GameUnit state;
    final private MatchGameContext context;
    final private MatchGameConfiguration configuration;
    final private int version;

    @JsonCreator
    public MatchGameState(
            @JsonProperty("context") MatchGameContext context,
            @JsonProperty("configuration") MatchGameConfiguration configuration,
            @JsonProperty("state") GameUnit state,
            @JsonProperty("version") int version) {
        // TODO Create MatchState and move logic from MatchGameContext to state
        this.state = state;
        this.context = context;
        this.version = context.getOutcomes().size();
        this.configuration = configuration;
    }

    @Override
    public MatchGameContext getContext(){
        return context;
    }

    public MatchGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public MatchStartedEvent start(){
        return new MatchStartedEvent(context.getSessionKey(), this, nextInitiation());
    }

    @Override
    public GameManagementEvent process(Event event) {
        LOG.debug("{} match processing {}", context.getSessionKey(), event);
        if(event instanceof GameEndedEvent) {
            context.addOutcome(((GameEndedEvent) event).getOutcome());
            int gamesLeft = configuration.getConfigurations().size() - context.getOutcomes().size();
            MultiValueMap<String, PlayerWonOutcome> wonOutcomes = new LinkedMultiValueMap<String, PlayerWonOutcome>();
            for(Outcome outcome: context.getOutcomes())
                if(outcome instanceof  PlayerWonOutcome)
                    wonOutcomes.add(((PlayerWonOutcome) outcome).getPlayer(), (PlayerWonOutcome) outcome);
            // Step 1. Searching for a leader in the pot
            List<Entry<String, List<PlayerWonOutcome>>> sortedContexts = new ArrayList<Entry<String, List<PlayerWonOutcome>>>(wonOutcomes.entrySet());
            Collections.sort(sortedContexts, ComparatorUtils.WON_OUT_COMPARATOR);
            Entry<String, List<PlayerWonOutcome>> leader = sortedContexts.get(0);
            int leaderScore = leader.getValue().size();
            int nextAfterLeaderScore = 0;
            if (sortedContexts.size() > 1) {
                Entry<String, List<PlayerWonOutcome>> nextAfterLeader = sortedContexts.get(1);
                nextAfterLeaderScore = nextAfterLeader.getValue().size();
            }
            // Step 2. Checking leader can be reached
            if (leaderScore > nextAfterLeaderScore &&
               (nextAfterLeaderScore + gamesLeft < leaderScore)) {
                LOG.debug("{} match winner determined {}", context.getSessionKey(), leader.getKey());
                return new MatchEndedEvent(context.getSessionKey(), this, new PlayerWonOutcome(leader.getKey()));
            }
            // Step 3. If no games left mark as a draw
            if (gamesLeft == 0) {
                LOG.debug("{} no more games left, considering this as a draw");
                return new MatchEndedEvent(context.getSessionKey(), this, new DrawOutcome());
            }
        }
        // Step 4. Constructing next match initiation
        GameInitiation nextInitiation = nextInitiation();
        // Step 5. Sending Game Changed event
        return new MatchChangedEvent(context.getSessionKey(), this, nextInitiation.getSessionKey(), nextInitiation);
    }

    private GameInitiation nextInitiation() {
        int gameNum = context.getOutcomes().size();
        String nextSessionKey = context.getSessionKey() + String.valueOf(gameNum);
        LOG.debug("{} launching new game {} with key {}", context.getSessionKey(), gameNum, nextSessionKey);
        context.setCurrentSession(nextSessionKey);
        return new GameInitiation(
                nextSessionKey,
                InitiationState.pending,
                PlayerAwareUtils.toPlayerList(context.getPlayerContexts()),
                configuration.getConfigurations().get(gameNum));
    }

    @Override
    public GameUnit getState() {
        return state;
    }

    @Override
    public int getVersion() {
        return context.getOutcomes().size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchGameState)) return false;

        MatchGameState that = (MatchGameState) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = context != null ? context.hashCode() : 0;
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }

}
