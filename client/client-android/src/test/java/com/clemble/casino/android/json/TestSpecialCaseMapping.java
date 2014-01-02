package com.clemble.casino.android.json;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.clemble.casino.json.ObjectMapperUtils;
import com.clemble.casino.player.PlayerPresence;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestSpecialCaseMapping {

    final private String JSON = "[{\"player\":\"aaa1dd49369-45c6-4243-b742-f4dc8af41cb4\",\"session\":{\"game\":null,\"session\":null},\"presence\":\"online\"}]";

    ObjectMapper objectMapper = ObjectMapperUtils.createObjectMapper();

    @Test
    public void testRead() throws JsonParseException, JsonMappingException, IOException {
        List<PlayerPresence> presencesList = objectMapper.readValue(JSON, List.class);
        PlayerPresence[] presences = objectMapper.readValue(JSON, PlayerPresence[].class);
    }
}
