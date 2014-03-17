package com.clemble.casino.android.json;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;

import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.player.PlayerPresence;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSpecialCaseMapping {

    final private String JSON = "[{\"player\":\"aaa1dd49369-45c6-4243-b742-f4dc8af41cb4\",\"session\":{\"game\":null,\"session\":null},\"presence\":\"online\"}]";

    ObjectMapper objectMapper = ObjectMapperUtils.OBJECT_MAPPER;

    @Test
    public void testRead() throws JsonParseException, JsonMappingException, IOException {
        PlayerPresence[] presences = objectMapper.readValue(JSON, PlayerPresence[].class);
        assertNotNull(presences[0]);
    }
}
