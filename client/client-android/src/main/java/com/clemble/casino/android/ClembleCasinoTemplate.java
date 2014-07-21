package com.clemble.casino.android;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.io.IOException;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.game.service.AndroidAutoGameConstructionService;
import com.clemble.casino.android.game.service.AndroidAvailabilityGameConstructionService;
import com.clemble.casino.android.game.service.AndroidGameActionTemplate;
import com.clemble.casino.android.game.service.AndroidGameConfigurationService;
import com.clemble.casino.android.game.service.AndroidGameInitiationService;
import com.clemble.casino.android.game.service.AndroidGameRecordService;
import com.clemble.casino.android.payment.AndroidPaymentTransactionService;
import com.clemble.casino.android.player.AndroidPlayerConnectionService;
import com.clemble.casino.android.player.AndroidPlayerPresenceService;
import com.clemble.casino.android.player.AndroidPlayerProfileService;
import com.clemble.casino.android.player.AndroidPlayerSessionService;
import com.clemble.casino.client.ClembleCasinoOperations;
import com.clemble.casino.client.error.ClembleCasinoResponseErrorHandler;
import com.clemble.casino.client.event.EventListenerOperations;
import com.clemble.casino.client.event.EventTypeSelector;
import com.clemble.casino.client.event.GameInitiationReadyEventEmulator;
import com.clemble.casino.client.event.PlayerToMoveEventEmulator;
import com.clemble.casino.client.event.RabbitEventListenerTemplate;
import com.clemble.casino.client.game.GameActionOperations;
import com.clemble.casino.client.game.GameActionOperationsFactory;
import com.clemble.casino.client.game.GameActionTemplateFactory;
import com.clemble.casino.client.game.GameConstructionOperations;
import com.clemble.casino.client.game.GameConstructionTemplate;
import com.clemble.casino.client.game.GameInitiationTemplate;
import com.clemble.casino.client.game.GameRecordOperations;
import com.clemble.casino.client.game.GameRecordTemplate;
import com.clemble.casino.client.payment.PaymentOperations;
import com.clemble.casino.client.payment.PaymentTemplate;
import com.clemble.casino.client.player.PlayerConnectionOperations;
import com.clemble.casino.client.player.PlayerConnectionTemplate;
import com.clemble.casino.client.player.PlayerPresenceOperations;
import com.clemble.casino.client.player.PlayerPresenceTemplate;
import com.clemble.casino.client.player.PlayerProfileOperations;
import com.clemble.casino.client.player.PlayerProfileTemplate;
import com.clemble.casino.client.player.PlayerSessionOperations;
import com.clemble.casino.client.player.PlayerSessionTemplate;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.game.GameState;
import com.clemble.casino.game.event.server.GameInitiatedEvent;
import com.clemble.casino.game.event.server.RoundEvent;
import com.clemble.casino.game.service.AutoGameConstructionService;
import com.clemble.casino.game.service.AvailabilityGameConstructionService;
import com.clemble.casino.game.service.GameActionService;
import com.clemble.casino.game.service.GameConfigurationService;
import com.clemble.casino.game.service.GameInitiationService;
import com.clemble.casino.payment.service.PaymentService;
import com.clemble.casino.player.service.PlayerConnectionService;
import com.clemble.casino.player.service.PlayerPresenceService;
import com.clemble.casino.player.service.PlayerProfileService;

public class ClembleCasinoTemplate extends AbstractOAuth1ApiBinding implements ClembleCasinoOperations {

    /**
     * Generated 19/11/13
     */
    private static final long serialVersionUID = 103204849955372481L;

    final private String host;
    final private String player;
    final private EventListenerOperations listenerOperations;
    final private PlayerSessionOperations playerSessionOperations;
    final private PlayerProfileOperations profileOperations;
    final private PlayerConnectionOperations connectionOperations;
    final private PlayerPresenceOperations presenceOperations;
    final private PaymentOperations transactionOperations;
    final private GameRecordOperations recordOperations;
    final private GameConstructionOperations constructionOperations;
    final private GameActionOperationsFactory actionOperationsFactory;

