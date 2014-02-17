package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.event.NotificationMapping;
import com.clemble.casino.event.PlayerAwareEvent;
import com.clemble.casino.game.GameSessionAwareEvent;
import com.clemble.casino.game.GameSessionKey;
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

public class GameConstructionTemplate implements GameConstructionOperations {

    final private String player;

    final private EventListenerOperations listenerOperations;
    final private GameConfigurationService configurationService;
    final private AutoGameConstructionService constructionService;
    final private AvailabilityGameConstructionService availabilityConstructionService;
    final private GameInitiationService initiationService;

    public GameConstructionTemplate(String player,
            AutoGameConstructionService constructionService,
            AvailabilityGameConstructionService availabilityConstructionService,
            GameInitiationService initiationService,
            GameConfigurationService specificationService,
            EventListenerOperations listenersManager) {
        this.player = checkNotNull(player);
        this.constructionService = checkNotNull(constructionService);
        this.initiationService = checkNotNull(initiationService);
        this.availabilityConstructionService = checkNotNull(availabilityConstructionService);
        this.configurationService = checkNotNull(specificationService);
        this.listenerOperations = checkNotNull(listenersManager);
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
    public GameConstruction getConstruct(GameSessionKey session) {
        return availabilityConstructionService.getConstruction(session.getGame(), session.getSession());
    }

    @Override
    public GameConstruction accept(GameSessionKey session) {
        return reply(session, new InvitationAcceptedEvent(player, session));
    }

    @Override
    public GameConstruction decline(GameSessionKey session) {
        return reply(session, new InvitationDeclinedEvent(player, session));
    }

    @Override
    public GameConstruction reply(GameSessionKey session, InvitationResponseEvent responce) {
        return availabilityConstructionService.reply(responce);
    }

    @Override
    public Collection<GameInitiation> pending() {
        return availabilityConstructionService.getPending(player);
    }

    @Override
    public GameInitiation confirm(GameSessionKey session) {
        return initiationService.confirm(session.getGame(), session.getSession(), player);
    }

    @Override
    public PlayerAwareEvent getResponce(GameSessionKey session, String fromPlayer) {
        return availabilityConstructionService.getReply(session.getGame(), session.getSession(), fromPlayer);
    }

    @Override
    public EventListenerController watch(GameSessionKey session, EventListener<GameSessionAwareEvent> constructionListener) {
        // Step 1. Sanity checks
        if (session == null || constructionListener == null)
            return null;
        // Step 2. Subscribing to specific table
        return listenerOperations.subscribe(NotificationMapping.toTable(session), constructionListener);
    }

    @Override
    public GameConfigurations getConfigurations() {
        return configurationService.getConfigurations();
    }

}
