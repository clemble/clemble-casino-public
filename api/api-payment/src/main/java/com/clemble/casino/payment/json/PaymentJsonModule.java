package com.clemble.casino.payment.json;

import com.clemble.casino.json.ClembleJsonModule;
import com.clemble.casino.payment.event.PaymentBonusEvent;
import com.clemble.casino.payment.event.PaymentCompleteEvent;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class PaymentJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Payment");
        module.registerSubtypes(new NamedType(PaymentBonusEvent.class, PaymentBonusEvent.class.getAnnotation(JsonTypeName.class).value()));
        module.registerSubtypes(new NamedType(PaymentCompleteEvent.class, PaymentCompleteEvent.class.getAnnotation(JsonTypeName.class).value()));
        return module;
    }

}
