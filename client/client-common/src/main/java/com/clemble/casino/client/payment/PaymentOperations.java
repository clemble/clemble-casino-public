package com.clemble.casino.client.payment;

import java.util.List;

import com.clemble.casino.client.event.EventListener;
import com.clemble.casino.client.event.EventListenerController;
import com.clemble.casino.game.Game;
import com.clemble.casino.payment.PaymentTransaction;
import com.clemble.casino.payment.PaymentTransactionKey;
import com.clemble.casino.payment.PlayerAccount;
import com.clemble.casino.payment.bonus.PaymentBonusSource;
import com.clemble.casino.payment.event.PaymentEvent;
import com.clemble.casino.player.PlayerAware;

public interface PaymentOperations extends PlayerAware {

    public PlayerAccount getAccount();

    public List<PaymentTransaction> getPaymentTransactions();

    public PaymentTransaction getPaymentTransaction(PaymentTransactionKey transactionKey);

    public PaymentTransaction getPaymentTransaction(Game game, String transaction);

    public PaymentTransaction getPaymentTransaction(String source, String transaction);

    public List<PaymentTransaction> getPaymentTransactions(String source);

    public List<PaymentTransaction> getPaymentTransactions(Game game);

    public List<PaymentTransaction> getPaymentTransactions(PaymentBonusSource source);

    public EventListenerController subscribe(EventListener<PaymentEvent> listener);

}
