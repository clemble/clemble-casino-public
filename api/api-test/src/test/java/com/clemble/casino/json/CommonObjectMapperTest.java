package com.clemble.casino.json;

import com.clemble.casino.game.lifecycle.management.RoundGameContext;
import com.clemble.casino.game.lifecycle.management.RoundGameState;
import com.clemble.casino.goal.lifecycle.management.GoalState;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.test.random.AbstractValueGenerator;
import com.clemble.test.random.ObjectGenerator;
import org.junit.BeforeClass;
import org.junit.Test;

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
                return new RoundGameState(roundGameContext, new FakeState(), 0);
            }
        });
    }

    @Test
    public void testSpecialCases() {
        assertNull(checkSerialization(GoalState.class));
        assertNull(checkSerialization(PrivacyRule.class));
        assertNull(checkSerialization(PlayerProfile.class));
    }

}
