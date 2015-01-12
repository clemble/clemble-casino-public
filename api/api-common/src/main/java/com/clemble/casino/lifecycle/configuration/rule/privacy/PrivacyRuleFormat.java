package com.clemble.casino.lifecycle.configuration.rule.privacy;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;

/**
 * Created by mavarazy on 1/10/15.
 */
public class PrivacyRuleFormat {

    public static class PrivacyRuleDeserialization extends JsonDeserializer<PrivacyRule> {

        @Override
        public PrivacyRule deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonToken start = p.nextToken();
            if(start == JsonToken.FIELD_NAME) {
                // Step 1. Reading first  field
                String a = p.getText();
                String aValue = p.nextTextValue();
                // Step 2. Reading second field
                String b = p.nextFieldName();
                String bValue = p.nextTextValue();
                // Step 3. Checking end
                p.nextToken();
                // Step 4. Checking field
                if (a == "name") {
                    return PrivacyRule.valueOf(aValue);
                } else {
                    return PrivacyRule.valueOf(bValue);
                }
            } else {
                return PrivacyRule.valueOf(p.getText());
            }
        }

        @Override
        public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
            return deserialize(p, ctxt);
        }

    }

    public static class PrivacyRuleSerializer extends JsonSerializer<PrivacyRule> {

        @Override
        public void serialize(PrivacyRule value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
            generator.writeStartObject();
            generator.writeFieldName("type");
            generator.writeString("rule:privacy");
            generator.writeFieldName("name");
            generator.writeString(value.getName());
            generator.writeEndObject();
        }

        @Override
        public void serializeWithType(PrivacyRule value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
            serialize(value, gen, serializers);
        }

    }

}
