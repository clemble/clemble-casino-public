package com.clemble.casino.payment.service;

import com.clemble.casino.payment.money.Currency;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.clemble.casino.ClembleService;
import com.clemble.casino.payment.PlayerAccount;

import java.util.Collection;
import java.util.List;

public interface PlayerAccountServiceContract extends ClembleService {

    public PlayerAccount getAccount(String playerWalletId);

    public List<String> canAfford(Collection<String> players, Currency currency, Long amount);

}
