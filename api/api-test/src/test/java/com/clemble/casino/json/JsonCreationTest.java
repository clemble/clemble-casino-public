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
import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.reflection.AnnotationReflectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCreationTest {

    private ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();

    @Before
    public void initialize() {
        ObjectTest.init();
    }

    final private String ERROR_FORMAT_JSON = "{\"problems\":["
            + "{\"error\":"
                + "{\"code\":\"040\",\"description\":\"Nick invalid\"},"
            + "\"player\":\"casino\","
            + "\"session\":{\"game\":null,\"session\":null}"
            + "}]}";
    final private String ERROR_JSON = "{\"error\":{\"code\":\"0C1\",\"description\":\"Server critical error\"},\"player\":\"f>RvzG{LHn\",\"session\":{\"game\":\"pac\",\"session\":\"'[jh$ FC([\"}}";

    @Test
    public void testSpecial() throws JsonParseException, JsonMappingException, IOException{
        ClembleCasinoFailure casinoFailure = objectMapper.readValue(ERROR_JSON, ClembleCasinoFailure.class);
        assertEquals(casinoFailure.getError().getCode(), "0C1");
        assertEquals(casinoFailure.getPlayer(), "f>RvzG{LHn");
        assertEquals(casinoFailure.getSession().getGame(), Game.pac);
        assertEquals(casinoFailure.getSession().getSession(), "'[jh$ FC([");
        //objectMapper.readValue(ERROR_FORMAT_JSON, ClembleCasinoFailureDescription.class);
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
