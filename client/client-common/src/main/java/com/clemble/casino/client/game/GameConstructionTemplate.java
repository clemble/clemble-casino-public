package com.clemble.casino.client.game;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.game.event.GameSessionAwareEvent;
import com.clemble.casino.game.construction.*;
import com.clemble.casino.game.construction.event.InvitationAcceptedEvent;
import com.clemble.casino.game.construction.event.InvitationDeclinedEvent;
import com.clemble.casino.game.construction.event.InvitationResponseEvent;
import com.clemble.casino.game.construction.service.AutoGameConstructionService;
import com.clemble.casino.game.construction.service.AvailabilityGameConstructionService;
import com.clemble.casino.game.configuration.service.GameConfigurationService;
import com.clemble.casino.game.construction.service.GameInitiationService;
import com.clemble.casino.game.configuration.GameConfiguration;
import com.clemble.casino.player.event.PlayerEvent;
import com.clemble.casino.utils.CollectionUtils;
import com.clemble.casino.game.GameWebMapping;

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
        AutomaticGameRequest automaticGameRequest = new AutomaticGameRequest(specification);
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
        AvailabilityGameRequest availabilityGameRequest = new AvailabilityGameRequest(specification, participants, GameDeclineBehavior.invalidate);
        // Step 2. Making actual construction
        return availabilityConstructionService.construct(availabilityGameRequest);
    }

    @Override
    public GameConstruction getConstruct(String sessionKey) {
        return availabilityConstructionService.getConstruction(sessionKey);
    }

    @Override
    public GameConstruction accept(String sessionKey) {
        return reply(sessionKey, new InvitationAcceptedEvent(player, sessionKey));
    }

    @Override
    public GameConstruction decline(String sessionKey) {
        return reply(sessionKey, new InvitationDeclinedEvent(player, sessionKey));
    }

    @Override
    public GameConstruction reply(String sessionKey, InvitationResponseEvent responce) {
        return availabilityConstructionService.reply(responce);
    }

    @Override
    public Collection<GameConstruction> pending() {
        return availabilityConstructionService.getPending(player);
    }

    @Override
    public GameInitiation confirm(String sessionKey) {
        return initiationService.confirm(sessionKey);
    }

    @Override
    public PlayerEvent getResponse(String sessionKey, String fromPlayer) {
        return availabilityConstructionService.getReply(sessionKey, fromPlayer);
    }

    @Override
    public EventListenerController watch(String sessionKey, EventListener<GameSessionAwareEvent> constructionListener) {
        // Step 1. Sanity checks
        if (sessionKey == null || constructionListener == null)
            return null;
        // Step 2. Subscribing to specific table
        return listenerOperations.subscribe(GameWebMapping.toTable(sessionKey), constructionListener);
    }

    @Override
    public List<GameConfiguration> getConfigurations() {
        return configurationService.getConfigurations();
    }

}
