package com.clemble.casino.goal.lifecycle.configuration.rule.share;

import com.clemble.casino.lifecycle.configuration.rule.privacy.PrivacyRule;
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
public class ShareRuleFormat {

    public static class ShareRuleDeserialization extends JsonDeserializer<ShareRule> {

        @Override
        public ShareRule deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
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
                    return ShareRule.valueOf(aValue);
                } else {
                    return ShareRule.valueOf(bValue);
                }
            } else {
                return ShareRule.valueOf(p.getText());
            }
        }

        @Override
        public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
            return deserialize(p, ctxt);
        }

    }

    public static class ShareRuleSerializer extends JsonSerializer<ShareRule> {

        @Override
        public void serialize(ShareRule value, JsonGenerator generator, SerializerProvider serializers) throws IOException, JsonProcessingException {
            generator.writeStartObject();
            generator.writeFieldName("type");
            generator.writeString("rule:share");
            generator.writeFieldName("name");
            generator.writeString(value.getName());
            generator.writeEndObject();
        }

        @Override
        public void serializeWithType(ShareRule value, JsonGenerator gen, SerializerProvider serializers, TypeSerializer typeSer) throws IOException {
            serialize(value, gen, serializers);
        }

    }

}
