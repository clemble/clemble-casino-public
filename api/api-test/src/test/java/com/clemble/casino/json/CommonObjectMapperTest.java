package com.clemble.casino.json;

import com.clemble.casino.game.RoundGameContext;
import com.clemble.casino.game.RoundGameState;
import com.clemble.casino.player.PlayerProfile;
import com.clemble.casino.rule.privacy.PrivacyRule;
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
                return new FakeState(roundGameContext, null, 0);
            }

        });
    }

    @Test
    public void testSpecialCases() {
        assertNull(checkSerialization(PrivacyRule.class));
        assertNull(checkSerialization(PlayerProfile.class));
    }

}
