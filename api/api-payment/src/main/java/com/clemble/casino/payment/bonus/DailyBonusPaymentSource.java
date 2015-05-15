package com.clemble.casino.payment.bonus;

import com.clemble.casino.CreatedAware;
import com.clemble.casino.payment.PaymentSource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mavarazy on 12/1/14.
 */
@JsonTypeName(DailyBonusPaymentSource.JSON_TYPE)
public class DailyBonusPaymentSource implements BonusPaymentSource, CreatedAware {

    final public static String JSON_TYPE = "payment:bonus:daily";

    final private static String DATE_FORMAT = "ddMMyy";

    final private DateTime created;

    @JsonCreator
    public DailyBonusPaymentSource(@JsonProperty("created") DateTime created) {
        this.created = created;
    }

    @Override
    public DateTime getCreated() {
        return created;
    }

    @Override
    public String toTransactionKey(String player) {
        return player + ":dailybonus:" + created.toString(DATE_FORMAT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyBonusPaymentSource that = (DailyBonusPaymentSource) o;

        if (created != null ? !created.equals(that.created) : that.created != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return created != null ? created.hashCode() : 0;
    }
}
