package com.clemble.casino.game.json;

import com.clemble.casino.event.action.PlayerDefaultAction;
import com.clemble.casino.game.GamePaymentSource;
import com.clemble.casino.game.lifecycle.management.*;
import com.clemble.casino.game.lifecycle.management.event.*;
import com.clemble.casino.lifecycle.management.event.action.bet.BetAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.GiveUpAction;
import com.clemble.casino.lifecycle.management.event.action.surrender.TimeoutSurrenderAction;
import com.clemble.casino.game.lifecycle.management.event.action.SelectAction;
import com.clemble.casino.game.lifecycle.construction.event.*;
import com.clemble.casino.game.lifecycle.initiation.event.GameInitiationCompleteEvent;
import com.clemble.casino.json.ClembleJsonModule;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.clemble.casino.game.lifecycle.construction.AutomaticGameRequest;
import com.clemble.casino.game.lifecycle.construction.AvailabilityGameRequest;
import com.clemble.casino.game.lifecycle.construction.ManagerGameConstructionRequest;
import com.clemble.casino.game.lifecycle.initiation.event.GameInitiationCreatedEvent;
import com.clemble.casino.game.lifecycle.initiation.event.GameInitiationCanceledEvent;
import com.clemble.casino.game.lifecycle.initiation.event.GameInitiationConfirmedEvent;
import com.clemble.casino.game.lifecycle.management.iterator.SequentialPlayerIterator;
import com.clemble.casino.game.lifecycle.configuration.MatchGameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
import com.clemble.casino.game.lifecycle.configuration.TournamentGameConfiguration;
import com.clemble.casino.game.lifecycle.management.unit.Chip;

public class GameJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Game");
        module.registerSubtypes(new NamedType(BetAction.class, BetAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(PlayerDefaultAction.class, PlayerDefaultAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(SelectAction.class, SelectAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(GiveUpAction.class, GiveUpAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(TimeoutSurrenderAction.class, TimeoutSurrenderAction.JSON_TYPE));
        module.registerSubtypes(new NamedType(AutomaticGameRequest.class, AutomaticGameRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(AvailabilityGameRequest.class, AvailabilityGameRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(ManagerGameConstructionRequest.class, ManagerGameConstructionRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameConstructionCanceledEvent.class, GameConstructionCanceledEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(RoundGameState.class, RoundGameState.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchGameState.class, MatchGameState.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TournamentGameState.class, TournamentGameState.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(GameConstructionCompleteEvent.class, GameConstructionCompleteEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GameConstructionPlayerInvitedEvent.class, GameConstructionPlayerInvitedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GameInitiationCreatedEvent.class, GameInitiationCreatedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GameInitiationCanceledEvent.class, GameInitiationCanceledEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GameInitiationConfirmedEvent.class, GameInitiationConfirmedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GameInitiationCompleteEvent.class, GameInitiationCompleteEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(MatchChangedEvent.class, MatchChangedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(MatchEndedEvent.class, MatchEndedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(MatchStartedEvent.class, MatchStartedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(GamePlayerMovedEvent.class, GamePlayerMovedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(RoundEndedEvent.class, RoundEndedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(RoundStartedEvent.class, RoundStartedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(RoundChangedEvent.class, RoundChangedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(TournamentEndedEvent.class, TournamentEndedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(TournamentChangedEvent.class, TournamentChangedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(TournamentStartedEvent.class, TournamentStartedEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(SequentialPlayerIterator.class, SequentialPlayerIterator.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchGameContext.class, MatchGameContext.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundGameContext.class, RoundGameContext.class.getAnnotation(JsonTypeName.class).value()));

//        module.registerSubtypes(new NamedType(PlayerNumberRule.class, PlayerNumberRule.class.getAnnotation(JsonTypeName.class).value()));
//        module.registerSubtypes(new NamedType(PrivacyRule.class, PrivacyRule.class.getAnnotation(JsonTypeName.class).value()));
//        module.registerSubtypes(new NamedType(GiveUpRule.class, GiveUpRule.class.getAnnotation(JsonTypeName.class).value()));
//        module.registerSubtypes(new NamedType(MoveTimeRule.class, MoveTimeRule.class.getAnnotation(JsonTypeName.class).value()));
//        module.registerSubtypes(new NamedType(TotalTimeRule.class, TotalTimeRule.class.getAnnotation(JsonTypeName.class).value()));
//        module.registerSubtypes(new NamedType(MatchFillRule.class, MatchFillRule.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(MatchGameConfiguration.class, MatchGameConfiguration.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundGameConfiguration.class, RoundGameConfiguration.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TournamentGameConfiguration.class, TournamentGameConfiguration.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(GamePaymentSource.class, GamePaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(TournamentGameContext.class, TournamentGameContext.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(Chip.class, Chip.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
