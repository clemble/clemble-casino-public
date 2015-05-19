package com.clemble.casino.error;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.StringUtils;

public class ClembleErrorCodeFormat {

    public static class Serializer extends JsonSerializer<ClembleErrorCode> {

        @Override
        public void serialize(ClembleErrorCode error, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (error == null)
                return;

            String[] words = StringUtils.splitByCharacterTypeCamelCase(error.name());
            StringBuilder builder = new StringBuilder("server");
            for(String word: words) {
                builder.append("_").append(word.toLowerCase());
            }

            jsonGenerator.writeString(builder.toString());
        }

    }

     public static class Deserializer extends JsonDeserializer<ClembleErrorCode> {

        @Override
        public ClembleErrorCode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            // Step 1. Fetching words
            String[] words = jp.getText().split("_");
            // Step 2. Building word name
            StringBuilder name = new StringBuilder();
            for(int i = 1; i < words.length; i++) {
                name.append(StringUtils.capitalize(words[i]));
            }
            // Step 3. Constructing ErrorCode
            return ClembleErrorCode.valueOf(name.toString());
        }
    }

}
