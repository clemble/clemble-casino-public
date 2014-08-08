package com.clemble.casino.android.payment;

import static com.clemble.casino.utils.Preconditions.checkNotNull;

import java.util.List;

import com.clemble.casino.payment.service.PaymentTransactionService;
import org.springframework.web.client.RestTemplate;

import com.clemble.casino.android.AbstractClembleCasinoOperations;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.utils.CollectionUtils;
import static com.clemble.casino.payment.PaymentWebMapping.*;

public class AndroidPaymentTransactionService extends AbstractClembleCasinoOperations implements PaymentTransactionService {

    final private RestTemplate restTemplate;

    public AndroidPaymentTransactionService(RestTemplate restTemplate, String host) {
        super(host);
        this.restTemplate = checkNotNull(restTemplate);
    }

    @Override
    public PaymentTransaction getTransaction(String source, String transactionId) {
        return restTemplate.getForObject(buildUriWith(toPaymentUrl(TRANSACTIONS_BY_ID), source, transactionId), PaymentTransaction.class);
    }

    @Override
    public List<PaymentTransaction> getPlayerTransactions(String player) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toPaymentUrl(TRANSACTIONS), player), PaymentTransaction[].class));
    }

    @Override
    public List<PaymentTransaction> getPlayerTransactionsWithSource(String player, String source) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toPaymentUrl(TRANSACTION_BY_SOURCE), player, source), PaymentTransaction[].class));
    }

    @Override
    public List<PaymentTransaction> myTransactions() {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toPaymentUrl(MY_TRANSACTIONS)), PaymentTransaction[].class));
    }

    @Override
    public List<PaymentTransaction> myTransactions(String source) {
        return CollectionUtils.immutableList(restTemplate.getForObject(buildUriWith(toPaymentUrl(MY_TRANSACTIONS_BY_SOURCE), source), PaymentTransaction[].class));
    }

}
