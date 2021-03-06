package com.clemble.casino.json;

import com.clemble.casino.player.event.PlayerEvent;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import org.joda.time.DateTimeZone;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapperUtils {

    final static public ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public static ObjectMapper createObjectMapper() {
        DateTimeZone.setDefault(DateTimeZone.UTC);
        ObjectMapper objectMapper = new ObjectMapper();
        // Step 1. Adding all JsonTypeName files
        objectMapper.registerModule(new JodaModule());
        loadJsonModule("com.clemble.casino.json.GenericJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.json.CommonJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.player.json.PlayerJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.player.json.PresenceJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.bet.json.BetJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.goal.json.GoalJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.payment.json.PaymentJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.tag.json.TagJsonModule", objectMapper);
        loadJsonModule("com.clemble.casino.json.ExtenstionJsonModule", objectMapper);
        // Step 1.1. Initializing Facebook module if available
        loadJsonModule("org.springframework.social.facebook.api.impl.json.FacebookModule", objectMapper);
        return objectMapper;
    }

    public static <T> Map<String, Class<? extends T>> collectSubtypes(Class<T> type) {
        // Step 1. Creating empty collection aggregator
        Map<String, Class<? extends T>> collectedSubtypes = new HashMap<String, Class<? extends T>>();
        // Step 2. Constructing ObjectMapper // TODO reuse already constructed ObjectMapper
        ObjectMapper mapper = OBJECT_MAPPER;
        AnnotatedClass annotatedClass = AnnotatedClass.construct(
                type,
                mapper.getSerializationConfig().getAnnotationIntrospector(),
                mapper.getSerializationConfig());
        // Step 3. Collecting NamedTypes
        Collection<NamedType> resolvedTypes = mapper.getSubtypeResolver().collectAndResolveSubtypes(annotatedClass, mapper.getSerializationConfig(), mapper.getSerializationConfig().getAnnotationIntrospector());
        for(NamedType namedType: resolvedTypes) {
            collectedSubtypes.put(namedType.getName(), (Class<? extends T>) namedType.getType());
        }

        return collectedSubtypes;
    }

    private static void loadJsonModule(String module, ObjectMapper mapper) {
        try {
            ClembleJsonModule jsonModule = (ClembleJsonModule) Class.forName(module).newInstance();
            mapper.registerModule(jsonModule.construct());
        } catch (Throwable throwable) {
            // Ignore
            if (module.startsWith("com.clemble")) {
                System.err.println("Failed to process " + module);
            }
        }
    }

}
