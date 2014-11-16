package com.clemble.casino.game.lifecycle.management;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

import com.clemble.casino.ImmutablePair;
import com.clemble.casino.game.lifecycle.management.event.*;
import com.clemble.casino.lifecycle.management.outcome.Outcome;
import com.clemble.casino.lifecycle.initiation.InitiationState;
import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.initiation.GameInitiation;
import com.clemble.casino.lifecycle.management.outcome.PlayerWonOutcome;
import com.clemble.casino.game.lifecycle.configuration.GameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.GameConfigurationAware;
import com.clemble.casino.game.lifecycle.configuration.TournamentGameConfiguration;
import com.clemble.casino.game.lifecycle.management.unit.GameUnit;
import com.clemble.casino.player.PlayerAware;
import com.clemble.casino.player.PlayerAwareUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/02/14.
 */
@JsonTypeName("tournament")
public class TournamentGameState implements GameState<TournamentGameContext> {

    final private static String TOURNAME_SEPARATOR = "_";

    final private TournamentGameContext context;
    final private List<TournamentLevel> levels = new ArrayList<TournamentLevel>();
    final private TournamentGameConfiguration configuration;

    @JsonCreator
    public TournamentGameState(
        @JsonProperty("configuration") TournamentGameConfiguration configuration,
        @JsonProperty("context") TournamentGameContext context,
        @JsonProperty("state") GameUnit state,
        @JsonProperty("version") int version) {
        List<String> players = PlayerAwareUtils.toPlayerList(context.getPlayerContexts());
        this.context = context;
        this.configuration = configuration;
        // Step 1. Calculating number of levels
        int numLevels = Double.valueOf(Math.log(players.size()) / Math.log(configuration.getNumberRule().getMinPlayers())).intValue();
        for(int i = 0; i < numLevels; i++)
            levels.add(new TournamentLevel(context.getSessionKey() + (TOURNAME_SEPARATOR + i), configuration.getConfiguration(), context));
        TournamentLevel tournamentLevel = levels.get(levels.size() - 1);
        // Step 2. Dividing in groups
        int groupSize = configuration.getConfiguration().getNumberRule().getMinPlayers();
        for(int i = 0; i < players.size(); i++) {
            tournamentLevel.add(i / groupSize,new TournamentLeaf(players.get(i), context.getSessionKey(), null));
        }
    }

    public TournamentGameContext getContext(){
        return context;
    }

    public TournamentGameConfiguration getConfiguration() {
        return configuration;
    }

    @Override
    public TournamentStartedEvent start() {
        return new TournamentStartedEvent(getContext().getSessionKey());
    }

    @Override
    public GameManagementEvent process(Event event) {
        GameManagementEvent resultEvent = null;
        if (event instanceof GameEndedEvent) {
            // Step 0. Reading session key and outcome
            GameContext context = ((GameEndedEvent) event).getState().getContext();
            Outcome outcome = ((GameEndedEvent) event).getOutcome();
            TournamentGameContext leafContext = (TournamentGameContext) context.getParent();
            if (outcome instanceof PlayerWonOutcome) {
                String leader = ((PlayerWonOutcome) outcome).getPlayer();
                TournamentLeaf leaf = leafContext.getLeaf();
                String sessionStr = leafContext.getSessionKey();
                // Step 1. Reading group and level
                int firstSeparator = sessionStr.indexOf(TOURNAME_SEPARATOR);
                int lastSeparator = sessionStr.lastIndexOf(TOURNAME_SEPARATOR);
                int level = Integer.valueOf(sessionStr.substring(firstSeparator, lastSeparator));
                int group = Integer.valueOf(sessionStr.substring(lastSeparator));
                TournamentLeaf finalLeaf = new TournamentLeaf(leader, leaf.getSessionKey(), leaf.getLeafs());
                // Step 2. Processing level & group
                if (level == 0) {
                    return new TournamentEndedEvent(context.getSessionKey(), outcome, this);
                } else {
                    // Step 3. Creating final leaf
                    Entry<Integer, Integer> levToGroup = new ImmutablePair<Integer, Integer>(level, group);
                    return levels.get(levToGroup.getKey()).add(levToGroup.getValue(), finalLeaf);
                }
            } else {
                // TODO add processing
            }
        } else {
            throw new IllegalArgumentException();
        }
        return null;
    }

    @Override
    public GameUnit getState() {
        return null;
    }

    @Override
    public int getVersion() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentGameState)) return false;

        TournamentGameState that = (TournamentGameState) o;

        if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
            return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        if (levels != null ? !levels.equals(that.levels) : that.levels != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = context != null ? context.hashCode() : 0;
        result = 31 * result + (levels != null ? levels.hashCode() : 0);
        result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
        return result;
    }

    public class TournamentLevel implements GameConfigurationAware {

        final private AtomicInteger level;
        final private GameContext<?> parent;
        final private String session;
        final private GameConfiguration configuration;
        final private List<TournamentLeaf> waitingList = new ArrayList<TournamentLeaf>();

        public TournamentLevel(String session, GameConfiguration configuration, GameContext<?> context) {
            this.level = new AtomicInteger();
            this.parent = context;
            this.session = session;
            this.configuration = configuration;
        }

        public GameManagementEvent add(int group, TournamentLeaf pending) {
            // This is synchronous due to GameManager, careful with GameManager
            waitingList.add(pending);
            if(waitingList.size() == configuration.getNumberRule().getMinPlayers()) {
                String nextSession = session + TOURNAME_SEPARATOR + level.getAndIncrement();
                GameInitiation nextInitiaion = new GameInitiation(session + TOURNAME_SEPARATOR + level, InitiationState.pending, PlayerAwareUtils.toPlayerList(waitingList), configuration);
                TournamentLeaf leaf = new TournamentLeaf(PlayerAware.DEFAULT_PLAYER, nextSession, waitingList);
                TournamentGameContext parentContext = new TournamentGameContext(nextInitiaion, leaf, parent);
                waitingList.clear();
                return new TournamentChangedEvent(pending.getSessionKey(), nextSession, nextInitiaion);
            }
            return null;
        }

        @Override
        public GameConfiguration getConfiguration() {
            return configuration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TournamentLevel)) return false;

            TournamentLevel that = (TournamentLevel) o;

            if (configuration != null ? !configuration.equals(that.configuration) : that.configuration != null)
                return false;
            if (session != null ? !session.equals(that.session) : that.session != null) return false;
            if (waitingList != null ? !waitingList.equals(that.waitingList) : that.waitingList != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = level != null ? level.hashCode() : 0;
            result = 31 * result + (session != null ? session.hashCode() : 0);
            result = 31 * result + (configuration != null ? configuration.hashCode() : 0);
            result = 31 * result + (waitingList != null ? waitingList.hashCode() : 0);
            return result;
        }

    }

}
