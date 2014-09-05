package com.clemble.casino.json;

import com.clemble.test.random.ObjectGenerator;
import com.clemble.test.reflection.AnnotationReflectionUtils;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * Created by mavarazy on 18/03/14.
 */
abstract public class AbstractObjectMapperTest {

    private ObjectMapper objectMapper = ObjectMapperUtils.OBJECT_MAPPER;

    @Before
    public void initialize() {
        TestObjectGeneratorInitializer.init();
    }

    @Test
    public void testJsonSerialization() throws IOException {
        List<Class<?>> candidates = AnnotationReflectionUtils.findCandidates("com.clemble.casino", JsonTypeName.class);

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
        assertTrue(errors.toString(), errors.isEmpty());

    }

    protected Throwable checkSerialization(Class<?> candidate) {
        Throwable error = null;
        try {
            Object expected = ObjectGenerator.generate(candidate);
            String stringPresentation = objectMapper.writeValueAsString(expected);
            Object actual = objectMapper.readValue(stringPresentation, candidate);

            assertEquals(stringPresentation, expected, actual);

            if (!candidate.isInterface()) {
                Class<?> originalClass = getOriginal(candidate);
                if (originalClass != null) {
                    actual = objectMapper.readValue(stringPresentation, originalClass);

                    assertEquals(expected, actual);
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            error = throwable;
        }
        return error;
    }

    final public Class<?> getOriginal(final Class<?> source) {
        Class<?> superClass = source;
        boolean found = false;
        Set<Class<?>> interfaces = new HashSet<Class<?>>();

        do {
            found = superClass.getAnnotation(JsonTypeInfo.class) != null;
            if (!found) {
                interfaces.addAll(Arrays.asList(superClass.getInterfaces()));
                superClass = superClass.getSuperclass();
            }
        } while (superClass != Object.class && !found);

        if (!found) {
            Set<Class<?>> interfaceParents = new HashSet<Class<?>>();
            do {
                for (Class<?> interfaceClass : interfaces) {
                    if (interfaceClass.getAnnotation(JsonTypeInfo.class) != null) {
                        superClass = interfaceClass;
                        found = true;
                        break;
                    } else {
                        if (interfaceClass.getSuperclass() != null && interfaceClass.getSuperclass() != Object.class) {
                            interfaceParents.add(interfaceClass);
                        }
                        interfaceParents.addAll(Arrays.asList(interfaceClass.getInterfaces()));
                    }
                }
                interfaces = interfaceParents;
                interfaceParents = new HashSet<Class<?>>();
            } while (!found && !interfaces.isEmpty());
        }

        return superClass != Object.class ? superClass : null;
    }

}
