package com.clemble.casino.tag.json;

import com.clemble.casino.json.ClembleJsonModule;
import com.clemble.casino.tag.event.TagCreatedEvent;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by mavarazy on 2/3/15.
 */
public class TagJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Tag");
        module.registerSubtypes(new NamedType(TagCreatedEvent.class, TagCreatedEvent.JSON_TYPE));
        return module;
    }

}
