package com.clemble.casino.client.game;

import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.construct.GameInitiation;
import com.clemble.casino.game.service.GameInitiationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameInitiationTemplate implements GameInitiationOperations {

    final private Logger LOG = LoggerFactory.getLogger(GameInitiationTemplate.class);

    final private String player;
    final private GameInitiationService initiationService;

    public GameInitiationTemplate(String player, GameInitiationService initiationService) {
        this.player = player;
        this.initiationService = initiationService;
    }

    @Override
    public GameInitiation confirm(GameSessionKey session) {
        LOG.debug("Confirming {}", session);
        return initiationService.confirm(session.getGame(), session.getSession(), player);
    }

}
