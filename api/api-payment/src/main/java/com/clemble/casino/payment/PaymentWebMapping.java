package com.clemble.casino.payment;

import com.clemble.casino.WebMapping;

public abstract class PaymentWebMapping implements WebMapping {

    final public static String PAYMENT_URL = "http://{host}/payment";

    final public static String ACCOUNTS = "/account";
    final public static String ACCOUNT = "/account/{player}";
    final public static String TRANSACTIONS = "/account/{player}/transaction";
    final public static String TRANSACTIONS_BY_ID = "/transaction/{transaction}";

    final public static String MY_ACCOUNT = "/account/my";
    final public static String MY_TRANSACTIONS = "/account/my/transaction";
    final public static String MY_TRANSACTIONS_BY_SOURCE = "/account/my/transaction/{source}";

    public static String toPaymentUrl(String path) {
        return PAYMENT_URL + path;
    }

}
