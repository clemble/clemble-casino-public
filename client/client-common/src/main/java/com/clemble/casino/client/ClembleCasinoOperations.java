package com.clemble.casino.client;

import java.io.Closeable;

import com.clemble.casino.client.goal.GoalOperations;
import com.clemble.casino.game.lifecycle.record.service.GameRecordService;
import com.clemble.casino.payment.service.PaymentTransactionOperations;
import com.clemble.casino.payment.service.PlayerAccountService;
import com.clemble.casino.player.service.*;
import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.client.game.GameActionOperations;
import com.clemble.casino.client.game.GameConstructionOperations;
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.player.PlayerAware;

public interface ClembleCasinoOperations extends ApiBinding, Closeable, PlayerAware {

    PlayerProfileService profileOperations();

    PlayerImageService imageOperations();

    PlayerConnectionService connectionOperations();

    PlayerFriendInvitationService friendInvitationService();

    PlayerPresenceService presenceOperations();

    PlayerSessionService sessionOperations();

    PlayerAccountService accountService();

    PaymentTransactionOperations paymentOperations();

    EventListenerOperations listenerOperations();

    GameConstructionOperations gameConstructionOperations();

    <State extends GameState> GameActionOperations<State> gameActionOperations(String sessionKey);

    GameRecordService gameRecordOperations();

    GoalOperations goalOperations();

    // TODO safety concern, since RestTemplate is reused all over the place, make a deep copy of returned rest template
    RestTemplate getRestTemplate();

    @Override
    void close();

}
