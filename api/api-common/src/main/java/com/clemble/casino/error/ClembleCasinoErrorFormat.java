package com.clemble.casino.error;

import java.io.IOException;
import java.util.Date;

import com.clemble.casino.game.Game;
import com.clemble.casino.game.GameSessionAware;
import com.clemble.casino.game.GameSessionKey;
import com.clemble.casino.player.PlayerAware;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ClembleCasinoErrorFormat {

    /**
     * Custom {@link Date} Serializer, used by Jackson, through {@link JsonSerializer} annotation.
     * 
     * @author Anton Oparin
     * 
     */
    public static class ClembleCasinoErrorSerializer extends JsonSerializer<ClembleCasinoError> {

        @Override
        public void serialize(ClembleCasinoError error, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (error == null)
                return;

            jsonGenerator.writeStartObject();
            jsonGenerator.writeFieldName("code");
            jsonGenerator.writeString(error.getCode());
            jsonGenerator.writeFieldName("description");
            jsonGenerator.writeString(error.getDescription());
            jsonGenerator.writeEndObject();
        }

    }

    public static class ClembleCasinoErrorDeserializer extends JsonDeserializer<ClembleCasinoError> {

        @Override
        public ClembleCasinoError deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String code = null;
            JsonToken token = jp.getCurrentToken();
            while (token != null && token != JsonToken.START_OBJECT) {
                token = jp.nextToken();
            }
            while (token != null && token != JsonToken.END_OBJECT) {
                if ("code".equals(jp.getText()))
                    code = jp.nextTextValue();
                token = jp.nextToken();
            }
            return ClembleCasinoError.forCode(code);
        }
    }

}
