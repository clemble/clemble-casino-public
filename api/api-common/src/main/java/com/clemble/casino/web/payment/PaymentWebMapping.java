package com.clemble.casino.web.payment;

import com.clemble.casino.web.mapping.WebMapping;

public abstract class PaymentWebMapping implements WebMapping {

    final public static String PAYMENT_URL = "http://{host}/payment";

    final public static String PAYMENT_ACCOUNTS = "/account";

    final public static String ACCOUNTS_PLAYER = "/account/{player}";
    final public static String PAYMENT_ACCOUNTS_PLAYER_TRANSACTIONS = "/account/{player}/transaction";
    final public static String PAYMENT_ACCOUNTS_PLAYER_TRANSACTION_SOURCE = "/account/{player}/transaction/{source}";

    final public static String PAYMENT_TRANSACTIONS = "/transaction";
    final public static String PAYMENT_TRANSACTIONS_TRANSACTION = "/transaction/{source}/{transaction}";

    public static String toPaymentUrl(String path) {
        return PAYMENT_URL + path;
    }

}
