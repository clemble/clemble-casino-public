package com.clemble.casino.json;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clemble.casino.event.action.PlayerExpectedAction;
import com.clemble.casino.game.lifecycle.configuration.TournamentGameConfiguration;
import com.clemble.casino.game.lifecycle.management.*;
import com.clemble.casino.game.lifecycle.construction.GameConstruction;
import com.clemble.casino.game.lifecycle.configuration.RoundGameConfiguration;
import com.clemble.casino.lifecycle.configuration.rule.breach.LooseBreachPunishment;
import com.clemble.test.random.AbstractValueGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.clemble.casino.error.ClembleCasinoFailure;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.lifecycle.initiation.event.GameInitiationCanceledEvent;
import com.clemble.casino.lifecycle.configuration.rule.bet.UnlimitedBetRule;
import com.clemble.casino.game.lifecycle.configuration.rule.construct.PlayerNumberRule;
import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
import com.clemble.casino.game.lifecycle.configuration.rule.giveup.GiveUpRule;
import com.clemble.casino.game.lifecycle.configuration.rule.outcome.DrawRule;
import com.clemble.casino.game.lifecycle.configuration.rule.outcome.WonRule;
import com.clemble.casino.lifecycle.configuration.rule.time.MoveTimeRule;
import com.clemble.casino.lifecycle.configuration.rule.time.TotalTimeRule;
import com.clemble.casino.game.lifecycle.configuration.rule.visibility.VisibilityRule;
import com.clemble.casino.money.Currency;
import com.clemble.casino.money.Money;
import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.reflection.AnnotationReflectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

public class JsonCreationTest {

    private ObjectMapper objectMapper = ObjectMapperUtils.OBJECT_MAPPER;

    @Before
    public void initialize() {
        TestObjectGeneratorInitializer.init();
        ObjectGenerator.register(RoundGameState.class, new AbstractValueGenerator<RoundGameState>() {
            @Override
            public RoundGameState generate() {
                return new RoundGameState(ObjectGenerator.generate(RoundGameConfiguration.class), ObjectGenerator.generate(RoundGameContext.class), new FakeState(), 0);
            }
        });
        ObjectGenerator.register(TournamentGameState.class, new AbstractValueGenerator<TournamentGameState>() {
            @Override
            public TournamentGameState generate() {
                TournamentGameContext context = new TournamentGameContext(
                    "",
                    null,
                    ObjectGenerator.generateList(TournamentGamePlayerContext.class, 10),
                    null);
                return new TournamentGameState(
                        ObjectGenerator.generate(TournamentGameConfiguration.class),
                        context,
                        null,
                        0);
            }
        });
    }

    // final private String ERROR_FORMAT_JSON = "{\"problems\":["
    // + "{\"error\":"
    // + "{\"code\":\"040\",\"description\":\"Nick invalid\"},"
    // + "\"player\":\"casino\","
    // + "\"session\":{\"game\":null,\"session\":null}"
    // + "}]}";
    final private String ERROR_JSON = "{\"error\":{\"code\":\"0C1\",\"description\":\"Server critical error\"},\"player\":\"f>RvzG{LHn\"}";

    @Test
    public void testSpecial() throws JsonParseException, JsonMappingException, IOException {
        Assert.assertEquals(checkSerialization(TournamentGameState.class), null);
        Assert.assertEquals(checkSerialization(MatchGameState.class), null);
        Assert.assertEquals(checkSerialization(GameConstruction.class), null);
        Assert.assertEquals(checkSerialization(PlayerExpectedAction.class), null);
        Assert.assertEquals(checkSerialization(MatchGameContext.class), null);
        Assert.assertEquals(checkSerialization(GameInitiationCanceledEvent.class), null);
        ClembleCasinoFailure casinoFailure = objectMapper.readValue(ERROR_JSON, ClembleCasinoFailure.class);
        assertEquals(casinoFailure.getError().getCode(), "0C1");
        assertEquals(casinoFailure.getPlayer(), "f>RvzG{LHn");
        // objectMapper.readValue(ERROR_FORMAT_JSON, ClembleCasinoFailureDescription.class);
    }

    @Test
    public void testJsonSerialization() throws IOException {
        List<Class<?>> candidates = AnnotationReflectionUtils.findCandidates("com.clemble.casino", JsonCreator.class);

        Map<Class<?>, Throwable> errors = new HashMap<Class<?>, Throwable>();

        for (Class<?> candidate : candidates) {
            Throwable error = checkSerialization(candidate);
            if (error != null) {
                errors.put(candidate, error);
            }
        }

        if (errors.size() != 0) {
            for (Map.Entry<Class<?>, Throwable> problem : errors.entrySet()) {
                System.out.println("Problem " + problem.getKey().getSimpleName() + " > " + problem.getValue().getClass().getSimpleName());
            }
        }

        Assert.assertTrue(errors.toString(), errors.isEmpty());
    }

    @Test
    public void test() throws JsonProcessingException {
        RoundGameConfiguration configuration = new RoundGameConfiguration(Game.num, "low", new Money(Currency.point, 50),
                UnlimitedBetRule.INSTANCE, GiveUpRule.all, new MoveTimeRule(2000, LooseBreachPunishment.getInstance()), new TotalTimeRule(4000, LooseBreachPunishment.getInstance()), PrivacyRule.world, PlayerNumberRule.two, VisibilityRule.visible, DrawRule.owned, WonRule.price,
                ImmutableList.<String> of("A", "B"), null);

        System.out.println(objectMapper.writeValueAsString(configuration));
    }

    private Throwable checkSerialization(Class<?> candidate) {
        Throwable error = null;
        String stringPresentation = null;
        try {
            Object expected = ObjectGenerator.generate(candidate);
            stringPresentation = objectMapper.writeValueAsString(expected);
            Object actual = objectMapper.readValue(stringPresentation, candidate);
            assertEquals(stringPresentation, expected, actual);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            error = throwable;
        }
        return error;
    }

}
