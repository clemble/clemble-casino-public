package com.clemble.casino.json;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clemble.test.random.AbstractValueGenerator;
import org.joda.time.DateTimeZone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.reflection.AnnotationReflectionUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonCreationTest {

    private ObjectMapper objectMapper = ObjectMapperUtils.OBJECT_MAPPER;

    @Before
    public void initialize() {
        TestObjectGeneratorInitializer.init();
        ObjectGenerator.register(DateTimeZone.class, new AbstractValueGenerator<DateTimeZone>() {
            @Override
            public DateTimeZone generate() {
                return DateTimeZone.UTC;
            }
        });
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
