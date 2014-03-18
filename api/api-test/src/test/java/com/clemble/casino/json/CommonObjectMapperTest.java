package com.clemble.casino.json;

import com.clemble.casino.game.RoundGameContext;
import com.clemble.casino.game.RoundGameState;
import com.clemble.test.random.AbstractValueGenerator;
import com.clemble.test.random.ObjectGenerator;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;

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
        ObjectGenerator.register(FakeState.class, new AbstractValueGenerator<FakeState>(){
            @Override
            public FakeState generate() {
                RoundGameContext roundGameContext = ObjectGenerator.generate(RoundGameContext.class);
                return new FakeState(roundGameContext, null, 0);
            }
        });
    }
}
