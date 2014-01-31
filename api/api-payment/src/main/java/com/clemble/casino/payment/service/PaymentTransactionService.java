package com.clemble.casino.payment.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.clemble.casino.ClembleService;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.web.mapping.WebMapping;
import com.clemble.casino.web.payment.PaymentWebMapping;

public interface PaymentTransactionService extends ClembleService {

    @RequestMapping(method = RequestMethod.GET, value = PaymentWebMapping.PAYMENT_TRANSACTIONS_TRANSACTION, produces = WebMapping.PRODUCES)
    public @ResponseBody PaymentTransaction getTransaction(@PathVariable("source") String source, @PathVariable("transaction") String transactionId);

    @RequestMapping(method = RequestMethod.GET, value = PaymentWebMapping.PAYMENT_ACCOUNTS_PLAYER_TRANSACTIONS, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<PaymentTransaction> getPlayerTransactions(@PathVariable("player") String player);

    @RequestMapping(method = RequestMethod.GET, value = PaymentWebMapping.PAYMENT_ACCOUNTS_PLAYER_TRANSACTION_SOURCE, produces = WebMapping.PRODUCES)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody List<PaymentTransaction> getPlayerTransactionsWithSource(@PathVariable("player") String player, @PathVariable("source") String source);

}
