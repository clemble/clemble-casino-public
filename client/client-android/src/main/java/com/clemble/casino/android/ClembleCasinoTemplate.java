package com.clemble.casino.android;

import java.io.IOException;

import com.clemble.casino.android.game.*;
import com.clemble.casino.android.goal.AndroidGoalOperations;
import com.clemble.casino.android.payment.AndroidPlayerAccountService;
import com.clemble.casino.android.player.*;
import com.clemble.casino.client.goal.GoalOperations;
import com.clemble.casino.game.lifecycle.initiation.event.GameInitiationCreatedEvent;
import com.clemble.casino.game.lifecycle.record.service.GameRecordService;
import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.payment.service.PaymentTransactionOperations;
import com.clemble.casino.payment.service.PlayerAccountService;
import com.clemble.casino.player.service.*;
import com.clemble.casino.registration.PlayerPasswordResetRequest;
import com.clemble.casino.registration.service.PlayerPasswordResetService;
import com.clemble.casino.registration.service.PlayerSignOutService;
import com.clemble.casino.tag.service.PlayerTagService;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.oauth1.AbstractOAuth1ApiBinding;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.payment.AndroidPaymentTransactionService;
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
import com.clemble.casino.game.lifecycle.management.GameState;
import com.clemble.casino.game.lifecycle.management.event.RoundEvent;
import com.clemble.casino.game.lifecycle.construction.service.AutoGameConstructionService;
import com.clemble.casino.game.lifecycle.construction.service.AvailabilityGameConstructionService;
import com.clemble.casino.game.lifecycle.management.service.GameActionService;
import com.clemble.casino.game.lifecycle.configuration.service.GameConfigurationService;
import com.clemble.casino.game.lifecycle.initiation.service.GameInitiationService;

public class ClembleCasinoTemplate extends AbstractOAuth1ApiBinding implements ClembleCasinoOperations {

    /**
     * Generated 19/11/13
     */
    private static final long serialVersionUID = 103204849955372481L;

    final private String player;
    final private EventListenerOperations listenerOperations;
    final private PlayerNotificationService notificationService;
    final private PlayerSessionService playerSessionOperations;
    final private PlayerProfileService profileOperations;
    final private PlayerImageService imageOperations;
    final private PlayerConnectionService connectionOperations;
    final private PlayerFeedService feedService;
    final private PlayerFriendInvitationService friendInvitationService;
    final private PlayerPresenceService presenceOperations;
    final private PaymentTransactionOperations transactionOperations;
    final private PlayerAccountService accountService;
    final private GameRecordService recordOperations;
    final private GameConstructionOperations constructionOperations;
    final private GameActionOperationsFactory actionOperationsFactory;

    final private GoalOperations goalOperations;

    final private PlayerSignOutService signOutService;
    final private PlayerPasswordResetService passwordResetService;

    final private PlayerEmailService emailService;
    final private PlayerTagService tagService;

