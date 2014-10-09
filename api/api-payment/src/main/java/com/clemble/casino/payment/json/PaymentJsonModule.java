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
        module.registerSubtypes(new NamedType(PaymentBonusEvent.class, PaymentBonusEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(PaymentCompleteEvent.class, PaymentCompleteEvent.JSON_TYPE));
        return module;
    }

}
