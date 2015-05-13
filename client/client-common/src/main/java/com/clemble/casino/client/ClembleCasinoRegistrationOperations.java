package com.clemble.casino.client;

import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.registration.PlayerCredential;

public interface ClembleCasinoRegistrationOperations {

    public ClembleCasinoOperations login(final PlayerCredential playerCredentials);

    public ClembleCasinoOperations register(final PlayerCredential playerCredential, final PlayerProfile playerProfile);

    public ClembleCasinoOperations register(final PlayerCredential playerCredential, final SocialConnectionData socialConnectionData);

    public ClembleCasinoOperations register(final PlayerCredential playerCredential, final SocialAccessGrant accessGrant);

}