    @SuppressWarnings({ "rawtypes" })
    public ClembleCasinoTemplate(
        String consumerKey,
        String consumerSecret,
        String accessToken,
        String accessTokenSecret,
        String player,
        String host) throws IOException {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        this.host = host;
        this.player = player;
        this.playerSessionOperations = new PlayerSessionTemplate(player, new AndroidPlayerSessionService(getRestTemplate(), host));
        this.playerSessionOperations.create();

        this.listenerOperations = new RabbitEventListenerTemplate(player, host, ClembleCasinoConstants.OBJECT_MAPPER);
        // TODO either make it part of server event or find a nicer way to deal with this
        this.listenerOperations.subscribe(new EventTypeSelector(RoundEvent.class), new PlayerToMoveEventEmulator(player, listenerOperations));
        // Step 1. Creating PlayerProfile service
        PlayerProfileService playerProfileService = new AndroidPlayerProfileService(getRestTemplate(), host);
        this.profileOperations = new PlayerProfileTemplate(player, playerProfileService);
        // Step 1.1. Creating Player connections service
        PlayerConnectionService playerConnectionService = new AndroidPlayerConnectionService(getRestTemplate(), host);
        this.connectionOperations = new PlayerConnectionTemplate(player, playerConnectionService, profileOperations);
        // Step 2. Creating PlayerPresence service
        PlayerPresenceService playerPresenceService = new AndroidPlayerPresenceService(getRestTemplate(), host);
        this.presenceOperations = new PlayerPresenceTemplate(player, playerPresenceService, listenerOperations);
        // Step 3. Creating PaymentTransaction service
        PaymentService paymentTransactionService = new AndroidPaymentTransactionService(getRestTemplate(), host);
        this.transactionOperations = new PaymentTemplate(player, paymentTransactionService, listenerOperations);
        // Step 4. Creating GameConstruction services
        AutoGameConstructionService constructionService = new AndroidAutoGameConstructionService(getRestTemplate(), host);
        AvailabilityGameConstructionService availabilityConstructionService = new AndroidAvailabilityGameConstructionService<GameState>(getRestTemplate(), host);
        GameInitiationService initiationService = new AndroidGameInitiationService(getRestTemplate(), host);
        GameConfigurationService configurationService = new AndroidGameConfigurationService(getRestTemplate(), host);
        GameActionService actionService = new AndroidGameActionTemplate(host, getRestTemplate());
        this.actionOperationsFactory = new GameActionTemplateFactory(player, listenerOperations, actionService);
        this.constructionOperations = new GameConstructionTemplate(player, constructionService, availabilityConstructionService, initiationService, configurationService, listenerOperations);
        this.recordOperations = new GameRecordTemplate(new AndroidGameRecordService(getRestTemplate(), host));
        // Step 5. Registering listener operations
        this.listenerOperations.subscribe(new EventTypeSelector(GameInitiatedEvent.class), new GameInitiationReadyEventEmulator(new GameInitiationTemplate(player, initiationService)));
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public PlayerProfileOperations profileOperations() {
        return profileOperations;
    }

    @Override
    public PlayerConnectionOperations connectionOperations(){
        return connectionOperations;
    }

    @Override
    public PlayerPresenceOperations presenceOperations() {
        return presenceOperations;
    }

    @Override
    public PlayerSessionOperations sessionOperations() {
        return playerSessionOperations;
    }

    @Override
    public PaymentOperations paymentOperations() {
        return transactionOperations;
    }

    @Override
    public GameRecordOperations gameRecordOperations() {
        return recordOperations;
    }

    @Override
    public GameConstructionOperations gameConstructionOperations() {
        return constructionOperations;
    }

    @Override
    public <State extends GameState> GameActionOperations<State> gameActionOperations(GameSessionKey session) {
        // Step 1. Constructing appropriate action operations
        return actionOperationsFactory.construct(session);
    }

    @Override
    public String getPlayer() {
        return player;
    }

    @Override
    public EventListenerOperations listenerOperations() {
        return listenerOperations;
    }

    /**
     * Returns a {@link MappingJackson2HttpMessageConverter} to be used by the internal {@link RestTemplate}. Override to customize the message converter (for
     * example, to set a custom object mapper or supported media types). To remove/replace this or any of the other message converters that are registered by
     * default, override the getMessageConverters() method instead.
     */
    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
        jacksonConverter.setObjectMapper(ClembleCasinoConstants.OBJECT_MAPPER);
        return jacksonConverter;
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new ClembleCasinoResponseErrorHandler(ClembleCasinoConstants.OBJECT_MAPPER));
    }

    @Override
    public void close() {
        if(listenerOperations != null)
            listenerOperations.close();
    }

}
