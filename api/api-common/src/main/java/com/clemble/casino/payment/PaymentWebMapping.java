package com.clemble.casino.payment;

import com.clemble.casino.web.mapping.WebMapping;

public abstract class PaymentWebMapping implements WebMapping {

    final public static String PAYMENT_URL = "http://{host}/payment";

    final public static String PAYMENT_ACCOUNTS = "/account";

    final public static String PLAYER_ACCOUNT = "/account/{player}";
    final public static String MY_ACCOUNT = "/account/my";

    final public static String PLAYER_TRANSACTIONS = "/account/{player}/transaction";
    final public static String PLAYER_TRANSACTION_BY_SOURCE = "/account/{player}/transaction/{source}";

    final public static String MY_TRANSACTIONS = "/account/me/transaction";
    final public static String MY_TRANSACTIONS_BY_SOURCE = "/account/me/transaction/{source}";

    final public static String PAYMENT_TRANSACTIONS_TRANSACTION = "/transaction/{source}/{transaction}";

    public static String toPaymentUrl(String path) {
        return PAYMENT_URL + path;
    }

}
