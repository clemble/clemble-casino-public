package com.clemble.casino.json;

import com.clemble.casino.event.PlayerAwareEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import org.junit.Test;

import java.util.Collection;

/**
 * Created by mavarazy on 17/03/14.
 */
public class SubtypeResolverTest {

    @Test
    public void getPlayerAwareEventSubtypes() {
        ObjectMapper mapper = ObjectMapperUtils.OBJECT_MAPPER;

        AnnotatedClass annotatedClass = AnnotatedClass.construct(
                PlayerAwareEvent.class,
                mapper.getSerializationConfig().getAnnotationIntrospector(),
                mapper.getSerializationConfig());

        Collection<NamedType> resolvedTypes = mapper.getSubtypeResolver().collectAndResolveSubtypes(annotatedClass, mapper.getSerializationConfig(), mapper.getSerializationConfig().getAnnotationIntrospector());
        for(NamedType type: resolvedTypes) {
            System.out.println("T >> " + type.getName() + " >> " + type.getType());
        }
    }
}
