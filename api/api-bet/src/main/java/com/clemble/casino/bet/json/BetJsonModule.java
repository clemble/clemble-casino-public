package com.clemble.casino.bet.json;

import com.clemble.casino.bet.configuration.BetConfiguration;
import com.clemble.casino.json.ClembleJsonModule;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Created by mavarazy on 8/9/14.
 */
public class BetJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Bet");
        module.registerSubtypes(new NamedType(BetConfiguration.class, BetConfiguration.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
