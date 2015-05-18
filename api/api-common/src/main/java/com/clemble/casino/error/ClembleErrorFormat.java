package com.clemble.casino.error;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClembleErrorFormat {

    public static class Serializer extends JsonSerializer<ClembleErrorCode> {

        @Override
        public void serialize(ClembleErrorCode error, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (error == null)
                return;

            jsonGenerator.writeString(error.name());
        }

    }

     public static class Deserializer extends JsonDeserializer<ClembleErrorCode> {

        @Override
        public ClembleErrorCode deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String code = jp.getText();
            return ClembleErrorCode.valueOf(code);
        }
    }

}
