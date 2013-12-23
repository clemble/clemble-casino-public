package com.clemble.casino.json;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

class ExtenstionJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Extenstion");
        module.registerSubtypes(new NamedType(FakeState.class, FakeState.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
