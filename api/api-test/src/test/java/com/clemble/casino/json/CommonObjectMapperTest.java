package com.clemble.casino.json;

import com.clemble.casino.event.Event;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.bonus.DailyBonusPaymentSource;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.player.notification.PlayerInvitedNotification;
import com.clemble.casino.utils.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by mavarazy on 22/12/13.
 */
public class CommonObjectMapperTest extends AbstractObjectMapperTest {

    @Test
    public void testSpecialCases() {
        assertNull(checkSerialization(DailyBonusPaymentSource.class));
        assertNull(checkSerialization(PlayerProfile.class));
        assertNull(checkSerialization(GoalState.class));
        assertNull(checkSerialization(PlayerProfile.class));
    }

}
