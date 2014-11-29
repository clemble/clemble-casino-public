package com.clemble.casino.json;

import com.clemble.casino.event.Event;
import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
import com.clemble.casino.game.lifecycle.management.RoundGameContext;
import com.clemble.casino.game.lifecycle.management.RoundGameState;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.casino.notification.PlayerNotification;
import com.clemble.casino.payment.bonus.PaymentBonusSource;
import com.clemble.casino.payment.notification.PaymentBonusNotification;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.player.notification.PlayerDiscoveredNotification;
import com.clemble.casino.utils.CollectionUtils;
import com.clemble.test.random.AbstractValueGenerator;
import com.clemble.test.random.ObjectGenerator;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;

/**
 * Created by mavarazy on 22/12/13.
 */
public class CommonObjectMapperTest extends AbstractObjectMapperTest {

    @BeforeClass
    public static void init(){
        ObjectGenerator.register(RoundGameState.class, new AbstractValueGenerator<RoundGameState>() {
            @Override
            public RoundGameState generate() {
                RoundGameContext roundGameContext = ObjectGenerator.generate(RoundGameContext.class);
                return new RoundGameState(ObjectGenerator.generate(RoundGameConfiguration.class), roundGameContext, new FakeState(), 0);
            }
        });
    }

    @Test
    public void testSpecialCases() {
        assertNull(checkSerialization(PrivacyRule.class));
        assertNull(checkSerialization(GoalState.class));
        assertNull(checkSerialization(PlayerProfile.class));
    }

    @Test
    public void testCollectionSerialization() throws IOException {
        // Step 1. Creating notifications
        PlayerNotification[] events = new PlayerNotification[]{new PlayerDiscoveredNotification("A", "B"), new PaymentBonusNotification("A", PaymentBonusSource.registration, Money.create(Currency.FakeMoney, 100))};
        // Step 2. Json notification
        String jsonNotifications = objectMapper.writeValueAsString(events);
        // Step 3. Reading notification
        List<Event> deserialized = CollectionUtils.immutableList(objectMapper.readValue(jsonNotifications, Event[].class));
        // Step 4. Checking value
        Assert.assertEquals(Arrays.asList(events), deserialized);
    }

}
