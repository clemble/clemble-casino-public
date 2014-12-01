package com.clemble.casino.payment.bonus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeName(RegistrationBonusPaymentSource.JSON_TYPE)
public class RegistrationBonusPaymentSource implements BonusPaymentSource {

    final public static String JSON_TYPE = "bonus:registration";

    final public static RegistrationBonusPaymentSource INSTANCE = new RegistrationBonusPaymentSource();

    public RegistrationBonusPaymentSource() {
    }

    @Override
    public String toTransactionKey(String player) {
        return player + ":registration";
    }

    @JsonCreator
    public static RegistrationBonusPaymentSource getInstance(@JsonProperty("type") String betType) {
        return INSTANCE;
    }

    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object other) {
        return other != null && other.getClass() == RegistrationBonusPaymentSource.class;
    }

}
