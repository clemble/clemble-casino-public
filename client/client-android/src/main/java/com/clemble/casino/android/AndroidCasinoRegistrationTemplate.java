package com.clemble.casino.android;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.codec.binary.Base64;

import com.clemble.casino.android.player.AndroidPlayerFacadeRegistrationService;
import com.clemble.casino.client.ClembleCasinoOperations;
import com.clemble.casino.client.ClembleCasinoRegistrationOperations;
import com.clemble.casino.player.SocialAccessGrant;
import com.clemble.casino.player.SocialConnectionData;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.client.ClembleConsumerDetails;
import com.clemble.casino.player.security.PlayerCredential;
import com.clemble.casino.player.security.PlayerToken;
import com.clemble.casino.player.service.PlayerFacadeRegistrationService;
import com.clemble.casino.player.web.PlayerLoginRequest;
import com.clemble.casino.player.web.PlayerRegistrationRequest;
import com.clemble.casino.player.web.PlayerSocialGrantRegistrationRequest;
import com.clemble.casino.player.web.PlayerSocialRegistrationRequest;
import com.clemble.casino.utils.ClembleConsumerDetailUtils;

public class AndroidCasinoRegistrationTemplate implements ClembleCasinoRegistrationOperations {

    final private String host;
    final private PlayerFacadeRegistrationService playerFacadeRegistrationService;

    public AndroidCasinoRegistrationTemplate(String host) {
        this.host = host;
        this.playerFacadeRegistrationService = new AndroidPlayerFacadeRegistrationService(host);
    }

    @Override
    public ClembleCasinoOperations login(PlayerCredential playerCredential) {
        // Step 1. Generating consumer details
        ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerLoginRequest loginRequest = new PlayerLoginRequest(consumerDetails, playerCredential);
        // Step 3. Constructing casino operations
        return casinoTemplate(playerFacadeRegistrationService.login(loginRequest), consumerDetails);
    }

    @Override
    public ClembleCasinoOperations createPlayer(PlayerCredential playerCredential, PlayerProfile playerProfile) {
        // Step 1. Generating consumer details
        ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerRegistrationRequest loginRequest = new PlayerRegistrationRequest(playerProfile, playerCredential, consumerDetails);
        // Step 3. Constructing casino operations
        return casinoTemplate(playerFacadeRegistrationService.createPlayer(loginRequest), consumerDetails);
    }

    @Override
    public ClembleCasinoOperations createSocialPlayer(PlayerCredential playerCredential, SocialConnectionData socialConnectionData) {
        // Step 1. Generating consumer details
        ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialRegistrationRequest socialRegistrationRequest = new PlayerSocialRegistrationRequest(consumerDetails, playerCredential, socialConnectionData);
        // Step 3. Constructing casino operations
        return casinoTemplate(playerFacadeRegistrationService.createSocialPlayer(socialRegistrationRequest), consumerDetails);
    }

    @Override
    public ClembleCasinoOperations createSocialPlayer(PlayerCredential playerCredential, SocialAccessGrant accessGrant) {
        // Step 1. Generating consumer details
        ClembleConsumerDetails consumerDetails = ClembleConsumerDetailUtils.generateDetails();
        // Step 2. Generating login request
        PlayerSocialGrantRegistrationRequest socialRegistrationRequest = new PlayerSocialGrantRegistrationRequest(consumerDetails, playerCredential, accessGrant);
        // Step 3. Generating ClembleTemplate
        return casinoTemplate(playerFacadeRegistrationService.createSocialGrantPlayer(socialRegistrationRequest), consumerDetails);
    }

    private ClembleCasinoTemplate casinoTemplate(PlayerToken token, ClembleConsumerDetails consumerDetails) {
        String consumerKey = token.getConsumerKey();
        String consumerSecret = new String(Base64.encodeBase64(consumerDetails.getSignatureSecret().getPrivateKey().getEncoded()), Charset.forName("UTF-8"));
        String accessToken = token.getValue();
        String accessTokenSecret = String.valueOf(token.getSecretKey().getEncoded());
        String player = token.getPlayer();
        try {
            return new ClembleCasinoTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret, player, host);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
