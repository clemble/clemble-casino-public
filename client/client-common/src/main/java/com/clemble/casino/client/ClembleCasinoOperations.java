package com.clemble.casino.client;

import java.io.Closeable;

import org.springframework.social.ApiBinding;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.client.game.GameActionOperations;
import com.clemble.casino.client.game.GameConstructionOperations;
import com.clemble.casino.client.game.GameRecordOperations;
import com.clemble.casino.client.payment.PaymentOperations;
import com.clemble.casino.client.player.PlayerConnectionOperations;
import com.clemble.casino.client.player.PlayerPresenceOperations;
import com.clemble.casino.client.player.PlayerProfileOperations;
import com.clemble.casino.client.player.PlayerSessionOperations;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.clemble.casino.player.PlayerAware;

public interface ClembleCasinoOperations extends ApiBinding, Closeable, PlayerAware {

    public PlayerProfileOperations profileOperations();

    public PlayerConnectionOperations connectionOperations();

    public PlayerPresenceOperations presenceOperations();

    public PlayerSessionOperations sessionOperations();

    public PaymentOperations paymentOperations();

    public EventListenerOperations listenerOperations();

    public GameConstructionOperations gameConstructionOperations();

    public <State extends GameState> GameActionOperations<State> gameActionOperations(GameSessionKey session);

    public GameRecordOperations gameRecordOperations();

    // TODO safety concern, since RestTemplate is reused all over the place, make a deep copy of returned rest template
    public RestTemplate getRestTemplate();

    @Override
    public void close();
}
