package com.clemble.casino.payment.service;

import com.clemble.casino.money.Currency;
import com.clemble.casino.payment.PlayerAccount;

import java.util.Collection;
import java.util.List;

/**
 * Created by mavarazy on 8/5/14.
 */
public interface PlayerAccountService {

    public PlayerAccount myAccount();

    public PlayerAccount getAccount(String playerWalletId);

    public List<String> canAfford(Collection<String> players, Currency currency, Long amount);

}