    @SuppressWarnings({ "rawtypes" })
    public ClembleCasinoTemplate(
        String consumerKey,
        String consumerSecret,
        String accessToken,
        String accessTokenSecret,
        final String player,
        String host) throws IOException {
        super(consumerKey, consumerSecret, accessToken, accessTokenSecret);

        RestTemplate restTemplate = getRestTemplate();
        restTemplate.getInterceptors().add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Cookie", "player=" + player);
                return execution.execute(request, body);
            }
        });

        this.player = player;
        this.playerSessionOperations = new AndroidPlayerSessionService(restTemplate, host);
        this.playerSessionOperations.create();


        this.listenerOperations = new RabbitEventListenerTemplate(player, host, ObjectMapperUtils.OBJECT_MAPPER);
        // TODO either make it part of server event or find a nicer way to deal with this
        this.listenerOperations.subscribe(new EventTypeSelector(RoundEvent.class), new PlayerToMoveEventEmulator(player, listenerOperations));
        // Step 1. Creating PlayerProfile service
        this.profileOperations = new AndroidPlayerProfileService(restTemplate, host);
        // Step 1.0. Creating Player image service
        this.imageOperations = new AndroidPlayerImageService(restTemplate, host);
        // Step 1.1. Creating Player connections service
        this.connectionOperations = new AndroidPlayerConnectionService(restTemplate, host);
        // Step 1.2. Creating friend invitation service
        this.friendInvitationService = new AndroidPlayerFriendInvitationService(restTemplate, host);
        // Step 1.3. Creating notification service
        this.notificationService = new AndroidPlayerNotificationService(restTemplate, host);
        // Step 1.4. Creating feed service
        this.feedService = new AndroidPlayerFeedService(restTemplate, host);
        // Step 2. Creating PlayerPresence service
        this.presenceOperations = new AndroidPlayerPresenceService(restTemplate, host);
        // Step 3. Creating PaymentTransaction service
        this.transactionOperations = new PaymentTransactionOperations(new AndroidPaymentTransactionService(restTemplate, host));
        // Step 4. Creating GameConstruction services
        AutoGameConstructionService constructionService = new AndroidAutoGameConstructionService(restTemplate, host);
        AvailabilityGameConstructionService availabilityConstructionService = new AndroidAvailabilityGameConstructionService<GameState>(restTemplate, host);
        GameInitiationService initiationService = new AndroidGameInitiationService(restTemplate, host);
        GameConfigurationService configurationService = new AndroidGameConfigurationService(restTemplate, host);
        GameActionService actionService = new AndroidGameActionTemplate(host, restTemplate);
        this.actionOperationsFactory = new GameActionTemplateFactory(player, listenerOperations, actionService);
        this.constructionOperations = new GameConstructionTemplate(player, constructionService, availabilityConstructionService, initiationService, configurationService, listenerOperations);
        this.recordOperations = new AndroidGameRecordService(restTemplate, host);
        // Step 5. Registering listener operations
        this.listenerOperations.subscribe(new EventTypeSelector(GameInitiationCreatedEvent.class), new GameInitiationReadyEventEmulator(initiationService));
        // Step 6. Creating goal service
        this.goalOperations = new AndroidGoalOperations(host, restTemplate);
        // Step 7. Creating account service
        this.accountService = new AndroidPlayerAccountService(restTemplate, host);

        // Step 8. Creating signOut service
        this.signOutService = new AndroidPlayerSignOutService(restTemplate, host);
        // Step 9. Creating password reset service
        this.passwordResetService = new AndroidPlayerPasswordResetService(restTemplate, host);
        // Step 10. Creating email service
        this.emailService = new AndroidPlayerEmailService(restTemplate, host);
        // Step 11. Creating tag service
        this.tagService = new AndroidPlayerTagService(restTemplate, host);
    }

    @Override
    public PlayerProfileService profileOperations() {
        return profileOperations;
    }

    @Override
    public PlayerImageService imageOperations() {
        return imageOperations;
    }

    @Override
    public PlayerConnectionService connectionOperations(){
        return connectionOperations;
    }

    @Override
    public PlayerFriendInvitationService friendInvitationService() {
        return friendInvitationService;
    }

    @Override
    public PlayerPresenceService presenceOperations() {
        return presenceOperations;
    }

    @Override
    public PlayerNotificationService notificationService() {
        return notificationService;
    }

    @Override
    public PlayerFeedService feedService() {
        return feedService;
    }

    @Override
    public PlayerSessionService sessionOperations() {
        return playerSessionOperations;
    }

    @Override
    public PlayerAccountService accountService() {
        return accountService;
    }

    @Override
    public PaymentTransactionOperations paymentOperations() {
        return transactionOperations;
    }

    @Override
    public GameRecordService gameRecordOperations() {
        return recordOperations;
    }

    @Override
    public GoalOperations goalOperations() {
        return goalOperations;
    }

    @Override
    public PlayerPasswordResetService passwordResetService() {
        return passwordResetService;
    }

    @Override
    public PlayerEmailService emailService() {
        return emailService;
    }

    @Override
    public PlayerTagService tagService() {
        return tagService;
    }

    @Override
    public GameConstructionOperations gameConstructionOperations() {
        return constructionOperations;
    }

    @Override
    public <State extends GameState> GameActionOperations<State> gameActionOperations(String session) {
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
        jacksonConverter.setObjectMapper(ObjectMapperUtils.OBJECT_MAPPER);
        return jacksonConverter;
    }

    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new ClembleCasinoResponseErrorHandler(ObjectMapperUtils.OBJECT_MAPPER));
    }

    @Override
    public void signOut() {
        // Step 1. Close listeners
        try {
            close();
        } catch (Throwable throwable) { }
        // Step 2. Sign out
        signOutService.signOut();
    }

    @Override
    public void close() {
        if(listenerOperations != null)
            listenerOperations.close();
    }

}
