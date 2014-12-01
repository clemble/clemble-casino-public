package com.clemble.casino.payment.bonus;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeName(DiscoveryBonusPaymentSource.JSON_TYPE)
public class DiscoveryBonusPaymentSource implements BonusPaymentSource {

    final public static String JSON_TYPE = "bonus:social:discovery";

    final private String discovered;

    @JsonCreator
    public DiscoveryBonusPaymentSource(@JsonProperty("discovered") String discovered) {
        this.discovered = discovered;
    }

    public String getDiscovered() {
        return discovered;
    }

    public String toTransactionKey(String player) {
        return player + ":discovered:" + discovered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiscoveryBonusPaymentSource that = (DiscoveryBonusPaymentSource) o;

        if (!discovered.equals(that.discovered)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return discovered.hashCode();
    }

}
