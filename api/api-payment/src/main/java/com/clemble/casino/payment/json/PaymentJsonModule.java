package com.clemble.casino.payment.json;

import com.clemble.casino.json.ClembleJsonModule;
import com.clemble.casino.payment.bonus.*;
import com.clemble.casino.payment.event.PaymentCompleteEvent;
import com.clemble.casino.payment.event.PaymentFreezeEvent;
import com.clemble.casino.payment.notification.PaymentNotification;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class PaymentJsonModule implements ClembleJsonModule {

    @Override
    public Module construct() {
        SimpleModule module = new SimpleModule("Payment");

        module.registerSubtypes(new NamedType(PaymentCompleteEvent.class, PaymentCompleteEvent.JSON_TYPE));
        module.registerSubtypes(new NamedType(PaymentFreezeEvent.class, PaymentFreezeEvent.JSON_TYPE));

        module.registerSubtypes(new NamedType(PaymentNotification.class, PaymentNotification.JSON_TYPE));

        module.registerSubtypes(new NamedType(SocialAddedBonusPaymentSource.class, SocialAddedBonusPaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(DailyBonusPaymentSource.class, DailyBonusPaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(EmailVerifiedBonusPaymentSource.class, EmailVerifiedBonusPaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(GoalReachedBonusPaymentSource.class, GoalReachedBonusPaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(PhoneVerifiedBonusPaymentSource.class, PhoneVerifiedBonusPaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(DiscoveryBonusPaymentSource.class, DiscoveryBonusPaymentSource.JSON_TYPE));
        module.registerSubtypes(new NamedType(RegistrationBonusPaymentSource.class, RegistrationBonusPaymentSource.JSON_TYPE));

        return module;
    }

}
