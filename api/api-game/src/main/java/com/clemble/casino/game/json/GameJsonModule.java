package com.clemble.casino.game.json;

import com.clemble.casino.event.DefaultPlayerEvent;
import com.clemble.casino.event.surrender.GiveUpEvent;
import com.clemble.casino.event.surrender.MoveTimeoutSurrenderEvent;
import com.clemble.casino.event.surrender.TotalTimeoutSurrenderEvent;
import com.clemble.casino.event.bet.BetEvent;
import com.clemble.casino.game.action.SelectEvent;
import com.clemble.casino.game.construction.event.*;
import com.clemble.casino.game.event.*;
import com.clemble.casino.game.rule.match.MatchFillRule;
import com.clemble.casino.json.ClembleJsonModule;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.clemble.casino.game.construction.AutomaticGameRequest;
import com.clemble.casino.game.construction.AvailabilityGameRequest;
import com.clemble.casino.game.construction.ManagerGameConstructionRequest;
import com.clemble.casino.game.construction.ScheduledGameRequest;
import com.clemble.casino.game.construction.event.GameInitiatedEvent;
import com.clemble.casino.game.construction.event.GameInitiationCanceledEvent;
import com.clemble.casino.game.construction.event.GameInitiationConfirmedEvent;
import com.clemble.casino.game.event.MatchChangedEvent;
import com.clemble.casino.game.event.MatchEndedEvent;
import com.clemble.casino.game.event.MatchStartedEvent;
import com.clemble.casino.game.event.PlayerMovedEvent;
import com.clemble.casino.game.event.RoundEndedEvent;
import com.clemble.casino.game.event.RoundStartedEvent;
import com.clemble.casino.game.event.RoundStateChangedEvent;
import com.clemble.casino.game.event.TournamentEndedEvent;
import com.clemble.casino.game.event.TournamentStartedEvent;
import com.clemble.casino.game.iterator.SequentialPlayerIterator;
import com.clemble.casino.game.MatchGameContext;
import com.clemble.casino.game.outcome.DrawOutcome;
import com.clemble.casino.game.outcome.NoOutcome;
import com.clemble.casino.game.outcome.PlayerWonOutcome;
import com.clemble.casino.game.RoundGameContext;
import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.rule.privacy.PrivacyRule;
import com.clemble.casino.game.rule.giveup.GiveUpRule;
import com.clemble.casino.rule.time.MoveTimeRule;
import com.clemble.casino.rule.time.TotalTimeRule;
import com.clemble.casino.game.configuration.MatchGameConfiguration;
import com.clemble.casino.game.configuration.RoundGameConfiguration;
import com.clemble.casino.game.configuration.TournamentGameConfiguration;
import com.clemble.casino.game.TournamentGameContext;
import com.clemble.casino.game.unit.Chip;

public class GameJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Game");
        module.registerSubtypes(new NamedType(BetEvent.class, BetEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(DefaultPlayerEvent.class, DefaultPlayerEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(SelectEvent.class, SelectEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GiveUpEvent.class, GiveUpEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MoveTimeoutSurrenderEvent.class, MoveTimeoutSurrenderEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TotalTimeoutSurrenderEvent.class, TotalTimeoutSurrenderEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(AutomaticGameRequest.class, AutomaticGameRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(AvailabilityGameRequest.class, AvailabilityGameRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(ManagerGameConstructionRequest.class, ManagerGameConstructionRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(ScheduledGameRequest.class, ScheduledGameRequest.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameCanceledEvent.class, GameCanceledEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(InvitationResponseEvent.class, InvitationResponseEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameConstructedEvent.class, GameConstructedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(InvitationAcceptedEvent.class, InvitationAcceptedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(InvitationDeclinedEvent.class, InvitationDeclinedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerInvitedEvent.class, PlayerInvitedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameInitiatedEvent.class, GameInitiatedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameInitiationExpiredEvent.class, GameInitiationExpiredEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameInitiationCanceledEvent.class, GameInitiationCanceledEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GameInitiationConfirmedEvent.class, GameInitiationConfirmedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchChangedEvent.class, MatchChangedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchEndedEvent.class, MatchEndedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchStartedEvent.class, MatchStartedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerMovedEvent.class, PlayerMovedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundEndedEvent.class, RoundEndedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundStartedEvent.class, RoundStartedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundStateChangedEvent.class, RoundStateChangedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TournamentEndedEvent.class, TournamentEndedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TournamentStartedEvent.class, TournamentStartedEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(SequentialPlayerIterator.class, SequentialPlayerIterator.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchGameContext.class, MatchGameContext.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(DrawOutcome.class, DrawOutcome.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(NoOutcome.class, NoOutcome.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerWonOutcome.class, PlayerWonOutcome.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundGameContext.class, RoundGameContext.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PlayerNumberRule.class, PlayerNumberRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PrivacyRule.class, PrivacyRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(GiveUpRule.class, GiveUpRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MoveTimeRule.class, MoveTimeRule.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TotalTimeRule.class, TotalTimeRule.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(MatchGameConfiguration.class, MatchGameConfiguration.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(RoundGameConfiguration.class, RoundGameConfiguration.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(TournamentGameConfiguration.class, TournamentGameConfiguration.class.getAnnotation(JsonTypeName.class).value()));

        module.registerSubtypes(new NamedType(TournamentGameContext.class, TournamentGameContext.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(Chip.class, Chip.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(MatchFillRule.class, MatchFillRule.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
