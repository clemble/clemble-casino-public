package com.clemble.casino.android;

import java.io.IOException;

import com.clemble.casino.android.player.AndroidFacadeRegistrationService;
import com.clemble.casino.client.ClembleCasinoOperations;
import com.clemble.casino.client.ClembleCasinoRegistrationOperations;
import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.registration.PlayerCredential;
import com.clemble.casino.registration.service.FacadeRegistrationService;
import com.clemble.casino.registration.PlayerRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.registration.PlayerSocialRegistrationRequest;

public class AndroidCasinoRegistrationTemplate implements ClembleCasinoRegistrationOperations {

    final private String host;
    final private FacadeRegistrationService facadeRegistrationService;

    public AndroidCasinoRegistrationTemplate(String host) {
        this.host = host;
        this.facadeRegistrationService = new AndroidFacadeRegistrationService(host);
    }

    @Override
    public ClembleCasinoOperations login(PlayerCredential playerCredential) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        // PlayerLoginRequest loginRequest = new PlayerLoginRequest(playerCredential);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.login(playerCredential));
    }

    @Override
    public ClembleCasinoOperations createPlayer(PlayerCredential playerCredential, PlayerProfile playerProfile) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerRegistrationRequest loginRequest = new PlayerRegistrationRequest(playerCredential, playerProfile);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.register(loginRequest));
    }

    @Override
    public ClembleCasinoOperations createSocialPlayer(PlayerCredential playerCredential, SocialConnectionData socialConnectionData) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialRegistrationRequest socialRegistrationRequest = new PlayerSocialRegistrationRequest(playerCredential, socialConnectionData);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.register(socialRegistrationRequest));
    }

    @Override
    public ClembleCasinoOperations createSocialPlayer(PlayerCredential playerCredential, SocialAccessGrant accessGrant) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialGrantRegistrationRequest socialRegistrationRequest = new PlayerSocialGrantRegistrationRequest(playerCredential, accessGrant);
        // Step 3. Generating ClembleTemplate
        return casinoTemplate(facadeRegistrationService.register(socialRegistrationRequest));
    }

    private ClembleCasinoTemplate casinoTemplate(String player) {
        try {
            return new ClembleCasinoTemplate("", "", "", "", player, host);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private ClembleCasinoTemplate casinoTemplate(PlayerToken token, ClembleConsumerDetails consumerDetails) {
//        TODO this is disabled temporely since, it's not active in the system, can deal with it later
//        String consumerKey = token.getConsumerKey();
//        String consumerSecret = new String(Base64.encodeBase64(consumerDetails.getSignatureSecret().getPrivateKey().getEncoded()), Charset.forName("UTF-8"));
//        String accessToken = token.getValue();
//        String accessTokenSecret = String.valueOf(token.getSecretKey().getEncoded());
//        String player = token.getPlayer();
//        try {
//            return new ClembleCasinoTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret, player, host);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

}
