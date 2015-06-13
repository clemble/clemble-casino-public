package com.clemble.casino.client;

import com.clemble.casino.social.SocialAccessGrant;
import com.clemble.casino.social.SocialConnectionData;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.registration.PlayerCredential;

public interface ClembleCasinoRegistrationOperations {

    ClembleCasinoOperations login(final PlayerCredential playerCredentials);

    ClembleCasinoOperations register(final PlayerCredential playerCredential, final PlayerProfile playerProfile);

    ClembleCasinoOperations register(final PlayerCredential playerCredential, final SocialConnectionData socialConnectionData);

    ClembleCasinoOperations register(final PlayerCredential playerCredential, final SocialAccessGrant accessGrant);

}
