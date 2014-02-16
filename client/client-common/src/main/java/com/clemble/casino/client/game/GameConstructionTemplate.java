package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.event.NotificationMapping;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameSessionAwareEvent;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.construct.AutomaticGameRequest;
import com.clemble.casino.game.construct.AvailabilityGameRequest;
import com.clemble.casino.game.construct.GameConstruction;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.event.schedule.InvitationAcceptedEvent;
import com.clemble.casino.game.event.schedule.InvitationDeclinedEvent;
import com.clemble.casino.game.event.schedule.InvitationResponseEvent;
import com.clemble.casino.game.service.AutoGameConstructionService;
import com.clemble.casino.game.service.AvailabilityGameConstructionService;
import com.clemble.casino.game.service.GameConfigurationService;
import com.clemble.casino.game.service.GameInitiationService;
import com.clemble.casino.game.specification.GameConfiguration;
import com.clemble.casino.game.specification.GameConfigurations;
import com.clemble.casino.utils.CollectionUtils;

public class GameConstructionTemplate<T extends GameState> implements GameConstructionOperations<T> {

    /**
     * Generated 12/11/13
     */
    private static final long serialVersionUID = -7073347007265754892L;

    final private String player;

    final private Game game;
    final private GameActionOperationsFactory actionOperationFactory;
    final private EventListenerOperations listenerOperations;
    final private GameConfigurationService configurationService;
    final private AutoGameConstructionService constructionService;
    final private AvailabilityGameConstructionService availabilityConstructionService;
    final private GameInitiationService initiationService;

    public GameConstructionTemplate(String player,
            Game game,
            GameActionOperationsFactory actionOperations,
            AutoGameConstructionService constructionService,
            AvailabilityGameConstructionService availabilityConstructionService,
            GameInitiationService initiationService,
            GameConfigurationService specificationService,
            EventListenerOperations listenersManager) {
        this.player = checkNotNull(player);
        this.game = checkNotNull(game);
        this.actionOperationFactory = checkNotNull(actionOperations);
        this.constructionService = checkNotNull(constructionService);
        this.initiationService = checkNotNull(initiationService);
        this.availabilityConstructionService = checkNotNull(availabilityConstructionService);
        this.configurationService = checkNotNull(specificationService);
        this.listenerOperations = checkNotNull(listenersManager);
    }

    @Override
    public Game getGame() {
        return game;
    }

    @Override
    public GameConstruction constructAutomatch(GameConfiguration specification) {
        // Step 1. Constructing automatic request
        AutomaticGameRequest automaticGameRequest = new AutomaticGameRequest(player, specification);
        // Step 2. Making actual construction
        return constructionService.construct(automaticGameRequest);
    }

    @Override
    public GameConstruction constructAvailability(GameConfiguration specification, String... players) {
        return constructAvailability(specification, CollectionUtils.immutableList(players));
    }

    @Override
    public GameConstruction constructAvailability(GameConfiguration specification, Collection<String> participants) {
        // Step 1. Constructing availability request
        AvailabilityGameRequest availabilityGameRequest = new AvailabilityGameRequest(player, specification, participants);
        // Step 2. Making actual construction
        return availabilityConstructionService.construct(availabilityGameRequest);
    }

    @Override
    public GameConstruction getConstruct(String session) {
        return availabilityConstructionService.getConstruction(game, session);
    }

    @Override
    public GameConstruction accept(String session) {
        return reply(session, new InvitationAcceptedEvent(player, toSessionKey(session)));
    }

    @Override
    public GameConstruction decline(String session) {
        return reply(session, new InvitationDeclinedEvent(player, toSessionKey(session)));
    }

    @Override
    public GameConstruction reply(String session, InvitationResponseEvent responce) {
        return availabilityConstructionService.reply(responce);
    }

    @Override
    public Collection<GameInitiation> pending() {
        return availabilityConstructionService.getPending(player);
    }

    @Override
    public GameInitiation ready(String session) {
        return initiationService.confirm(game, session, player);
    }

    @Override
    public PlayerAwareEvent getResponce(String session, String fromPlayer) {
        return availabilityConstructionService.getReply(game, session, fromPlayer);
    }

    @Override
    public EventListenerController watch(String session, EventListener<GameSessionAwareEvent> constructionListener) {
        // Step 1. Sanity checks
        if (session == null || constructionListener == null)
            return null;
        // Step 2. Subscribing to specific table
        return listenerOperations.subscribe(NotificationMapping.toTable(toSessionKey(session)), constructionListener);
    }

    @Override
    public GameActionOperations<T> getActionOperations(String session) {
        return actionOperationFactory.construct(toSessionKey(session));
    }

    private GameSessionKey toSessionKey(String session) {
        return new GameSessionKey(game, session);
    }

    @Override
    public GameConfigurations getConfigurations() {
        return configurationService.getConfigurations();
    }

}
