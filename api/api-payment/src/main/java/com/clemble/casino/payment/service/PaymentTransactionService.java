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

    public PaymentTransaction getTransaction(String source, String transactionId);

    public List<PaymentTransaction> getPlayerTransactions(String player);

    public List<PaymentTransaction> getPlayerTransactionsWithSource(String player, String source);

}
