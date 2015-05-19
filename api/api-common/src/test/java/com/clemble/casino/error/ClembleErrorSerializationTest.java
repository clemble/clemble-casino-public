package com.clemble.casino.error;

import com.clemble.casino.json.ObjectMapperUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by mavarazy on 5/19/15.
 */
public class ClembleErrorSerializationTest {

    @Test
    public void testReadableAndWritable() throws IOException {
        ObjectMapper objectMapper = ObjectMapperUtils.OBJECT_MAPPER;
        for(ClembleErrorCode error: ClembleErrorCode.values()) {
            String strPresentation = objectMapper.writeValueAsString(error);
            System.out.println(strPresentation);
            ClembleErrorCode deserializedError = objectMapper.readValue(strPresentation, ClembleErrorCode.class);
            Assert.assertEquals(deserializedError, error);
        }
    }

}
