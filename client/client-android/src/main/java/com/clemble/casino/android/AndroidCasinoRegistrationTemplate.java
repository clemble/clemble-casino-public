package com.clemble.casino.android;

import java.io.IOException;

import com.clemble.casino.android.player.AndroidFacadeRegistrationService;
import com.clemble.casino.client.ClembleCasinoOperations;
import com.clemble.casino.client.ClembleCasinoRegistrationOperations;
import com.clemble.casino.registration.*;
import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.registration.service.FacadeRegistrationService;

public class AndroidCasinoRegistrationTemplate implements ClembleCasinoRegistrationOperations {

    final private String host;
    final private FacadeRegistrationService facadeRegistrationService;

    public AndroidCasinoRegistrationTemplate(String host) {
        this.host = host;
        this.facadeRegistrationService = new AndroidFacadeRegistrationService(host);
    }

    @Override
    public ClembleCasinoOperations login(PlayerLoginRequest playerCredential) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        // PlayerLoginRequest loginRequest = new PlayerLoginRequest(playerCredential);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.login(playerCredential).getPlayer());
    }

    @Override
    public ClembleCasinoOperations register(PlayerCredential playerCredential, PlayerProfile playerProfile) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerRegistrationRequest loginRequest = PlayerRegistrationRequest.create(playerCredential, playerProfile);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.register(loginRequest).getPlayer());
    }

    @Override
    public ClembleCasinoOperations register(PlayerCredential playerCredential, SocialConnectionData socialConnectionData) {
        // Step 1. Generating consumer details
        // ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialRegistrationRequest socialRegistrationRequest = new PlayerSocialRegistrationRequest(playerCredential, socialConnectionData);
        // Step 3. Constructing casino operations
        return casinoTemplate(facadeRegistrationService.register(socialRegistrationRequest));
    }

    @Override
    public ClembleCasinoOperations register(PlayerCredential playerCredential, SocialAccessGrant accessGrant) {
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
