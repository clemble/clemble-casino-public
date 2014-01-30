package com.clemble.casino.json;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.clemble.casino.error.ClembleCasinoFailure;
import com.clemble.casino.game.Game;
import com.clemble.casino.game.event.server.GameInitiationCanceledEvent;
import com.clemble.casino.game.rule.bet.UnlimitedBetRule;
import com.clemble.casino.game.rule.construct.PlayerNumberRule;
import com.clemble.casino.game.rule.construct.PrivacyRule;
import com.clemble.casino.game.rule.giveup.GiveUpRule;
import com.clemble.casino.game.rule.outcome.DrawRule;
import com.clemble.casino.game.rule.outcome.WonRule;
import com.clemble.casino.game.rule.time.MoveTimeRule;
import com.clemble.casino.game.rule.time.TimeBreachPunishment;
import com.clemble.casino.game.rule.time.TotalTimeRule;
import com.clemble.casino.game.rule.visibility.VisibilityRule;
import com.clemble.casino.game.specification.GameConfiguration;
import com.clemble.casino.game.specification.GameConfigurationKey;
import com.clemble.casino.game.specification.MatchGameConfiguration;
import com.clemble.casino.payment.money.Currency;
import com.clemble.casino.payment.money.Money;
import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.reflection.AnnotationReflectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

public class JsonCreationTest {

    private ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();

    @Before
    public void initialize() {
        ObjectTest.init();
    }

    // final private String ERROR_FORMAT_JSON = "{\"problems\":["
    // + "{\"error\":"
    // + "{\"code\":\"040\",\"description\":\"Nick invalid\"},"
    // + "\"player\":\"casino\","
    // + "\"session\":{\"game\":null,\"session\":null}"
    // + "}]}";
    final private String ERROR_JSON = "{\"error\":{\"code\":\"0C1\",\"description\":\"Server critical error\"},\"player\":\"f>RvzG{LHn\",\"session\":{\"game\":\"pac\",\"session\":\"'[jh$ FC([\"}}";
    final private String MATCH_JSON = "[{\"type\":\"match\",\"configurationKey\":{\"game\":\"num\",\"specificationName\":\"low\"},\"price\":{\"currency\":\"FakeMoney\",\"amount\":50},\"betRule\":{\"betType\":\"unlimited\"},\"giveUpRule\":{\"giveUp\":\"all\"},\"moveTimeRule\":{\"rule\":\"moveTime\",\"limit\":2000,\"punishment\":\"loose\"},\"totalTimeRule\":{\"rule\":\"totalTime\",\"limit\":4000,\"punishment\":\"loose\"},\"privacyRule\":[\"privacy\",\"everybody\"],\"numberRule\":[\"participants\",\"two\"],\"visibilityRule\":\"visible\",\"drawRule\":[\"DrawRule\",\"owned\"],\"wonRule\":[\"WonRule\",\"price\"],\"roles\":[\"A\",\"B\"]}]";

    @Test
    public void testSpecial() throws JsonParseException, JsonMappingException, IOException {
        checkSerialization(GameInitiationCanceledEvent.class);
        ClembleCasinoFailure casinoFailure = objectMapper.readValue(ERROR_JSON, ClembleCasinoFailure.class);
        assertEquals(casinoFailure.getError().getCode(), "0C1");
        assertEquals(casinoFailure.getPlayer(), "f>RvzG{LHn");
        assertEquals(casinoFailure.getSession().getGame(), Game.pac);
        assertEquals(casinoFailure.getSession().getSession(), "'[jh$ FC([");
        // objectMapper.readValue(ERROR_FORMAT_JSON, ClembleCasinoFailureDescription.class);
    }

    @Test
    public void testRead() throws JsonParseException, JsonMappingException, IOException {
        GameConfiguration[] configurations = objectMapper.readValue(MATCH_JSON, MatchGameConfiguration[].class);
        String arrJsonPresentation = objectMapper.writeValueAsString(configurations);
        assertEquals(arrJsonPresentation, MATCH_JSON);
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
        MatchGameConfiguration configuration = new MatchGameConfiguration(new GameConfigurationKey(Game.num, "low"), new Money(Currency.FakeMoney, 50),
                UnlimitedBetRule.INSTANCE, GiveUpRule.all, new MoveTimeRule(2000, TimeBreachPunishment.loose), new TotalTimeRule(4000,
                        TimeBreachPunishment.loose), PrivacyRule.everybody, PlayerNumberRule.two, VisibilityRule.visible, DrawRule.owned, WonRule.price,
                ImmutableList.<String> of("A", "B"));

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
